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
            receiptItemLine(item, receiptBuilder);
        }
        for (Discount discount : receipt.getDiscounts()) {
            String productPresentation = discount.getProduct().getName();
            String pricePresentation = String.format("%.2f", discount.getDiscountAmount());
            String description = discount.getDescription();

            String discountDescription = description + "(" + productPresentation + ")";
            receiptBuilder.addDiscount(discountDescription, "-" + pricePresentation);
        }

        addTotalSection(receipt, receiptBuilder);
        result.append(receiptBuilder.build());
        return result.toString();
    }

    private void addTotalSection(Receipt receipt, ReceiptBuilder receiptBuilder) {
        String pricePresentation = String.format("%.2f", (double) receipt.getTotalPrice());
        String total = "Total: ";
        receiptBuilder.appendTotalSection(total, pricePresentation);
    }

    private void receiptItemLine(ReceiptItem item, ReceiptBuilder receiptBuilder) {

        String price = String.format("%.2f", item.getTotalPrice());
        String name = item.getProduct().getName();

        receiptBuilder.addReceiptItem(name, price);

        if (item.getQuantity() != 1) {
            String quantity = ProductUnit.Each.equals(item.getProduct().getUnit())
                    ? String.format("%x", (int) item.getQuantity())
                    : String.format("%.3f", item.getQuantity());
            String unitPrice = String.format("%.2f", item.getPrice());
            String line = "  " + unitPrice + " * " + quantity + "\n";
            receiptBuilder.addItemQuantity(line);
        }
    }

}
