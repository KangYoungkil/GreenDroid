package greendroid.image;

import android.graphics.Bitmap;

/**
 * Allows multiple image processors to be chained
 * @author kennydude
 *
 */
public class MultipleImageProcessors implements ImageProcessor {
	ImageProcessor[] mProcessors;
	
	public MultipleImageProcessors(ImageProcessor... processors){
		mProcessors = processors;
	}
	
	@Override
	public Bitmap processImage(Bitmap bitmap) {
		for(ImageProcessor processor : mProcessors){
			bitmap = processor.processImage(bitmap);
		}
		return bitmap;
	}

}
