package dojo.supermarket.model;

// TODO could be implemented with a n for m
public class TwoForAmountOffer extends Offer {
    private double totalAmountForBoth;

    public TwoForAmountOffer(Product product, double totalAmountForBoth) {
        super(SpecialOfferType.TwoForAmount, product, totalAmountForBoth);
        this.totalAmountForBoth = totalAmountForBoth;
    }

    @Override
    Discount calculateDiscount(double quantity, double unitPrice) {
        Discount discount;
        if (quantity >= 2) {
            int numberOfXs = (int) quantity / 2;
            double discountForTwo = unitPrice * 2 - totalAmountForBoth;
            double discountN = discountForTwo * numberOfXs;
            discount = new Discount(product, "2 for " + totalAmountForBoth, discountN);

        } else {
            discount = null;
        }
        return discount;
    }
}
