package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class ThreeForTwo extends Offer {

    public ThreeForTwo(Product product, double argument) {
        super(product, argument);
    }

    @Override
    public Discount getDiscount(double quantity, double unitPrice, int quantityAsInt) {
        if (quantityAsInt <= 2) {
            return null;
        }

        int numberOfXs = quantityAsInt;
        double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
        return new Discount(this.getProduct(), "3 for 2", discountAmount);
    }
}
