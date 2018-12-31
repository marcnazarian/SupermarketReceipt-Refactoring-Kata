package dojo.supermarket.model;

import dojo.supermarket.ReceiptBuilder;

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
    public void addReceiptSection(ReceiptBuilder receiptBuilder) {
        String productPresentation = product.getName();
        String pricePresentation = String.format("%.2f", discountAmount);
        String description = this.description;

        String discountDescription = description + "(" + productPresentation + ")";
        receiptBuilder.addDiscount(discountDescription, "-" + pricePresentation);
    }
}
