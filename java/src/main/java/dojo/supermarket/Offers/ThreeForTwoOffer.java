package dojo.supermarket.Offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class ThreeForTwoOffer extends Offer {
    public ThreeForTwoOffer(Product product) {
        super(product);
    }

    @Override
    public Discount calculateDiscount(double quantity, double unitPrice) {
        Discount discount;
        if (quantity >= 3) {
            int numberOfXs = (int) quantity / 3;
            double discountAmount = unitPrice * numberOfXs;
            discount = new Discount(product, "3 for 2", discountAmount);
        } else {
            discount = null;
        }
        return discount;
    }
}
