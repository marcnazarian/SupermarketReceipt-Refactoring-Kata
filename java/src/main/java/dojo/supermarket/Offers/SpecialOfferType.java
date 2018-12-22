package dojo.supermarket.Offers;

import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public enum SpecialOfferType {
    FiveForAmount, TenPercentDiscount, ThreeForTwo, TwoForAmount;

    public Offer createSpecialOffer(Product product, double argument) {
        switch (this) {
            case ThreeForTwo:
                return new ThreeForTwoOffer(product, argument);
            case TenPercentDiscount:
                return new TenPercentDiscountOffer(product);
            case TwoForAmount:
                return new NForAmountOffer(product, argument, 2);
            case FiveForAmount:
                return new NForAmountOffer(product, argument, 5);
        }
        throw new IllegalArgumentException("unreachable");
    }
}
