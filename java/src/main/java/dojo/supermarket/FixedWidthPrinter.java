package dojo.supermarket;

public class FixedWidthPrinter {
    private int columns;

    public FixedWidthPrinter(int columns) {

        this.columns = columns;
    }

    private static String getWhitespace(int whitespaceSize) {
        StringBuilder whitespace = new StringBuilder();
        for (int i = 0; i < whitespaceSize; i++) {
            whitespace.append(" ");
        }
        return whitespace.toString();
    }

    public String formatColumns(String leftColumn, String rightColumn) {
        String whitespaces = getWhitespace(this.columns - leftColumn.length() - rightColumn.length());
        return leftColumn +
                whitespaces + rightColumn;
    }
}
