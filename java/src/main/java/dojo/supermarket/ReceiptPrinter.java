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
            String line = receiptItemLines(item);
            result.append(line);
        }
        for (Discount discount : receipt.getDiscounts()) {
            String discountLine = getDiscountLine(discount.getDiscountData());
            result.append(discountLine);
        }
        result.append("\n");
        String receiptTotal = receiptTotalSection(receipt);
        result.append(receiptTotal);
        return result.toString();
    }

    private String receiptItemLines(ReceiptItem item) {

        String price = String.format("%.2f", item.getTotalPrice());

        String lines = formatColumns(item.getProductName(), price) + "\n";

        if (item.getQuantity() != 1) {
            String quantity = quantityDescription(item);
            String unitPrice = String.format("%.2f", item.getPrice());
            lines += "  " + unitPrice + " * " + quantity + "\n";
        }
        return lines;
    }

    private String quantityDescription(ReceiptItem item) {
        return item.isProductPerUnit()
                        ? String.format("%x", (int) item.getQuantity())
                        : String.format("%.3f", item.getQuantity());
    }

    private String getDiscountLine(DiscountData discountData) {
        String description = discountData.getDescription() + "(" + discountData.getProductPresentation() + ")";
        String amount = "-" + String.format("%.2f", discountData.getDiscountAmount());

        return formatColumns(description, amount) + "\n";
    }


    private String receiptTotalSection(Receipt receipt) {
        String pricePresentation = String.format("%.2f", (double) receipt.getTotalPrice());
        String total = "Total: ";

        return formatColumns(total, pricePresentation);
    }


    private String formatColumns(String leftColumn, String rightColumn) {
        String whitespaces = getWhitespace(this.columns - leftColumn.length() - rightColumn.length());
        return leftColumn + whitespaces + rightColumn;
    }

    private static String getWhitespace(int whitespaceSize) {
        StringBuilder whitespace = new StringBuilder();
        for (int i = 0; i < whitespaceSize; i++) {
            whitespace.append(" ");
        }
        return whitespace.toString();
    }

}
