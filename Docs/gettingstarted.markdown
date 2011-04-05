Getting started with GreenDroid
============================

To get started with GreenDroid, it's really easy.

1. Download either a copy from GitHub, or check it out by using
 > `git clone git://github.com/kennydude/GreenDroid.git`
2. Open Eclipse (if you use it) and go to File -> Import and import the project in the GreenDroid folder
3. Right click your application project and select properties
4. Select Android
5. Press 'Choose Libraries'
6. Select GreenDroid

Now, change your activities to use GDActivity and they'll get an action bar if you change:

> `setContentView( ... )`

to:

> `setActionBarContentView( ... )`

Notes
-----

* If you edit GreenDroid to make those edits build in Eclipse, you need to right click your application project, select Refresh and then Clean projects from the build menu

