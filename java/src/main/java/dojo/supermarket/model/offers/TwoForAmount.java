package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class TwoForAmount extends Offer {

    public TwoForAmount(Product product, double amount) {
        super(product, amount);
    }

    @Override
    public Discount getDiscount(Product p, double quantity, double unitPrice, int quantityAsInt) {
        int x = 2;
        if (quantityAsInt < 2) {
            return null;
        }

        double total = argument * quantityAsInt / x + quantityAsInt % 2 * unitPrice;
        double discountN = unitPrice * quantity - total;
        return new Discount(p, "2 for " + argument, discountN);
    }
}
