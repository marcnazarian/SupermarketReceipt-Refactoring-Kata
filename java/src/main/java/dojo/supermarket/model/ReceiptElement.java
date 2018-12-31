package dojo.supermarket.model;

import dojo.supermarket.ColumnReceiptVisitor;

public interface ReceiptElement {
    void addReceiptSection(ColumnReceiptVisitor receiptBuilder);
}
