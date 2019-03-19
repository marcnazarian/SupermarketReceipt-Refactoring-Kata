package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public abstract class NumberOfItemsForAmount extends Offer {

    private final int numberOfItemsForOffer;

    NumberOfItemsForAmount(Product product, int numberOfItemsForDiscount, double amount) {
        super(product, amount);
        this.numberOfItemsForOffer = numberOfItemsForDiscount;
    }

    @Override
    public Discount getDiscount(double quantity, double unitPrice, int quantityAsInt) {
        if (quantityAsInt < numberOfItemsForOffer) {
            return null;
        }

        double total = argument * quantityAsInt / numberOfItemsForOffer + quantityAsInt % numberOfItemsForOffer * unitPrice;
        double discountN = unitPrice * quantity - total;
        return new Discount(this.getProduct(), numberOfItemsForOffer + " for " + argument, discountN);
    }

}
