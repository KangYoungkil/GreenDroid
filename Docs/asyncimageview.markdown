Using AsyncImageView
===================

AsyncImageView is a normal Android ImageView, however it allows you to easily and safely grab images from the internet.

AndroidManifest.xml Changes
---------------------------

If you're using the kennydude version of GreenDroid then GreenDroid contains an automatic cleaner which dumps the rubbish out of cache which is old. However, you need to add this to your AndroidManifest.xml:

> `<!-- GreenDroid -->`

> `<receiver
	android:name="greendroid.image.ClearImageCacheTask" />`

> `<service
	android:name="greendroid.image.ClearImageCacheTask$TheService" />`

(Basially, we use AlarmManager to call the task which needs a service otherwise Android will force-close your app)

How to change from an ImageView
-------------------------------

In your xml change this:
> `<ImageView ...></ImageView>`

to:

> `<greendroid.widget.AsyncImageView ...></greendroid.widget.AsyncImageView>`

Also, to keep your User Experience up to scratch, *ensure* that you add this to the XML:

> `xmlns:greendroid="http://schemas.android.com/apk/res/com.cyrilmottier.android.gdcatalog"
greendroid:defaultSrc=" Insert a drawable to show while the image is loading here! "`

In you're code you'll want to change how the drawables to set. Simply get change your code to get a Url of the image you want and then set it with:

> `asyncImageView.setUrl( url );`

ImageProcessors
---------------

Image Processors allow you to do some crazy things to images before they're cached. They're pretty cool and there's a couple ready for you to use in GreenDroid.

To use one:

> `asyncImageView.setImageProcessor( processor )`

Also, a really really handy Image Processor is MultipleImageProcessors. This is a magic processor which you can chain multiple processors onto like this:

> `asyncImageView.setImageProcessor( new MultipleImageProcessors( one, two, three, four... )`

Built into GreenDroid is CornersImageProcessor and ResizeImageProcessor.

CornersImageProcessor puts some nice rounded corners onto your image, which makes them look so much better than just pointy. Use it like this:

> `asynImageView.setImageProcessor( new CornersImageProcessor() )`

ResizeImageProcessor basically well, resizes images, however not so they get squished. It resizes them so they will hit a maximum size. Just use android:gravity to ensure the final result is where you want it. It works like this:

> `asyncImageView.setImageProcessor( new ResizeImageProcessor( maxWidth, maxHeight ) )`

Caching the Image Size
----------------------

** This feature is availble only for those people using kennydude's version of GreenDroid (or forked from there) **

If you use AsyncImageView in normal views, this should be fine. However, if you have images which can change size (for example from the website Dailybooth or Facebook) this can become annoying for your users. Users will have to put up with a bumping effect as the views change size. This can also cause issues with slow phones.

However, to make this work in a ListView, it takes a little bit more work. You should already be using an ArrayAdapter at this point, otherwise it won't work!

To start with, add two integers to your data item (you don't need to set them) and call them 'width' and 'height' or whatever. Next, on the "getView" method of your array adapter add something like this:

	x.setOnViewSizeListener(new OnViewSizeListener(){
		@Override
		public void onViewSizeCached(Integer width, Integer height) {
			DailyboothItem y = (DailyboothItem)DailyboothFeedAdapter.this.getItem(i);
			y.width = width;
			y.height = height;
			DailyboothView.this.images_to_show.set(i, y); // update
		}
	});
	if(xy.width != null){ // cached
	 x.setCachedViewSize(xy.width, xy.height);
	}

(This is saying that you're adapter is called DailyboothFeedAdapter, i is your position, x is your AsyncView and xy is your item. Change this depending on your application)
