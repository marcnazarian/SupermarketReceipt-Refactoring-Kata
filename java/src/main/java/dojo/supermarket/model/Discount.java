package dojo.supermarket.model;

import dojo.supermarket.FixedWidthPrinter;

public class Discount {
    private final String description;
    private final double discountAmount;
    private final Product product;

    public Discount(Product product, String description, double discountAmount) {
        this.product = product;
        this.description = description;
        this.discountAmount = discountAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public String discountLine(FixedWidthPrinter fixedWidthPrinter) {
        String leftColumn = this.description + "(" + product.getName() + ")";
        String rightColumn = "-" + String.format("%.2f", discountAmount);
        return fixedWidthPrinter.formatColumns(leftColumn, rightColumn);
    }
}
