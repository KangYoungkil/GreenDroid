/**
 * 
 */
package greendroid.image;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Resizes Images
 * @author kennydude
 *
 */
public class ResizeImageProcessor implements ImageProcessor {
	Integer mWidth;
	Integer mHeight;
	
	public ResizeImageProcessor(Integer width, Integer height){
		mWidth = width;
		mHeight = height;
	}
	
	/* (non-Javadoc)
	 * @see greendroid.image.ImageProcessor#processImage(android.graphics.Bitmap)
	 */
	@Override
	public Bitmap processImage(Bitmap bitmap) {
		Point pt=calculateFitImage(bitmap,mWidth,mHeight,null); 
		Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 
                pt.x, pt.y, true); 
		return resizedBitmap;
	}
	public static Point calculateFitImage(Bitmap baseImage,int width,int height,Point receiver){
		if(receiver==null){
			receiver=new Point();
		}
		int dw=width;
		int dh=height;
		
		
		if(dw!=0 && dh!=0 ){
			double waspect=(double)dw/baseImage.getWidth();
			double haspect=(double)dh/baseImage.getHeight();
			if(waspect>haspect){//fit h
				dw=(int) (baseImage.getWidth()*haspect);
				
			}else{
				dh=(int)(baseImage.getHeight()*waspect);
			}
		}
		receiver.x=dw;
		receiver.y=dh;
		return receiver;
	}

}
