package dojo.supermarket.model;

public class Offer {
    SpecialOfferType offerType;
    protected final Product product;
    double argument;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    Product getProduct() {
        return this.product;
    }

    Discount calculateDiscount(double quantity, double unitPrice) {
        return null;
    }

}
