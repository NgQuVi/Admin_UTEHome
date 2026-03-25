package com.example.admin_utehome;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.ApartmentViewHolder> {

    private List<Apartment> apartmentList;

    public ApartmentAdapter(List<Apartment> apartmentList) {
        this.apartmentList = apartmentList;
    }

    @NonNull
    @Override
    public ApartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apartment, parent, false);
        return new ApartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentViewHolder holder, int position) {
        Apartment apt = apartmentList.get(position);
        holder.tvBuilding.setText(apt.getBuilding());
        holder.tvApartmentNumber.setText(apt.getNumber());
        holder.tvArea.setText("Diện tích: " + apt.getArea() + " m²");

        if (apt.isOccupied()) {
            holder.tvStatus.setText("Đang ở");
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_occupied);
            holder.tvStatus.setTextColor(Color.parseColor("#1F7343"));
            
            holder.ivOwnerIcon.setImageResource(R.drawable.ic_person_tiny);
            holder.tvOwner.setText("Chủ hộ: " + apt.getOwner());
            holder.tvOwner.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.text_primary));
        } else {
            holder.tvStatus.setText("Trống");
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_empty);
            holder.tvStatus.setTextColor(Color.parseColor("#4D5F7A"));
            
            holder.ivOwnerIcon.setImageResource(R.drawable.ic_person_off_tiny);
            holder.tvOwner.setText("Chưa có chủ hộ");
            holder.tvOwner.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.text_secondary));
        }
    }

    @Override
    public int getItemCount() {
        return apartmentList == null ? 0 : apartmentList.size();
    }

    static class ApartmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvBuilding, tvApartmentNumber, tvStatus, tvOwner, tvArea;
        ImageView ivOwnerIcon;

        public ApartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBuilding = itemView.findViewById(R.id.tvBuilding);
            tvApartmentNumber = itemView.findViewById(R.id.tvApartmentNumber);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvOwner = itemView.findViewById(R.id.tvOwner);
            tvArea = itemView.findViewById(R.id.tvArea);
            ivOwnerIcon = itemView.findViewById(R.id.ivOwnerIcon);
        }
    }
}
