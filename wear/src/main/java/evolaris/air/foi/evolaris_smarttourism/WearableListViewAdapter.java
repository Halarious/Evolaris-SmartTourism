package evolaris.air.foi.evolaris_smarttourism;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public final class WearableListViewAdapter
        extends WearableListView.Adapter
{
    private String[] mDataSet;
    private final Context mContext;
    private final LayoutInflater mInflater;

    WearableListViewAdapter(Context context, String[] dataSet)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDataSet = dataSet;
    }

    public static class ItemViewHolder
            extends WearableListView.ViewHolder
    {
        private TextView textView;
        public ItemViewHolder(View itemView)
        {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.name);
        }
    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ItemViewHolder(mInflater.inflate(R.layout.list_item, null));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder, int position)
    {
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        TextView view = itemHolder.textView;

        view.setText(mDataSet[position]);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount()
    {
        return mDataSet.length;
    }
}

