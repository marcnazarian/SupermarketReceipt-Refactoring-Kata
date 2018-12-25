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
        DiscountVisitor discountVisitor = new DiscountVisitor();
        discountVisitor.visitProductname(discount.getProduct());
        discountVisitor.visitDescription(discount.getDescription());
        discountVisitor.visitAmount(discount.getDiscountAmount());

        String productPresentation = discount.getProduct().getName();
        String pricePresentation = String.format("%.2f", discount.getDiscountAmount());
        String description = discount.getDescription();
        String leftColumn = description + "(" + productPresentation + ")";
        String rightColumn = "-" + pricePresentation;
        return formatColumns(leftColumn, rightColumn);
    }

    private String formatColumns(String leftColumn, String rightColumn) {
        String whitespaces = getWhitespace(this.columns - leftColumn.length() - rightColumn.length());
        return leftColumn +
                whitespaces + rightColumn;
    }

    private String receiptTotalSection(Receipt receipt) {
        String pricePresentation = String.format("%.2f", (double) receipt.getTotalPrice());
        String total = "Total: ";
        return formatColumns(total, pricePresentation);
    }

    private String receiptItemLine(ReceiptItem item) {

        String price = String.format("%.2f", item.getTotalPrice());
        String name = item.getProduct().getName();

        String line1 = formatColumns(name, price);

        String line = line1 + "\n";

        if (item.getQuantity() != 1) {
            String quantity = presentQuantity(item);
            String unitPrice = String.format("%.2f", item.getPrice());
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
