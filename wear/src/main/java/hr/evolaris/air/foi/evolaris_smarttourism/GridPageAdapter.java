package hr.evolaris.air.foi.evolaris_smarttourism;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import java.util.List;

public class GridPageAdapter
    extends FragmentGridPagerAdapter
{

    private final Context mContext;
    private List mRows;
    static final int[] BG_IMAGES = new int[] {R.drawable.ic_doge, R.drawable.doge};
    private final Page[][] PAGES = {{new Page(R.string.app_name,R.string.hello_square,R.drawable.card_background), new Page(R.string.app_name,R.string.hello_round,R.drawable.card_background)},
                                    {new Page(R.string.app_name,R.string.hello_round,R.drawable.card_background)}};

    private static class Page
    {
        int titleRes;
        int textRes;
        int iconRes;

        public Page(int t, int tx, int i)
        {
            this.titleRes = t;
            this.textRes = tx;
            this.iconRes = i;
        }
    }

    public GridPageAdapter(Context context, FragmentManager fragmentManager)
    {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public int getRowCount()
    {
        return PAGES.length;
    }

    @Override
    public int getColumnCount(int rowNum)
    {
        return PAGES[rowNum].length;
    }

    @Override
    public Drawable getBackgroundForPage(int row, int column) {
        if(row == 0 && column == 0)
        {
            return mContext.getResources().getDrawable(R.drawable.ic_full_cancel);
        }
        else
        {
            return GridPageAdapter.BACKGROUND_NONE;
        }
    }

    @Override
    public Drawable getBackgroundForRow(int row) {
        return mContext.getResources().
                getDrawable(BG_IMAGES[row % BG_IMAGES.length]);
    }

    @Override
    public Fragment getFragment(int row, int column) {
        Page page = PAGES[row][column];
        String title = page.titleRes != 0 ? mContext.getString(page.titleRes): null;
        String text = page.textRes != 0 ? mContext.getString(page.textRes): null;

        CardFragment cardFragment = CardFragment.create(title, text, page.iconRes);
        cardFragment.setCardGravity(0);
        cardFragment.setExpansionEnabled(false);
        cardFragment.setExpansionDirection(0);
        cardFragment.setExpansionFactor(0);

        return cardFragment;
    }

}
