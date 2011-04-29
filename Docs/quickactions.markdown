Making some QuickActions
========================

So you want to add some lovely QuickActions to your applications as seen here http://android-developers.blogspot.com/2010/05/twitter-for-android-closer-look-at.html (Pattern 6)?

Well, that's easy.

Create the instance
-------------------

You can either choose QuickActionGrid (for when you press that feed switching thingy) or QuickActionBar (holidng on a tweet):

	QuickActionGrid grid = new QuickActionGrid(this);

(this being an Activity)

Add the items
-------------

Until XML support is added, you have to do this in code like this:

	grid.addQuickAction(new QuickAction(this, R.drawable.cloud, R.string.everything));

Making it show up
-----------------

On the onClick or whatever event of the view you want it to pop-up on call this:

	grid.show(v);

(v being the view sent in the event)

Responding to clicking on an item
---------------------------------

Do something like this:

	grid.setOnQuickActionClickListener(new OnQuickActionClickListener(){
		public void onQuickActionClicked(QuickActionWidget widget,
				int position) {
			QuickAction x = grid.getQuickAction(position);
			String feed = null;
			switch(x.mTitleId){
			case R.string.everything:
			}
		}
	});
