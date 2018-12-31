package dojo.supermarket;

import dojo.supermarket.model.*;

public class ReceiptPrinter {

    private final int columns;

    public ReceiptPrinter(int columns) {
        this.columns = columns;
    }

    public String printReceipt(Receipt receipt) {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder(this.columns);
        StringBuilder result = new StringBuilder();
        for (ReceiptItem item : receipt.getItems()) {
            item.addReceiptSection(receiptBuilder);
        }
        for (Discount discount : receipt.getDiscounts()) {
            discount.addReceiptSection(receiptBuilder);
        }

        receipt.addReceiptSection(receiptBuilder);
        result.append(receiptBuilder.build());
        return result.toString();
    }

}
