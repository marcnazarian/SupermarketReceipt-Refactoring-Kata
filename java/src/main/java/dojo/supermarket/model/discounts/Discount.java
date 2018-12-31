package dojo.supermarket.model.discounts;

import dojo.supermarket.ReceiptBuilder;
import dojo.supermarket.model.ReceiptElement;

public interface Discount extends ReceiptElement {

    double getDiscountAmount();
}
