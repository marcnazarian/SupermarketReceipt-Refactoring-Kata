package dojo.supermarket;

import dojo.supermarket.model.*;

public class ReceiptPrinter {

    private final int columns;

    public ReceiptPrinter(int columns) {
        this.columns = columns;
    }

    public String printReceipt(Receipt receipt) {
        StringBuilder result = new StringBuilder();
        for (ReceiptItem item : receipt.getItems()) {
            String line = receiptItemLine(item);
            result.append(line);
        }
        for (Discount discount : receipt.getDiscounts()) {
            String discountline = discountLine(discount);
            result.append(discountline + "\n");
        }
        result.append("\n");
        String line = receiptTotalSection(receipt);
        result.append(line);
        return result.toString();
    }

    private String discountLine(Discount discount) {
        return formatColumns(discount.fullDescription(), discount.amountDescription());
    }

    private String formatColumns(String leftColumn, String rightColumn) {
        String whitespaces = getWhitespace(this.columns - leftColumn.length() - rightColumn.length());
        return leftColumn + whitespaces + rightColumn;
    }

    private String receiptTotalSection(Receipt receipt) {
        String pricePresentation = String.format("%.2f", (double) receipt.getTotalPrice());
        String total = "Total: ";
        return formatColumns(total, pricePresentation);
    }

    private String receiptItemLine(ReceiptItem item) {

        String line1 = formatColumns(item.productName(), item.totalPrice());

        String line = line1 + "\n";

        if (item.getQuantity() != 1) {
            line += item.quantityAndPrice();
        }
        return line;
    }

    private static String getWhitespace(int whitespaceSize) {
        StringBuilder whitespace = new StringBuilder();
        for (int i = 0; i < whitespaceSize; i++) {
            whitespace.append(" ");
        }
        return whitespace.toString();
    }
}
