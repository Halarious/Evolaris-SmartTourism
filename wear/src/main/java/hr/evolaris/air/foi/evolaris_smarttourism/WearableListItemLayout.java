package hr.evolaris.air.foi.evolaris_smarttourism;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WearableListItemLayout
        extends LinearLayout
        implements WearableListView.OnCenterProximityListener, WearableListView.OnClickListener
{
    private ImageView mCircle;
    private TextView mName;

    private final float mFadedTextAlpha;
    private final int mFadedCircleColor;
    private final int mChosenCircleColor;

    public WearableListItemLayout(Context context)
    {
        this(context, null);
    }

    public WearableListItemLayout(Context context, AttributeSet attributes)
    {
        this(context, attributes, 0);
    }

    public WearableListItemLayout(Context context, AttributeSet attributes, int defStyles)
    {
        super(context, attributes, defStyles);

        mFadedTextAlpha = 1f;//getResources().getInteger(R.integer.google_play_services_version)/100f;
        mFadedCircleColor = getResources().getColor(R.color.grey);
        mChosenCircleColor = getResources().getColor(R.color.blue);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

        mCircle = (ImageView) findViewById(R.id.circle);
        mName = (TextView) findViewById(R.id.name);
    }

    @Override
    public void onCenterPosition(boolean animate)
    {
        mName.setAlpha(1f);
        ((GradientDrawable)mCircle.getDrawable()).setColor(mChosenCircleColor);
    }

    @Override
    public void onNonCenterPosition(boolean animate)
    {
        mName.setAlpha(mFadedTextAlpha);
        ((GradientDrawable)mCircle.getDrawable()).setColor(mFadedCircleColor);
    }

    @Override
    public void onClick(View v)
    {

    }
}
