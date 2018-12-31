package dojo.supermarket.Offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.NoDiscount;
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
        Discount discount;
        if (discountApplies(quantity)) {
            discount = getDiscount((int) quantity, unitPrice);

        } else {
            discount = new NoDiscount();
        }
        return discount;
    }

    protected abstract Discount getDiscount(int quantity, double unitPrice);

    protected abstract boolean discountApplies(double quantity);
}
