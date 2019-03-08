package dojo.supermarket.model.offers;

import dojo.supermarket.model.Product;

public class FiveForAmount extends NumberOfItemsForAmount {

    public FiveForAmount(Product product, double amount) {
        super(product, 5, amount);
    }


}
