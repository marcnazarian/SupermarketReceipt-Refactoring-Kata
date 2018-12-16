package dojo.supermarket.model;

public class TenPercentDiscountOffer extends Offer {
    private double percentage = 10.0;

    public TenPercentDiscountOffer(SpecialOfferType tenPercentDiscount, Product product, double argument) {
        super(SpecialOfferType.TenPercentDiscount, product, 0);
    }

    @Override
    Discount calculateDiscount(double quantity, double unitPrice) {
        return new Discount(product, percentage + "% off", quantity * unitPrice * percentage / 100.0);
    }
}
