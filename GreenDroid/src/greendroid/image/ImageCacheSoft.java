package greendroid.image;

import greendroid.util.GDUtils;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

public class ImageCacheSoft extends ImageCache {
	private static final int DELAY_BEFORE_PURGE = 30 * 1000; // in milliseconds

	private final ConcurrentHashMap<String, SoftReference<Bitmap>> mSoftCache;

	private final Handler purgeHandler = new Handler();

	private final Runnable purger = new Runnable() {
		public void run() {
			Log.i("ImageCache", "Flushing Memory Cache");
			flush();
		}
	};

	public ImageCacheSoft(Context context) {
		mSoftCache = new ConcurrentHashMap<String, SoftReference<Bitmap>>();
		GDUtils.getGDApplication(context).registerOnLowMemoryListener(this);
	}

	public static ImageCache from(Context context) {
		return GDUtils.getImageCache(context);
	}

	public Bitmap get(String url) {
		resetPurgeTimer();

		Bitmap bitmap = null;

		// Try and get bitmap from in-memory cache
		final SoftReference<Bitmap> ref = mSoftCache.get(url);
		if (ref != null) {
			bitmap = ref.get();
		}

		if (bitmap == null) {
			mSoftCache.remove(url);
		}

		return bitmap;
	}

	public void put(String url, Bitmap bitmap) {
		mSoftCache.put(url, new SoftReference<Bitmap>(bitmap));
	}

	public void flush() {
		mSoftCache.clear();
	}

	void resetPurgeTimer() {
		purgeHandler.removeCallbacks(purger);
		purgeHandler.postDelayed(purger, DELAY_BEFORE_PURGE);
	}

	public void onLowMemoryReceived() {
		flush();
	}
}
