package dojo.supermarket.model.discounts;

import dojo.supermarket.ColumnReceiptVisitor;

public class NoDiscount implements Discount {
    @Override
    public void addReceiptSection(ColumnReceiptVisitor receiptBuilder) {
        // do nothing of course
    }

    @Override
    public double getDiscountAmount() {
        return 0;
    }
}
