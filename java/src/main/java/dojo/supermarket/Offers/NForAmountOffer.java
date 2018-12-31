package dojo.supermarket.Offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.RealDiscount;

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
    protected Discount getDiscount(int quantity, double unitPrice) {
        Discount discount;
        int numberOfXs = quantity / requiredQuantity;
        double discountForTwo = unitPrice * requiredQuantity - totalAmountForBoth;
        double discountN = discountForTwo * numberOfXs;
        discount = new RealDiscount(product, requiredQuantity + " for " + totalAmountForBoth, discountN);
        return discount;
    }

    @Override
    protected boolean discountApplies(double quantity) {
        return quantity >= requiredQuantity;
    }
}
