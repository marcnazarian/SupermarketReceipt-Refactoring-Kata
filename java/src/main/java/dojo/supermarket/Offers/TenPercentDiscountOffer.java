package dojo.supermarket.Offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class TenPercentDiscountOffer extends Offer {
    private double percentage = 10.0;

    public TenPercentDiscountOffer(Product product) {
        super(product);
    }

    @Override
    public Discount calculateDiscount(double quantity, double unitPrice) {
        return new Discount(product, percentage + "% off", quantity * unitPrice * percentage / 100.0);
    }
}
