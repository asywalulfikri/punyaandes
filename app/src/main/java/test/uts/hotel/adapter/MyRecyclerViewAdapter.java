package test.uts.hotel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import test.uts.hotel.DetailKamarActivity;
import test.uts.hotel.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private String[] maintitle;
    private String[] subtitle;
    private String[] description;
    private Integer[] imgid;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, String[] maintitle, String[] subtitle, Integer[] imgid,String[] description) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
        this.imgid = imgid;
        this.description = description;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_hotel, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.titleText.setText(maintitle[position]);
        holder.imageView.setImageResource(imgid[position]);
        holder.subtitleText.setText(subtitle[position]);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailKamarActivity.class);
                intent.putExtra("title",maintitle[position]);
                intent.putExtra("subtitle",subtitle[position]);
                intent.putExtra("description",description[position]);
                intent.putExtra("icon",imgid[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return maintitle.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleText,subtitleText;
        ImageView imageView;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            subtitleText = itemView.findViewById(R.id.subtitle);
            imageView = itemView.findViewById(R.id.icon);
            cardView = itemView.findViewById(R.id.cvItem);

            //itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
   /* String getItem(int id) {
        //return mData.get(id);
    }*/

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
