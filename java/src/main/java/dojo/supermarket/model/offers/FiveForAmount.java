package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class FiveForAmount extends Offer {

    public FiveForAmount(Product product, double argument) {
        super(product, argument);
    }

    @Override
    public Discount getDiscount(Product p, double quantity, double unitPrice, int quantityAsInt) {
        if (quantityAsInt < 5) {
            return null;
        }

        int x = 5;
        int numberOfXs = quantityAsInt / x;
        double discountTotal = unitPrice * quantity - (argument * numberOfXs + quantityAsInt % 5 * unitPrice);
        return new Discount(p, x + " for " + argument, discountTotal);
    }
}
