package dojo.supermarket;

import dojo.supermarket.model.*;
import dojo.supermarket.model.discounts.RealDiscount;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

public class ReceiptPrinterTest {

    Product toothbrush = new Product("toothbrush", ProductUnit.Each);
    Product apples = new Product("apples", ProductUnit.Kilo);
    Receipt receipt = new Receipt();

    @Test
    public void oneLineItem() {
        receipt.addProduct(new ProductQuantity(toothbrush, 1), 0.99);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void quantityTwo() {
        receipt.addProduct(new ProductQuantity(toothbrush, 2), 0.99);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void looseWeight() {
        receipt.addProduct(new ProductQuantity(apples, 2.3), 1.99);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    // TODO buggy test,
    @Test
    public void total() {

        receipt.addProduct(toothbrush, 1, 0.99, 2 * 0.99);
        receipt.addProduct(new ProductQuantity(apples, 0.75), 1.99);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void discounts() {
        receipt.addDiscount(new RealDiscount(apples, "3 for 2", 0.99));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void printWholeReceipt() {
        receipt.addProduct(new ProductQuantity(toothbrush, 1), 0.99);
        receipt.addProduct(new ProductQuantity(toothbrush, 2), 0.99);
        receipt.addProduct(new ProductQuantity(apples, 0.75), 1.99);
        receipt.addDiscount(new RealDiscount(toothbrush, "3 for 2", 0.99));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

}
