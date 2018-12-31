package dojo.supermarket.model.discounts;

import dojo.supermarket.receiptVisitor;
import dojo.supermarket.model.Product;

public class RealDiscount implements Discount {
    private final String description;
    private final double discountAmount;
    private final Product product;

    public RealDiscount(Product product, String description, double discountAmount) {
        this.product = product;
        this.description = description;
        this.discountAmount = discountAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public void addReceiptSection(receiptVisitor receiptVisitor) {
        receiptVisitor.addDiscount(product.getName(), discountAmount, this.description);
    }
}
