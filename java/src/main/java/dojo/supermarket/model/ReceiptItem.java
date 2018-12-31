package dojo.supermarket.model;

import dojo.supermarket.receiptVisitor;

import java.util.Objects;

public class ReceiptItem implements ReceiptElement {
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

    @Override
    public void addReceiptSection(receiptVisitor receiptBuilder) {

        receiptBuilder.addItem(totalPrice, product.getName());
        // Todo should this be in the receiptBuilder?
        if (quantity != 1) {
            ProductQuantity productQuantity = new ProductQuantity(this.product, this.quantity);
            receiptBuilder.addItemQuantity(this.price, productQuantity);
        }
    }

}
