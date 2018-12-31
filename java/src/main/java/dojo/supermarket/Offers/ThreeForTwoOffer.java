package dojo.supermarket.Offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class ThreeForTwoOffer extends Offer {
    public ThreeForTwoOffer(Product product) {
        super(product);
    }

    @Override
    protected Discount getDiscount(int quantity, double unitPrice) {
        int numberOfXs = (int) quantity / 3;
        double discountAmount = unitPrice * numberOfXs;
        return new Discount(product, "3 for 2", discountAmount);
    }

    @Override
    protected boolean discountApplies(double quantity) {
        return quantity >= 3;
    }
}
