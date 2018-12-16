package dojo.supermarket.model;

public abstract class Offer {
    protected final Product product;

    public Offer(Product product) {
        this.product = product;
    }

    Product getProduct() {
        return this.product;
    }

    abstract Discount calculateDiscount(double quantity, double unitPrice);

}
