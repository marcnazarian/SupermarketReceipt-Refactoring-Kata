package dojo.supermarket.model;

import dojo.supermarket.ReceiptBuilder;

public interface ReceiptElement {
    void addReceiptSection(ReceiptBuilder receiptBuilder);
}
