package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new HashMap<>();


    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return productQuantities;
    }


    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p : productQuantities().keySet()) {
            double quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                double unitPrice = catalog.getUnitPrice(p);
                Discount discount = calculateDiscount(p, quantity, offer, unitPrice);

                if (discount != null) {
                    receipt.addDiscount(discount);
                }
            }

        }
    }

    private Discount calculateDiscount(Product p, double quantity, Offer offer, double unitPrice) {
        Discount discount;
        if (offer.offerType == SpecialOfferType.TwoForAmount && quantity >= 2) {
            double total = offer.argument * quantity / 2 + quantity % 2 * unitPrice;
            double discountN = unitPrice * quantity - total;
            discount = new Discount(p, "2 for " + offer.argument, discountN);

        } else if (offer.offerType == SpecialOfferType.ThreeForTwo && quantity > 2) {
            int numberOfXs =  (int) quantity / 3;
            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantity % 3 * unitPrice);
            discount = new Discount(p, "3 for 2", discountAmount);

        } else if (offer.offerType == SpecialOfferType.TenPercentDiscount) {
            discount = new Discount(p, offer.argument + "% off", quantity * unitPrice * offer.argument / 100.0);

        } else if (offer.offerType == SpecialOfferType.FiveForAmount && quantity >= 5) {
            int numberOfXs = (int) quantity / 5;
            double discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantity % 5 * unitPrice);
            discount = new Discount(p, 5 + " for " + offer.argument, discountTotal);
        } else {
            discount = null;
        }
        return discount;
    }
}
