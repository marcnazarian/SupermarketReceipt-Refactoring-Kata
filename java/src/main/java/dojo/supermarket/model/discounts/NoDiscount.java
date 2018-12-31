package dojo.supermarket.model.discounts;

import dojo.supermarket.receiptVisitor;

public class NoDiscount implements Discount {
    @Override
    public void addReceiptSection(receiptVisitor receiptBuilder) {
        // do nothing of course
    }

    @Override
    public double getDiscountAmount() {
        return 0;
    }
}
