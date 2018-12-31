package dojo.supermarket.model;

import dojo.supermarket.Offers.Offer;
import dojo.supermarket.model.discounts.Discount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(Product product, Offer offer) {
        this.offers.put(product, offer);
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        for (ProductQuantity pq : theCart.getItems()) {
            double unitPrice = this.catalog.getUnitPrice(pq.getProduct());
            receipt.addProduct(pq, unitPrice);
        }

        // prductquantities -> offers -> discounts
        ArrayList<Discount> discounts = new ArrayList<>();
        for (Product p : theCart.productQuantities().keySet()) {
            if (this.offers.containsKey(p)) {
                SupermarketCatalog catalog = this.catalog;
                double unitPrice = catalog.getUnitPrice(p);

                Offer offer = this.offers.get(p);
                double quantity = theCart.productQuantities.get(p);
                Discount discount = offer.calculateDiscount(quantity, unitPrice);

                discounts.add(discount);
            }

        }
        receipt.addDiscounts(discounts);

        return receipt;
    }

}
