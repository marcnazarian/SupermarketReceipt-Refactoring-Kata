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

        if (getQuantity() != 1) {
            String quantity = presentQuantity(this);
            String unitPrice = String.format("%.2f", getPrice());
            line += "  " + unitPrice + " * " + quantity + "\n";
        }
        return line;
    }

    public static String presentQuantity(ReceiptItem item) {
        return ProductUnit.Each.equals(item.product.getUnit())
                ? String.format("%x", (int) item.getQuantity())
                : String.format("%.3f", item.getQuantity());
    }

    public double getPrice() {
        return this.price;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
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
