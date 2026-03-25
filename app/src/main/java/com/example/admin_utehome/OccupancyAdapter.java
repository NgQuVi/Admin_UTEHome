package com.example.admin_utehome;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OccupancyAdapter extends RecyclerView.Adapter<OccupancyAdapter.OccupancyViewHolder> {

    private List<Apartment> apartmentList;

    public OccupancyAdapter(List<Apartment> list) {
        this.apartmentList = list;
    }

    public void updateList(List<Apartment> list) {
        this.apartmentList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OccupancyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_occupancy_apartment, parent, false);
        return new OccupancyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OccupancyViewHolder holder, int position) {
        Apartment apt = apartmentList.get(position);
        holder.tvBuilding.setText(apt.getBuilding());
        holder.tvApartmentNumber.setText(apt.getNumber());
        holder.tvArea.setText("Diện tích: " + apt.getArea() + " m²");

        if (apt.getOwner() == null || apt.getOwner().isEmpty()) {
            holder.tvOwner.setText("Chưa có chủ hộ");
            holder.tvOwner.setTextColor(Color.parseColor("#8E8E8E"));
        } else {
            holder.tvOwner.setText("Chủ hộ: " + apt.getOwner());
            holder.tvOwner.setTextColor(Color.parseColor("#141414"));
        }

        if (apt.isOccupied()) {
            holder.tvStatus.setText("Đang ở");
            holder.tvStatus.setTextColor(Color.parseColor("#1F7343"));
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_occupied);
        } else {
            holder.tvStatus.setText("Trống");
            holder.tvStatus.setTextColor(Color.parseColor("#445A74"));
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_empty);
        }

        android.view.View.OnClickListener listener = v -> {
            Context context = v.getContext();
            android.content.Intent intent = new android.content.Intent(context, ApartmentDetailActivity.class);
            context.startActivity(intent);
        };
        
        holder.itemView.setOnClickListener(listener);
        holder.btnDetails.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return apartmentList != null ? apartmentList.size() : 0;
    }

    public static class OccupancyViewHolder extends RecyclerView.ViewHolder {
        TextView tvBuilding, tvApartmentNumber, tvStatus, tvOwner, tvArea, btnDetails;

        public OccupancyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBuilding = itemView.findViewById(R.id.tvBuilding);
            tvApartmentNumber = itemView.findViewById(R.id.tvApartmentNumber);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvOwner = itemView.findViewById(R.id.tvOwner);
            tvArea = itemView.findViewById(R.id.tvArea);
            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }
}
