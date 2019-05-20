package com.volskyioleh.test.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volskyioleh.test.R;
import com.volskyioleh.test.api.models.ItemModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemsHolder> {

    private final Context mContext;
    private List<ItemModel> mArticles;

    public ListAdapter(List<ItemModel> itemsList, Context context) {
        mArticles = itemsList;
        mContext = context;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        return new ItemsHolder(view);
    }

    public void setItems(List<ItemModel> itemsList) {
        mArticles = itemsList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder itemsHolder, int position) {
        ItemModel model = mArticles.get(position);
        itemsHolder.actualTime.setText( getDate(model.actualTime));
        itemsHolder.commentTv.setText(model.comment);
        itemsHolder.pairTv.setText(model.pair);
        itemsHolder.cmdTv.setText(String.valueOf(model.cmd));
        itemsHolder.tradingTv.setText(String.valueOf(model.tradingSystem));
        itemsHolder.periodTv.setText(model.period);
        itemsHolder.priceTv.setText(String.valueOf(model.price));
        itemsHolder.slTv.setText(String.valueOf(model.sl));
        itemsHolder.tpTv.setText(String.valueOf(model.tp));
    }

    public String getDate(long milliSeconds) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class ItemsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.actual_time_tv)
        TextView actualTime;

        @BindView(R.id.comment_tv)
        TextView commentTv;


        @BindView(R.id.pair_tv)
        TextView pairTv;

        @BindView(R.id.cmd_tv)
        TextView cmdTv;

        @BindView(R.id.tradingSystem_TV)
        TextView tradingTv;

        @BindView(R.id.periodTv)
        TextView periodTv;

        @BindView(R.id.price_tv)
        TextView priceTv;
        @BindView(R.id.sl_tv)
        TextView slTv;
        @BindView(R.id.tp_tv)
        TextView tpTv;

        ItemsHolder(View itemView) {
            super(itemView);
            //       mItemImage = itemView.findViewById(R.id.);
            ButterKnife.bind(this, itemView);
        }
    }
}
