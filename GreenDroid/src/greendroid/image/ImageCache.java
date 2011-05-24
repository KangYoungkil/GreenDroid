/*
 * Copyright (C) 2010 Cyril Mottier (http://www.cyrilmottier.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package greendroid.image;

import greendroid.app.GDApplication.OnLowMemoryListener;
import greendroid.util.GDUtils;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

public class ImageCache implements OnLowMemoryListener {

	private static final int DELAY_BEFORE_PURGE = 30 * 1000; // in milliseconds

	private final ConcurrentHashMap<String, SoftReference<Bitmap>> mSoftCache;

	private final Handler purgeHandler = new Handler();

	private final Runnable purger = new Runnable() {
		public void run() {
			Log.i("ImageCache", "Flushing Memory Cache");
			flush();
		}
	};

	public ImageCache(Context context) {
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

			// Get bitmap from Hard Cache (SD-Card)
			bitmap = ImageCacheHard.getCacheFile(url);
		}

		return bitmap;
	}

	public void put(String url, Bitmap bitmap) {
		mSoftCache.put(url, new SoftReference<Bitmap>(bitmap));
	}

	public void flush() {
		mSoftCache.clear();
	}

	private void resetPurgeTimer() {
		purgeHandler.removeCallbacks(purger);
		purgeHandler.postDelayed(purger, DELAY_BEFORE_PURGE);
	}

	public void onLowMemoryReceived() {
		flush();
	}
}
