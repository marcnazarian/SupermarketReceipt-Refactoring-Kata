package dojo.supermarket;

public class receiptVisitor {
    private int columns;
    private StringBuilder receiptText = new StringBuilder();

    public receiptVisitor(int columns) {

        this.columns = columns;
    }


    public void addItemQuantity(String line) {
        receiptText.append(line);

    }
    public void addReceiptItem(String name, String price) {
        String line = formatColumns(name, price);
        receiptText.append( line + "\n");

    }


    public void addDiscount(String description, String pricePresentation) {
        String line = formatColumns(description, pricePresentation);
        receiptText.append( line + "\n");

    }

    public void appendTotalSection(String total, String pricePresentation) {
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
