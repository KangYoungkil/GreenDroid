package greendroid.image;

import greendroid.util.GDUtils;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

/**
 * This is an Automated task which is scheduled to run and remove the hard cache
 * Automagically, reducing app size without bothering the user one bit! :)
 * @author kennydude
 *
 */
public class ClearImageCacheTask extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		Log.d("GD", "ClearImageCacheTask called");
		Intent clear = new Intent(context, TheService.class);
		context.startService(clear);
	}

	public static class TheService extends Service{
		public class Task extends AsyncTask<Object, Object, Object>{

			@Override
			protected Object doInBackground(Object... arg0) {
				GDUtils.getImageCache(TheService.this).cleanupCache();
				return null;
			}
			
		}
		
		
		@Override
		public IBinder onBind(Intent intent) {
			return null;
		}
		
		@Override
		public void onStart(Intent intent, int startId) {
			new Task().execute();
		}
		
	}
}
