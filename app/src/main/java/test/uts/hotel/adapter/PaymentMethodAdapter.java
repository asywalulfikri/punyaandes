package test.uts.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import test.uts.hotel.Model.Bank;
import test.uts.hotel.Model.PodImage;
import test.uts.hotel.R;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private Context context;
    int [] imageId;
    private int checkedPosition = -1;
    private ArrayList<Bank> employees;
    ViewHolder holder1;
    public interface DeletedItemListener{
        void onDeleted(PodImage model, int position, String type);
    }

    public PaymentMethodAdapter(Context mainActivity, ArrayList<Bank> prgmNameList, int[] prgmImages) {
        this.employees=prgmNameList;
        this.context=mainActivity;
        imageId=prgmImages;
    }

    public void setEmployees(ArrayList<Bank> employees) {
        this.employees = new ArrayList<>();
        this.employees = employees;
        notifyDataSetChanged();
    }


    public void removeCheck(){
        checkedPosition = -1;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_payment_method, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder1 = holder;
        final Bank bank = employees.get(position);
        holder.tv_payment.setText(bank.getName());
        Picasso.get().load(imageId[position]).into(holder.iv_payment);

        if (checkedPosition == -1) {
            holder.iv_check.setVisibility(View.GONE);
        } else {
            if (checkedPosition == position) {
                holder.iv_check.setVisibility(View.VISIBLE);
            } else {
                holder.iv_check.setVisibility(View.GONE);
            }
        }
        holder.ll_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkedPosition != position) {
                    notifyItemChanged(checkedPosition);
                    checkedPosition = position;
                    holder.iv_check.setVisibility(View.VISIBLE);
                    bank.setChecked(true);
                }else {

                    checkedPosition = -1;
                    holder.iv_check.setVisibility(View.GONE);
                    bank.setChecked(false);
                }
                onItemClickListener.OnActionClickQuestion(view, position);

            }

        });

    }


    @Override
    public int getItemCount() {
        return employees.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_payment;
        ImageView iv_payment;
        LinearLayout ll_payment;
        RelativeLayout virtual_bri;
        ImageView iv_check;

        public ViewHolder(View view) {
            super(view);
            tv_payment= view.findViewById(R.id.tv_payment);
            iv_payment= view.findViewById(R.id.iv_payment);
            ll_payment = view.findViewById(R.id.ll_payment);
            iv_check = view.findViewById(R.id.iv_check);
          //  virtual_bri = view.findViewById(R.id.virtual_bri);


        }
    }

    public void actionQuestion(OnItemClickListener actionQuestion) {
        onItemClickListener = actionQuestion;
    }public interface OnItemClickListener {
        void OnActionClickQuestion(View view, int position);
    }
}

