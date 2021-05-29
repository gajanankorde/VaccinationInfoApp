package com.gajanan.vaccinationinfoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class VaccinationRecyclerAdapter extends RecyclerView.Adapter<VaccinationRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<VaccinationModel> vaccinationArrayList;
    VaccinationRecyclerAdapter.ItemClickListener itemClickListener;

    public VaccinationRecyclerAdapter(Context context, ArrayList<VaccinationModel> vaccinationArrayList, VaccinationRecyclerAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.vaccinationArrayList = vaccinationArrayList;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public VaccinationRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vaccination, parent, false);
        // set the view's size, margins, paddings and layout parameters
        VaccinationRecyclerAdapter.MyViewHolder vh = new VaccinationRecyclerAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VaccinationRecyclerAdapter.MyViewHolder holder, final int position) {
        final VaccinationModel VaccinationModel= vaccinationArrayList.get(position);


        try {
            // set the data in items
            // set the data in items
            holder.tv_centerName.setText(VaccinationModel.getCenterName());
            holder.tv_centerAddress.setText(VaccinationModel.getCenterAddress());
            holder.tv_pinCode.setText(VaccinationModel.getPinCode());
            holder.tv_date.setText(VaccinationModel.getDate());
            holder.tv_fromTime.setText(VaccinationModel.getFromTime());
            holder.tv_toTime.setText(VaccinationModel.getToTime());
            holder.tv_vaccineName.setText(VaccinationModel.getVaccineName());
            holder.tv_age.setText(VaccinationModel.getMinAge());
            holder.tv_feeType.setText(VaccinationModel.getFeeType());
            holder.tv_fees.setText(VaccinationModel.getFee());



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return vaccinationArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        // init the item view's
        TextView tv_centerName,tv_centerAddress,tv_pinCode,tv_date,tv_fromTime,tv_toTime,tv_vaccineName,tv_age,tv_feeType,tv_fees;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            tv_centerName=itemView.findViewById(R.id.tv_centerName);
            tv_centerAddress=itemView.findViewById(R.id.tv_centerAddress);
            tv_pinCode=itemView.findViewById(R.id.tv_pinCode);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_fromTime=itemView.findViewById(R.id.tv_fromTime);
            tv_toTime=itemView.findViewById(R.id.tv_toTime);
            tv_vaccineName=itemView.findViewById(R.id.tv_vaccineName);
            tv_age=itemView.findViewById(R.id.tv_age);
            tv_feeType=itemView.findViewById(R.id.tv_feeType);
            tv_fees=itemView.findViewById(R.id.tv_fees);


            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null)
                        itemClickListener.onItemClick(v, getAdapterPosition());
                }
            });


        }
    }
}





