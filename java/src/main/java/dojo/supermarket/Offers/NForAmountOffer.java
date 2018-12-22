package dojo.supermarket.Offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

// TODO could be implemented with a n for m
public class NForAmountOffer extends Offer {
    private double totalAmountForBoth;
    private final int requiredQuantity;

    public NForAmountOffer(Product product, double totalAmountForAll, int requiredQuantity) {
        super(product);
        this.totalAmountForBoth = totalAmountForAll;
        this.requiredQuantity = requiredQuantity;
    }

    @Override
    public Discount calculateDiscount(double quantity, double unitPrice) {
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
