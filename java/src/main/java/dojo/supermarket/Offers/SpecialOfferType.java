package dojo.supermarket.Offers;

import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public enum SpecialOfferType {
    FiveForAmount, TenPercentDiscount, ThreeForTwo, TwoForAmount;

    public Offer createSpecialOffer(Product product, double argument) {
        switch (this) {
            case ThreeForTwo:
                return threeForTwo(product);
            case TenPercentDiscount:
                return tenPercent(product);
            case TwoForAmount:
                return twoForAmount(product, argument);
            case FiveForAmount:
                return fiveForAmount(product, argument);
        }
        throw new IllegalArgumentException("unreachable");
    }

    public NForAmountOffer fiveForAmount(Product product, double argument) {
        return nForAmount(product, argument, 5);
    }

    public NForAmountOffer twoForAmount(Product product, double argument) {
        return nForAmount(product, argument, 2);
    }

    public NForAmountOffer nForAmount(Product product, double amountForAll, int requiredQuantity) {
        return new NForAmountOffer(product, amountForAll, requiredQuantity);
    }

    public TenPercentDiscountOffer tenPercent(Product product) {
        return new TenPercentDiscountOffer(product);
    }

    public ThreeForTwoOffer threeForTwo(Product product) {
        return new ThreeForTwoOffer(product);
    }
}
