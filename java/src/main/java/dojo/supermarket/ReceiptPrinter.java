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
        String productPresentation = discount.getProduct().getName();
        String description = discount.getDescription();
        String leftColumn = description + "(" + productPresentation + ")";
        String rightColumn = "-" + String.format("%.2f", discount.getDiscountAmount());
        return fixedWidthPrinter.formatColumns(leftColumn, rightColumn);
    }

    private String receiptTotalSection(Receipt receipt) {
        String pricePresentation = String.format("%.2f", (double) receipt.getTotalPrice());
        String total = "Total: ";
        return fixedWidthPrinter.formatColumns(total, pricePresentation);
    }

    private String receiptItemLine(ReceiptItem item) {

        String price = String.format("%.2f", item.getTotalPrice());
        String name = item.getProduct().getName();

        String line1 = fixedWidthPrinter.formatColumns(name, price);

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

}
