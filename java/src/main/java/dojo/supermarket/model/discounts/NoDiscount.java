package dojo.supermarket.model.discounts;

import dojo.supermarket.ReceiptBuilder;

public class NoDiscount implements Discount {
    @Override
    public void addReceiptSection(ReceiptBuilder receiptBuilder) {
        // do nothing of course
    }

    @Override
    public double getDiscountAmount() {
        return 0;
    }
}
