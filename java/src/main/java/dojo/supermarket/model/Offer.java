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
        Discount discount;
        if (offerType == SpecialOfferType.TwoForAmount && quantity >= 2) {
            int numberOfXs = (int) quantity / 2;
            double discountN = (unitPrice * 2 - argument) * numberOfXs;
            discount = new Discount(product, "2 for " + argument, discountN);

        }  else if (offerType == SpecialOfferType.FiveForAmount && quantity >= 5) {
            int numberOfXs = (int) quantity / 5;
            double discountTotal = (unitPrice * 5 - argument) * numberOfXs;
            discount = new Discount(product, "5 for " + argument, discountTotal);

        } else {
            discount = null;
        }
        return discount;
    }
}
