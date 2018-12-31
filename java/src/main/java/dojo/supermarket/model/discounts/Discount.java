package dojo.supermarket.model.discounts;

import dojo.supermarket.ReceiptBuilder;

public interface Discount {
    void addReceiptSection(ReceiptBuilder receiptBuilder);

    double getDiscountAmount();
}
