package dojo.supermarket;

import dojo.supermarket.model.*;

public class ReceiptPrinter {

    private final FixedWidthPrinter fixedWidthPrinter;
    private final int columns;

    public ReceiptPrinter(int columns) {
        this.columns = columns;
        fixedWidthPrinter = new FixedWidthPrinter(columns);
    }

    public String printReceipt(Receipt receipt) {
        StringBuilder result = new StringBuilder();
        for (ReceiptItem item : receipt.getItems()) {
            String line = item.receiptItemLine(fixedWidthPrinter);
            result.append(line);
        }
        for (Discount discount : receipt.getDiscounts()) {
            String discountline = discount.discountLine(fixedWidthPrinter);
            result.append(discountline + "\n");
        }
        result.append("\n");
        String line = receipt.receiptTotalSection(fixedWidthPrinter);
        result.append(line);
        return result.toString();
    }

}
