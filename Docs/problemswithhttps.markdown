Problems with using HTTPS in Android
====================================

If you get errors to do with HTTPS certificates (especially in Android 1.6) not being valid and it's driving you up the wall, GreenDroid has a very easy peasy solution for you.

It's really simple, just change this:

	HttpClient client = new DefaultHttpClient();

to this:

	HttpClient client = HttpClientHelper.getTolerantClient();

Done!
