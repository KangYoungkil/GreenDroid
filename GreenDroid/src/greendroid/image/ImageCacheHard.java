package greendroid.image;

/**
 * This is a much edited version of the code produced by Sergi Juanola
 * available at http://www.flexjockey.com/2011/03/create-a-pretty-simple-cache-for-android/
 * 
 * @author Chris Banes
 */

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;

public class ImageCacheHard extends ImageCache {
	
	public ImageCacheHard(Application app){
		super();
		cache = ImageCacheHardStore.getInstance(app);
	}

	// Singleton of the actual SD Cache
	private static ImageCacheHardStore cache;

	public static boolean isCacheAvailable() {
		final String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
	}

	public static boolean isCacheWritable() {
		final String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state);
	}

	@Override
	public void flush() {
		cache.deleteCache();
	}

	@Override
	public Bitmap get(String url) {
		if (isCacheAvailable()) {
			return cache.getCacheFile(url);
		}
		return null;
	}

	@Override
	public void put(String url, Bitmap bitmap) {
		if (isCacheWritable()) {
			cache.saveCacheFile(url, bitmap);
		}
	}

	@Override
	void resetPurgeTimer() {}

	@Override
	public void onLowMemoryReceived() { }
}