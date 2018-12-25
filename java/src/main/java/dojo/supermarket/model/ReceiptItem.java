package dojo.supermarket.model;

import dojo.supermarket.FixedWidthPrinter;

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

    public String receiptItemLine(FixedWidthPrinter fixedWidthPrinter) {

        String price = String.format("%.2f", totalPrice);

        String line = fixedWidthPrinter.formatColumns(product.getName(), price) + "\n";

        if (quantity != 1) {
            String quantity = presentQuantity();
            String unitPrice = String.format("%.2f", this.price);
            line += "  " + unitPrice + " * " + quantity + "\n";
        }
        return line;
    }

    private String presentQuantity() {
        return ProductUnit.Each.equals(this.product.getUnit())
                ? String.format("%x", (int) this.quantity)
                : String.format("%.3f", this.quantity);
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
}
