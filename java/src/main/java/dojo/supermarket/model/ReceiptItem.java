package dojo.supermarket.model;

import java.util.Objects;

public class ReceiptItem {
    private final Product product;
    private final double price;
    private double totalPrice;
    private final double quantity;

    public ReceiptItem(Product p, double quantity, double price, double totalPrice) {
        this.product = p;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItem that = (ReceiptItem) o;
        return Double.compare(that.price, price) == 0 &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                Double.compare(that.quantity, quantity) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(product, price, totalPrice, quantity);
    }


    public String productName() {
        return product.getName();
    }

    public String totalPrice() {
        return String.format("%.2f", totalPrice);
    }

    public String quantityAndPrice() {
        String quantity = product.isPerUnit()
                ? String.format("%x", (int) this.quantity)
                : String.format("%.3f", this.quantity);
        String unitPrice = String.format("%.2f", this.price);
        return "  " + unitPrice + " * " + quantity + "\n";
    }
}
