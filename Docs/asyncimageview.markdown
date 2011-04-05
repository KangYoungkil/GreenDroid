Using AsyncImageView
===================

AsyncImageView is a normal Android ImageView, however it allows you to easily and safely grab images from the internet.

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
