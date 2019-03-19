package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public abstract class NumberOfItemsForAmount extends Offer {

    private final int numberOfItemsForOffer;
    private double amount;

    NumberOfItemsForAmount(Product product, int numberOfItemsForDiscount, double amount) {
        super(product);
        this.numberOfItemsForOffer = numberOfItemsForDiscount;
        this.amount = amount;
    }

    @Override
    public Discount getDiscount(double quantity, double unitPrice, int quantityAsInt) {
        if (quantityAsInt < numberOfItemsForOffer) {
            return null;
        }

        double total = amount * quantityAsInt / numberOfItemsForOffer + quantityAsInt % numberOfItemsForOffer * unitPrice;
        double discountN = unitPrice * quantity - total;
        return new Discount(this.getProduct(), numberOfItemsForOffer + " for " + amount, discountN);
    }

}
