package dojo.supermarket.Offers;

import dojo.supermarket.model.discounts.Discount;
import dojo.supermarket.model.discounts.NoDiscount;
import dojo.supermarket.model.Product;

public abstract class Offer {
    protected final Product product;

    public Offer(Product product) {
        this.product = product;
    }

    Product getProduct() {
        return this.product;
    }

    public Discount calculateDiscount(double quantity, double unitPrice) {
        int intQuantity = (int) quantity;
        return discountApplies(quantity) ? getDiscount(intQuantity, unitPrice) : new NoDiscount();
    }

    protected abstract Discount getDiscount(int quantity, double unitPrice);

    protected abstract boolean discountApplies(double quantity);
}
