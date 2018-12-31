package dojo.supermarket;

import dojo.supermarket.model.ProductUnit;

public class receiptVisitor {
    private int columns;
    private StringBuilder receiptText = new StringBuilder();

    public receiptVisitor(int columns) {
        this.columns = columns;
    }

    public void addItem(double totalPrice, String productName) {
        String price = String.format("%.2f", totalPrice);

        String line = formatColumns(productName, price);
        receiptText.append( line + "\n");

    }

    public void addItemQuantity(ProductUnit unit, double quantity1, double price) {
        // Todo use ProductQuantity
        String quantity = ProductUnit.Each.equals(unit)
                ? String.format("%x", (int) quantity1)
                : String.format("%.3f", quantity1);
        String unitPrice = String.format("%.2f", price);
        String line = "  " + unitPrice + " * " + quantity + "\n";
        receiptText.append(line);

    }

    public void addDiscount(String productName, double discountAmount, String description) {
        String pricePresentation = String.format("%.2f", discountAmount);
        String discountDescription = description + "(" + productName + ")";
        String line = formatColumns(discountDescription, "-" + pricePresentation);
        receiptText.append( line + "\n");
    }

    public void addReceiptSection(double totalPrice) {
        String pricePresentation = String.format("%.2f", totalPrice);
        String total = "Total: ";
        String line = formatColumns(total, pricePresentation);
        receiptText.append("\n");
        receiptText.append(line);
    }

    public String build() {
        return receiptText.toString();
    }


    private String formatColumns(String leftColumn, String rightColumn) {
        String whitespaces = getWhitespace(this.columns - leftColumn.length() - rightColumn.length());
        return leftColumn +
                whitespaces + rightColumn;
    }

    private static String getWhitespace(int whitespaceSize) {
        StringBuilder whitespace = new StringBuilder();
        for (int i = 0; i < whitespaceSize; i++) {
            whitespace.append(" ");
        }
        return whitespace.toString();
    }

}
