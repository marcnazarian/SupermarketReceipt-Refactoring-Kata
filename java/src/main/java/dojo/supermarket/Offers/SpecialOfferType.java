package dojo.supermarket.Offers;

import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public enum SpecialOfferType {
    FiveForAmount, TenPercentDiscount, ThreeForTwo, TwoForAmount;

    public static NForAmountOffer fiveForAmount(Product product, double argument) {
        return nForAmount(product, argument, 5);
    }

    public static NForAmountOffer twoForAmount(Product product, double argument) {
        return nForAmount(product, argument, 2);
    }

    public static NForAmountOffer nForAmount(Product product, double amountForAll, int requiredQuantity) {
        return new NForAmountOffer(product, amountForAll, requiredQuantity);
    }

    public static TenPercentDiscountOffer tenPercent(Product product) {
        return new TenPercentDiscountOffer(product);
    }

    public static ThreeForTwoOffer threeForTwo(Product product) {
        return new ThreeForTwoOffer(product);
    }
}
