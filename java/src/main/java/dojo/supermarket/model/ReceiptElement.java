package dojo.supermarket.model;

import dojo.supermarket.receiptVisitor;

public interface ReceiptElement {
    void addReceiptSection(receiptVisitor receiptBuilder);
}
