package com.example.admin_utehome;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<Notification> items;

    public NotificationAdapter(List<Notification> items) {
        this.items = items;
    }

    public void updateList(List<Notification> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notif = items.get(position);
        holder.tvTitle.setText(notif.getTitle());
        holder.tvTime.setText(notif.getTime());
        holder.tvRecipient.setText(notif.getRecipient());

        // Set tag style berdasarkan type
        switch (notif.getType()) {
            case Notification.TYPE_MAINTENANCE:
                holder.tvTag.setText("BẢO TRÌ");
                holder.tvTag.setBackgroundResource(R.drawable.bg_tag_maintenance);
                holder.tvTag.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_blue_dark, null));
                holder.ivRecipientIcon.setImageResource(R.drawable.ic_person_tiny);
                break;
            case Notification.TYPE_EVENT:
                holder.tvTag.setText("SỰ KIỆN");
                holder.tvTag.setBackgroundResource(R.drawable.bg_tag_event);
                holder.tvTag.setTextColor(0xFF7C4DFF);
                holder.ivRecipientIcon.setImageResource(R.drawable.ic_building_24);
                break;
            case Notification.TYPE_URGENT:
                holder.tvTag.setText("THÔNG BÁO KHẨN");
                holder.tvTag.setBackgroundResource(R.drawable.bg_tag_urgent);
                holder.tvTag.setTextColor(0xFFFF6D00);
                holder.ivRecipientIcon.setImageResource(R.drawable.ic_directions_car_24);
                break;
        }

        // Click mở màn hình chi tiết
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), NotificationDetailActivity.class);
            intent.putExtra("notif_type", notif.getType());
            intent.putExtra("notif_title", notif.getTitle());
            intent.putExtra("notif_time", notif.getTime());
            intent.putExtra("notif_recipient", notif.getRecipient());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTag, tvTime, tvTitle, tvRecipient;
        ImageView ivRecipientIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tvTag);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRecipient = itemView.findViewById(R.id.tvRecipient);
            ivRecipientIcon = itemView.findViewById(R.id.ivRecipientIcon);
        }
    }
}
