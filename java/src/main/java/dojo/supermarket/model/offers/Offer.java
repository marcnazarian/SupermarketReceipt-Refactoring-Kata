package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public abstract class Offer {
    private final Product product;
    double argument;

    public Offer(Product product, double argument) {
        this.argument = argument;
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public abstract Discount getDiscount(Product p, double quantity, double unitPrice, int quantityAsInt);
}
