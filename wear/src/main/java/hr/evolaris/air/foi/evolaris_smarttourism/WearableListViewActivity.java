package hr.evolaris.air.foi.evolaris_smarttourism;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.support.wearable.view.WearableListView;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class WearableListViewActivity
        extends Activity
        implements WearableListView.ClickListener
{
    private static final String[] listElements = {"List item 1", "List item 2", "List item 3"};
    private DismissOverlayView mDismissOverlay;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wearable_list_activity);

        WearableListView listView = (WearableListView)findViewById(R.id.wearable_list);
        listView.setAdapter(new WearableListViewAdapter(this, listElements));
        listView.setClickListener(this);

        /*mDismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent ev) {
                mDismissOverlay.show();
            }

        });*/
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder)
    {

    }

    @Override
    public void onTopEmptyRegionClick()
    {

    }
}
