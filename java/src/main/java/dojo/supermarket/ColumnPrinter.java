package dojo.supermarket;

public class ColumnPrinter {
    private int columns;

    public ColumnPrinter(int columns) {

        this.columns = columns;
    }

    String getWhitespace(int whitespaceSize) {
        StringBuilder whitespace = new StringBuilder();
        for (int i = 0; i < whitespaceSize; i++) {
            whitespace.append(" ");
        }
        return whitespace.toString();
    }

    String formatColumns(String leftColumn, String rightColumn) {
        String whitespaces = getWhitespace(this.columns - leftColumn.length() - rightColumn.length());
        return leftColumn + whitespaces + rightColumn;
    }
}
