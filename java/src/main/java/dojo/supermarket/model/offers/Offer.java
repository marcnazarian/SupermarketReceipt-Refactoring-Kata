package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public abstract class Offer {
    private final Product product;

    public Offer(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public abstract Discount getDiscount(double quantity, double unitPrice, int quantityAsInt);
}
