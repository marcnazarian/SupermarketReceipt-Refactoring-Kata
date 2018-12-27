package dojo.supermarket;

import dojo.supermarket.model.Discount;

public class DiscountData {
    private Discount discount;
    private String productPresentation;
    private String description;
    private double discountAmount;

    public DiscountData(String productPresentation, String description, double discountAmount) {
        this.productPresentation = productPresentation;
        this.description = description;
        this.discountAmount = discountAmount;
    }

    public String getProductPresentation() {
        return productPresentation;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

}
