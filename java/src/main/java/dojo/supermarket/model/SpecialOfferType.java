package dojo.supermarket.model;

public enum SpecialOfferType {
    FiveForAmount, TenPercentDiscount, ThreeForTwo, TwoForAmount;

    Discount calculateDiscount(Product p, double quantity, double unitPrice, double amountForLot) {
        Discount discount;
        if (this == TwoForAmount && quantity >= 2) {
            int numberOfXs = (int) quantity / 2;
            double discountN = (unitPrice * 2 - amountForLot) * numberOfXs;
            discount = new Discount(p, "2 for " + amountForLot, discountN);

        } else if (this == ThreeForTwo && quantity >= 3) {
            int numberOfXs = (int) quantity / 3;
            double discountAmount = unitPrice * numberOfXs;
            discount = new Discount(p, "3 for 2", discountAmount);

        } else if (this == FiveForAmount && quantity >= 5) {
            int numberOfXs = (int) quantity / 5;
            double discountTotal = (unitPrice * 5 - amountForLot) * numberOfXs;
            discount = new Discount(p, "5 for " + amountForLot, discountTotal);

        } else if (this == TenPercentDiscount) {
            double percentageDiscount = amountForLot;
            discount = new Discount(p, amountForLot + "% off", quantity * unitPrice * percentageDiscount / 100.0);

        } else {
            discount = null;
        }
        return discount;
    }
}
