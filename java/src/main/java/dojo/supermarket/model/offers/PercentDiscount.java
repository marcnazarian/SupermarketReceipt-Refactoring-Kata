package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public abstract class PercentDiscount extends Offer {

    private double percentDiscount;

    PercentDiscount(Product product, double percentDiscount) {
        super(product, percentDiscount);
        this.percentDiscount = percentDiscount;
    }

    @Override
    public Discount getDiscount(Product p, double quantity, double unitPrice, int quantityAsInt) {
        return new Discount(p, percentDiscount + "% off", quantity * unitPrice * percentDiscount / 100.0);
    }
}
