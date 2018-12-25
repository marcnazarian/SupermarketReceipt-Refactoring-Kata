package dojo.supermarket;

public class FixedWidthPrinter {
    private int width;

    public FixedWidthPrinter(int width) {
        this.width = width;
    }

    public String formatColumns(String leftColumn, String rightColumn) {
        return leftColumn + getWhitespace() + rightColumn;
    }

    private String getWhitespace() {
        StringBuilder whitespace = new StringBuilder();
        for (int i = 0; i < width; i++) {
            whitespace.append(" ");
        }
        return whitespace.toString();
    }

}
