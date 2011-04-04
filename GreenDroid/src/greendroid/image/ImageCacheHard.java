package greendroid.image;

/**
 * This is a much edited version of the code produced by Sergi Juanola
 * available at http://www.flexjockey.com/2011/03/create-a-pretty-simple-cache-for-android/
 * 
 * @author Chris Banes
 */
import java.io.InputStream;

import android.graphics.Bitmap;
import android.os.Environment;

public class ImageCacheHard {

	// Singleton of the actual SD Cache
	private static ImageCacheHardStore cache = ImageCacheHardStore.getInstance();

	public static boolean isCacheAvailable() {
		final String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
	}

	public static boolean isCacheWritable() {
		final String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state);
	}

	public static void saveCacheFile(String cacheUri, InputStream image) {
		if (isCacheWritable()) {
			cache.saveCacheFile(cacheUri, image);
		}
	}

	/**
	 * @param imageUri
	 *            - URI of the Image on the Internet
	 * @return Bitmap associated with URI if available, else null
	 */
	public static Bitmap getCacheFile(String imageUri) {
		if (isCacheAvailable()) {
			return cache.getCacheFile(imageUri);
		}
		return null;
	}

	/**
	 * Clear SD Card cache.
	 * 
	 * @return int - number of files deleted
	 */
	public static int clearCache() {
		return cache.deleteCache();
	}
}