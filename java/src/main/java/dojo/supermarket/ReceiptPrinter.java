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
            String line = receiptItemLine(item, this.columns);
            result.append(line);
        }
        for (Discount discount : receipt.getDiscounts()) {
            String discountline = discountTotalLine(discount);
            result.append(discountline);
        }
        result.append("\n");
        String line = receiptTotalSection(receipt);
        result.append(line);
        return result.toString();
    }

    private String discountTotalLine(Discount discount) {
        String productPresentation = discount.getProduct().getName();
        String pricePresentation = String.format("%.2f", discount.getDiscountAmount());
        String description = discount.getDescription();
        String whitespaces = getWhitespace(this.columns - 3 - productPresentation.length() - description.length() - pricePresentation.length());
        return description + "(" + productPresentation + ")" +
                whitespaces + "-" + pricePresentation + "\n";
    }

    private String receiptTotalSection(Receipt receipt) {
        String pricePresentation = String.format("%.2f", (double) receipt.getTotalPrice());
        String total = "Total: ";
        String whitespace = getWhitespace(this.columns - total.length() - pricePresentation.length());
        return total + whitespace + pricePresentation;
    }

    private String receiptItemLine(ReceiptItem item, int columnCount) {
        String price = String.format("%.2f", item.getTotalPrice());
        String quantity = presentQuantity(item);
        String name = item.getProduct().getName();
        String unitPrice = String.format("%.2f", item.getPrice());

        int whitespaceSize = columnCount - name.length() - price.length();
        String whitespaces = getWhitespace(whitespaceSize);

        String line = name + whitespaces + price + "\n";

        if (item.getQuantity() != 1) {
            line += "  " + unitPrice + " * " + quantity + "\n";
        }
        return line;
    }

    private static String presentQuantity(ReceiptItem item) {
        return ProductUnit.Each.equals(item.getProduct().getUnit())
                ? String.format("%x", (int) item.getQuantity())
                : String.format("%.3f", item.getQuantity());
    }

    private static String getWhitespace(int whitespaceSize) {
        StringBuilder whitespace = new StringBuilder();
        for (int i = 0; i < whitespaceSize; i++) {
            whitespace.append(" ");
        }
        return whitespace.toString();
    }
}
