package com.example.admin_utehome;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {

    private List<Invoice> invoiceList;

    public InvoiceAdapter(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public void updateList(List<Invoice> newList) {
        this.invoiceList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_invoice, parent, false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        Invoice invoice = invoiceList.get(position);
        holder.bind(invoice);
    }

    @Override
    public int getItemCount() {
        return invoiceList == null ? 0 : invoiceList.size();
    }

    class InvoiceViewHolder extends RecyclerView.ViewHolder {
        TextView tvApartment, tvInvoiceId, tvDueDate, tvStatus, tvAmount;

        public InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvApartment = itemView.findViewById(R.id.tvApartment);
            tvInvoiceId = itemView.findViewById(R.id.tvInvoiceId);
            tvDueDate = itemView.findViewById(R.id.tvDueDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvAmount = itemView.findViewById(R.id.tvAmount);
        }

        public void bind(Invoice invoice) {
            tvApartment.setText(invoice.getApartment());
            tvInvoiceId.setText(invoice.getInvoiceId());
            tvDueDate.setText(invoice.getDueDate());
            tvAmount.setText(invoice.getAmount());

            // Resetting colors
            int blackColor = itemView.getContext().getResources().getColor(android.R.color.black, null);
            tvAmount.setTextColor(blackColor);

            if (invoice.getStatus() == Invoice.STATUS_UNPAID) {
                tvStatus.setText("CHƯA THANH TOÁN");
                tvStatus.setBackgroundResource(R.drawable.bg_tag_unpaid);
                int orangeColor = itemView.getContext().getResources().getColor(R.color.brand_orange, null);
                tvStatus.setTextColor(orangeColor);
                tvAmount.setTextColor(orangeColor);
            } else if (invoice.getStatus() == Invoice.STATUS_PAID) {
                tvStatus.setText("ĐÃ THANH TOÁN");
                tvStatus.setBackgroundResource(R.drawable.bg_tag_paid);
                tvStatus.setTextColor(0xFF0F9D58); // Green
            } else if (invoice.getStatus() == Invoice.STATUS_OVERDUE) {
                tvStatus.setText("QUÁ HẠN");
                tvStatus.setBackgroundResource(R.drawable.bg_tag_overdue);
                tvStatus.setTextColor(0xFFD50000); // Red
                tvAmount.setTextColor(0xFFD50000); // Red
            }

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), InvoiceDetailActivity.class);
                intent.putExtra("invoiceId", invoice.getInvoiceId());
                v.getContext().startActivity(intent);
            });
        }
    }
}
