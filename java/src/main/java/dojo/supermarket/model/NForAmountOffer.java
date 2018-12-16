package dojo.supermarket.model;

// TODO could be implemented with a n for m
public class NForAmountOffer extends Offer {
    private double totalAmountForBoth;
    private final int requiredQuantity;

    public NForAmountOffer(Product product, double totalAmountForBoth, int requiredQuantity) {
        super(SpecialOfferType.TwoForAmount, product, totalAmountForBoth);
        this.totalAmountForBoth = totalAmountForBoth;
        this.requiredQuantity = requiredQuantity;
    }

    @Override
    Discount calculateDiscount(double quantity, double unitPrice) {
        Discount discount;
        if (quantity >= requiredQuantity) {
            int numberOfXs = (int) quantity / requiredQuantity;
            double discountForTwo = unitPrice * requiredQuantity - totalAmountForBoth;
            double discountN = discountForTwo * numberOfXs;
            discount = new Discount(product, requiredQuantity + " for " + totalAmountForBoth, discountN);

        } else {
            discount = null;
        }
        return discount;
    }
}
