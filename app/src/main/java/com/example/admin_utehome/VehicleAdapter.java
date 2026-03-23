package com.example.admin_utehome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    private List<Vehicle> vehicleList;

    public VehicleAdapter(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public void updateList(List<Vehicle> newList) {
        this.vehicleList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);

        holder.tvLicensePlate.setText(vehicle.getLicensePlate());
        holder.tvVehicleDesc.setText(vehicle.getDescription());
        holder.tvOwnerInfo.setText(vehicle.getOwnerName() + " - " + vehicle.getApartmentCode());
        holder.tvStatus.setText(vehicle.getStatus());

        if (Vehicle.TYPE_CAR.equals(vehicle.getType())) {
            holder.ivVehicleIcon.setImageResource(R.drawable.ic_directions_car_24);
        } else {
            holder.ivVehicleIcon.setImageResource(R.drawable.ic_motorcycle_24);
        }

        if (Vehicle.STATUS_APPROVED.equals(vehicle.getStatus())) {
            holder.tvStatus.setBackgroundResource(R.drawable.bg_tag_approved);
            holder.tvStatus.setTextColor(0xFF28A745); // Green
        } else {
            holder.tvStatus.setBackgroundResource(R.drawable.bg_tag_pending);
            holder.tvStatus.setTextColor(0xFFFFA000); // Amber/Orange
        }

        holder.itemView.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, VehicleDetailActivity.class);
            intent.putExtra("plate", vehicle.getLicensePlate());
            intent.putExtra("desc", vehicle.getDescription());
            intent.putExtra("owner", vehicle.getOwnerName());
            intent.putExtra("apt", vehicle.getApartmentCode());
            intent.putExtra("type", vehicle.getType());
            intent.putExtra("status", vehicle.getStatus());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return vehicleList == null ? 0 : vehicleList.size();
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {
        ImageView ivVehicleIcon;
        TextView tvLicensePlate;
        TextView tvVehicleDesc;
        TextView tvStatus;
        TextView tvOwnerInfo;

        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivVehicleIcon = itemView.findViewById(R.id.ivVehicleIcon);
            tvLicensePlate = itemView.findViewById(R.id.tvLicensePlate);
            tvVehicleDesc = itemView.findViewById(R.id.tvVehicleDesc);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvOwnerInfo = itemView.findViewById(R.id.tvOwnerInfo);
        }
    }
}
