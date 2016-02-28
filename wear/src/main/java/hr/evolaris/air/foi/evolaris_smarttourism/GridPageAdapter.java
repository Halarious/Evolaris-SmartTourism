package hr.evolaris.air.foi.evolaris_smarttourism;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import java.util.List;

import hr.evolaris.air.foi.evolaris_smarttourism.db.NotificationMessage;

public class GridPageAdapter
    extends FragmentGridPagerAdapter
{

    private final Context mContext;
    private List mRows;
    static final int[] BG_IMAGES = new int[] {R.drawable.ic_doge, R.drawable.doge};
    private final Page[][] PAGES = {{new Page(NotificationMessage.title,R.string.hello_square,R.drawable.card_background), new Page(NotificationMessage.title,R.string.hello_round,R.drawable.card_background)},
                                    {new Page(NotificationMessage.title,R.string.hello_round,R.drawable.card_background)}};

    private static class Page
    {
        String titleResource;
        int textResource;
        int iconResource;

        public Page(String t, int tx, int i)
        {
            this.titleResource = t;
            this.textResource = tx;
            this.iconResource = i;
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
        return 1;//PAGES.length;
    }

    @Override
    public int getColumnCount(int rowNum)
    {
        return 1;//PAGES[rowNum].length;
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
        String title = NotificationMessage.title;//page.titleResource != 0 ? mContext.getString(page.titleResource): null;
        String text = NotificationMessage.message;//page.textResource != 0 ? mContext.getString(page.textResource): null;

        CardFragment cardFragment = CardFragment.create(title, text, page.iconResource);
        cardFragment.setCardMargins(10, 120, 10, 0);
        cardFragment.setCardGravity(0);
        cardFragment.setExpansionEnabled(true);
        cardFragment.setExpansionDirection(1);
        cardFragment.setExpansionFactor(10);

        return cardFragment;
    }

}
