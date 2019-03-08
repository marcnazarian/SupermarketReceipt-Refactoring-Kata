package dojo.supermarket.model.offers;

import dojo.supermarket.model.Product;

public class TwentyPercentDiscount extends PercentDiscount {
    public TwentyPercentDiscount(Product product) {
        super(product, 20.0);
    }
}
