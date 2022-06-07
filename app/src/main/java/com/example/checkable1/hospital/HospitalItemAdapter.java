package com.example.checkable1.hospital;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkable1.R;

import java.util.ArrayList;

public class HospitalItemAdapter extends RecyclerView.Adapter<HospitalItemAdapter.ViewHolder> {
    private ArrayList<HospitalInfo> mHospitalList;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    @NonNull
    @Override
    public HospitalItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mHospitalList.get(position), position);
    }

    public void setHospitalList(ArrayList<HospitalInfo> list) {
        this.mHospitalList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHospitalList.size();
    }

    //Class
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hName;
        TextView hAddr;
        TextView hPhoneNumber;
        TextView hDistance;
        FrameLayout frameLayout;
        LinearLayout linearLayout;
        ImageView callImgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            frameLayout = (FrameLayout) itemView.findViewById(R.id.frame_recyclerItem);
            hName = (TextView) itemView.findViewById(R.id.text_recyclerItem_hName);
            hAddr = (TextView) itemView.findViewById(R.id.text_recyclerItem_hAddr);
            hPhoneNumber = (TextView) itemView.findViewById(R.id.text_recyclerItem_hPhoneNumber);
            hDistance = (TextView) itemView.findViewById(R.id.text_recyclerItem_distance);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_recyclerItem);
            callImgView = (ImageView) itemView.findViewById(R.id.imgView_recyclerItem_call);

            linearLayout.bringToFront();
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });

        }

        void onBind(HospitalInfo item, int position) {
            if (position % 2 == 1) {
                frameLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F3F3F3")));
            } else {
                frameLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#CEE6EF")));
            }
            hName.setText(item.hosName);
            hAddr.setText(item.hosAddress);
            hPhoneNumber.setText("전화번호: " + item.hosPhoneNumber);
            hDistance.setText(item.hosDistance_string + " | ");
        }
    }
}
