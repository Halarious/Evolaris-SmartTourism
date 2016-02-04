package hr.evolaris.air.foi.evolaris_smarttourism;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DelayedConfirmationView;
import android.support.wearable.view.DismissOverlayView;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WearableListView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity
        extends     Activity
        implements  DelayedConfirmationView.DelayedConfirmationListener
{

    private DelayedConfirmationView mDelayedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.round_activity_main);

        /*mDelayedView =
                (DelayedConfirmationView) findViewById(R.id.delayed_confirm);
        mDelayedView.setListener(this);*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);//mDetector.onTouchEvent(event) ||
    }

    @Override
    public void onTimerFinished(View view) {

    }

    @Override
    public void onTimerSelected(View view) {

    }
}
