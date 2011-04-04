/**
 * 
 */
package greendroid.image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/**
 * Adds rounded corners to images
 * @author kennydude
 *
 */
public class CornersImageProcessor implements ImageProcessor {

	/* (non-Javadoc)
	 * @see greendroid.image.ImageProcessor#processImage(android.graphics.Bitmap)
	 */
	@Override
	public Bitmap processImage(Bitmap input) {
		try{
    		/* Thanks: http://stackoverflow.com/questions/1705239/how-should-i-give-images-rounded-corners-in-android */
    		int w = input.getWidth(), h = input.getHeight();
    		Bitmap rounder = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
    		Canvas canvas = new Canvas(rounder);
    		Paint xferPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    		xferPaint.setColor(Color.RED);
    		canvas.drawRoundRect(new RectF(0,0,w,h), 20.0f, 20.0f, xferPaint);
    		xferPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    		canvas.drawBitmap(input, 0,0, xferPaint);
    		// canvas.drawBitmap(rounder, 0, 0, xferPaint);
    		return rounder;
		} catch(Exception e){
			return input;
		}
	}

}
