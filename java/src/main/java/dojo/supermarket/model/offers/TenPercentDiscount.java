package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class TenPercentDiscount extends Offer {

    public TenPercentDiscount(Product product, double argument) {
        super(product, argument);
    }

    @Override
    public Discount getDiscount(Product p, double quantity, double unitPrice, int quantityAsInt) {
        return new Discount(p, argument + "% off", quantity * unitPrice * argument / 100.0);
    }
}
