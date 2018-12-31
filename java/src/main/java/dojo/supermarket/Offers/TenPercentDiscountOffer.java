package dojo.supermarket.Offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class TenPercentDiscountOffer extends Offer {
    private double percentage = 10.0;

    public TenPercentDiscountOffer(Product product) {
        super(product);
    }

    @Override
    protected Discount getDiscount(int quantity, double unitPrice) {
        return new Discount(product, percentage + "% off", quantity * unitPrice * percentage / 100.0);
    }

    @Override
    protected boolean discountApplies(double quantity) {
        return true;
    }
}
