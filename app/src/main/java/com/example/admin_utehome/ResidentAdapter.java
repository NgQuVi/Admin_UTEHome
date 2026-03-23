package com.example.admin_utehome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ResidentAdapter extends RecyclerView.Adapter<ResidentAdapter.ResidentViewHolder> {

    private List<Resident> residentList;

    public ResidentAdapter(List<Resident> residentList) {
        this.residentList = residentList;
    }

    @NonNull
    @Override
    public ResidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resident, parent, false);
        return new ResidentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResidentViewHolder holder, int position) {
        Resident resident = residentList.get(position);
        holder.tvName.setText(resident.getName());
        holder.tvRoomInfo.setText(resident.getRoomInfo());
        
        holder.itemView.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(v.getContext(), ResidentDetailActivity.class);
            intent.putExtra("RESIDENT_NAME", resident.getName());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return residentList == null ? 0 : residentList.size();
    }

    static class ResidentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvRoomInfo;

        public ResidentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvRoomInfo = itemView.findViewById(R.id.tvRoomInfo);
        }
    }
}
