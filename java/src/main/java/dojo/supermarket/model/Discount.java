package dojo.supermarket.model;

import dojo.supermarket.DiscountData;

public class Discount {
    private final String description;
    private final double discountAmount;
    private final Product product;

    public Discount(Product product, String description, double discountAmount) {
        this.product = product;
        this.description = description;
        this.discountAmount = discountAmount;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public Product getProduct() {
        return product;
    }

    public DiscountData getDiscountData() {
        return new DiscountData(this.getProduct().getName(), this.getDescription(), this.getDiscountAmount());
    }
}
