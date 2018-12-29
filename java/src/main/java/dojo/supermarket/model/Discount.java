package dojo.supermarket.model;

public class Discount {
    private final String description;
    private final double discountAmount;
    private final Product product;

    public Discount(Product product, String description, double discountAmount) {
        this.product = product;
        this.description = description;
        this.discountAmount = discountAmount;
    }

    double getDiscountAmount() {
        return discountAmount;
    }

    public Product getProduct() {
        return product;
    }

    public String fullDescription() {
        return description + "(" + product.getName() + ")";
    }

    public String amountDescription() {
        return "-" + String.format("%.2f", discountAmount);
    }
}
