package dojo.supermarket.model;

import dojo.supermarket.ReceiptBuilder;

public interface Discount {
    void addReceiptSection(ReceiptBuilder receiptBuilder);

    double getDiscountAmount();
}
