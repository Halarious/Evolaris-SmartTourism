package hr.evolaris.air.foi.evolaris_smarttourism;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;

public class RecommendationActivity
        extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity_main);

        /*mDismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent ev) {
                mDismissOverlay.show();
            }

        }); Voda 126 */

        final GridViewPager gridViewPager = (GridViewPager)findViewById(R.id.pager);
        gridViewPager.setAdapter(new GridPageAdapter(this, getFragmentManager()));

    }
}
