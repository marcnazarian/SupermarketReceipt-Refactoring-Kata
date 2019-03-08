package dojo.supermarket.model.offers;

import dojo.supermarket.model.Product;

public class TwoForAmount extends NumberOfItemsForAmount {

    public TwoForAmount(Product product, double amount) {
        super(product, 2, amount);
    }

}
