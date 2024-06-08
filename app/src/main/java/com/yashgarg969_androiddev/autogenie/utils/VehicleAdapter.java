package com.yashgarg969_androiddev.autogenie.utils;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yashgarg969_androiddev.autogenie.R;

import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> {

    List<VehicleModel> vehiclesList;

    public VehicleAdapter(List<VehicleModel> vehiclesList) {
        this.vehiclesList = vehiclesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View myItem= inflater.inflate(R.layout.single_vehicle_item,parent,false);
        MyViewHolder viewHolder= new MyViewHolder(myItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            VehicleModel currvehicle= vehiclesList.get(position);
            holder.companyName.setText(currvehicle.getMake());
            holder.carModel.setText(currvehicle.getModel());
            holder.carPrice.setText(String.valueOf(currvehicle.getExShowroomPrice()));
    }

    @Override
    public int getItemCount() {
        return vehiclesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView companyName;
        TextView carModel;
        TextView carPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            companyName= itemView.findViewById(R.id.comapnyEt);
            carModel= itemView.findViewById(R.id.modelEt);
            carPrice= itemView.findViewById(R.id.priceEt);
        }
    }
}
