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

public abstract class ImageCache implements OnLowMemoryListener {

	public static ImageCache from(Context context) {
		return GDUtils.getImageCache(context);
	}

	public abstract Bitmap get(String url);

	public abstract void put(String url, Bitmap bitmap);

	public abstract void flush();
	
	abstract void resetPurgeTimer();

}
