package greendroid.util;

import greendroid.app.GDApplication;

import java.util.Date;

import com.cyrilmottier.android.greendroid.R;

import android.util.Log;

/**
 * Various String utils
 * @author kennydude
 *
 */
public class StringUtils {
	
	/**
	 * Gets a resource by the Id
	 * @param resource Integer resource. Generally in the format R.string.x
	 * @return String
	 */
	public static String getResource(int resource){
		return GDApplication.getAppResources().getString(resource);
	}
	
	/**
	 * Parses a date to be be pretty like "x ago"
	 * Based on http://ejohn.org/files/pretty.js
	 * @param input Date to parse
	 * @return Pretty Date
	 */
	public static String prettyDate(Date input){
		Date now = new Date();
		int diff = (int) (now.getTime() - input.getTime()) / 1000;
		int day_diff = (int) Math.floor(diff / 86400);
		Log.d("dwsd", "diff: " + diff + " " + day_diff); 
		if(day_diff == 0){
			if(diff < 60)
				return getResource(R.string.gd_pretty_now);
			if(diff < 120)
				return getResource(R.string.gd_pretty_minute_ago);
			if(diff < 3600)
				return getResource(R.string.gd_pretty_minutes_ago).replace("{x}", (int)Math.floor(diff/60) + "");
			if(diff < 7200)
				return getResource(R.string.gd_pretty_hour_ago);
			if(diff < 86400)
				return getResource(R.string.gd_pretty_hours_ago).replace("{x}", (int)Math.floor(diff/3600) + "");
		} if(day_diff == 1)
			return getResource(R.string.gd_pretty_yesterday);
		if(day_diff < 7)
			return getResource(R.string.gd_pretty_days_ago).replace("{x}", day_diff + "");
		if(day_diff < 31)
			return getResource(R.string.gd_pretty_weeks_ago).replace("{x}", (int)Math.ceil(day_diff/7) + "");
		return input.toLocaleString();
	}
}
