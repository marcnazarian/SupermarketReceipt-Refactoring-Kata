package dojo.supermarket.model;

public class ThreeForTwoOffer extends Offer {
    public ThreeForTwoOffer(Product product, double unitPrice) {
        super(SpecialOfferType.ThreeForTwo, product, unitPrice);
    }

    @Override
    Discount calculateDiscount(double quantity, double unitPrice) { // TODO could remove unitPrice
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
