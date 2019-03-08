package dojo.supermarket.model.offers;

import dojo.supermarket.model.Product;

public class TenPercentDiscount extends PercentDiscount {
    public TenPercentDiscount(Product product) {
        super(product, 10.0);
    }
}
