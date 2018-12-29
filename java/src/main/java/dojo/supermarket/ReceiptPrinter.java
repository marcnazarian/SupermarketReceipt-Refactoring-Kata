package dojo.supermarket;

import dojo.supermarket.model.*;

public class ReceiptPrinter {

    private final ColumnPrinter columnPrinter;

    public ReceiptPrinter(int columns) {
        columnPrinter = new ColumnPrinter(columns);
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
        return columnPrinter.formatColumns(discount.fullDescription(), discount.amountDescription());
    }

    private String receiptTotalSection(Receipt receipt) {
        return columnPrinter.formatColumns("Total: ", receipt.totalPrice());
    }

    private String receiptItemLine(ReceiptItem item) {

        String line1 = columnPrinter.formatColumns(item.productName(), item.totalPrice());

        String line = line1 + "\n";

        if (item.getQuantity() != 1) {
            line += item.quantityAndPrice();
        }
        return line;
    }

}
