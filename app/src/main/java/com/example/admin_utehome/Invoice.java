package com.example.admin_utehome;

public class Invoice {
    public static final int STATUS_UNPAID = 0;
    public static final int STATUS_PAID = 1;
    public static final int STATUS_OVERDUE = 2;

    private String apartment;
    private String invoiceId;
    private String dueDate;
    private int status;
    private String amount;

    public Invoice(String apartment, String invoiceId, String dueDate, int status, String amount) {
        this.apartment = apartment;
        this.invoiceId = invoiceId;
        this.dueDate = dueDate;
        this.status = status;
        this.amount = amount;
    }

    public String getApartment() { return apartment; }
    public String getInvoiceId() { return invoiceId; }
    public String getDueDate() { return dueDate; }
    public int getStatus() { return status; }
    public String getAmount() { return amount; }
}
