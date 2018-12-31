package dojo.supermarket.model;

import dojo.supermarket.receiptVisitor;
import dojo.supermarket.model.discounts.Discount;

import java.util.ArrayList;
import java.util.List;

public class Receipt implements ReceiptElement {
    private List<ReceiptItem> items = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();

    private double getTotalPrice() {
        double total = 0.0;
        for (ReceiptItem item : this.items) {
            total += item.getTotalPrice();
        }
        for (Discount discount : this.discounts) {
            total -= discount.getDiscountAmount();
        }
        return total;
    }

    /**
     * @deprecated use addProduct(productQuantity, unitPrice) # primitiveObsession
     */
    public void addProduct(Product p, double quantity, double price, double totalPrice) {
        this.items.add(new ReceiptItem(p, quantity, price, totalPrice));
    }

    public void addProduct(ProductQuantity pq, double unitPrice) {
        this.items.add(createItem(pq, unitPrice));
    }

    private ReceiptItem createItem(ProductQuantity pq, double unitPrice) {
        Product p = pq.getProduct();
        double quantity = pq.getQuantity();
        double price = quantity * unitPrice;
        return new ReceiptItem(p, quantity, unitPrice, price);
    }


    public List<ReceiptItem> getItems() {
        return new ArrayList<>(this.items);
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void addDiscounts(List<Discount> discounts) {
        this.discounts.addAll(discounts);
    }

    @Override
    public void addReceiptSection(receiptVisitor receiptBuilder) {
        receiptBuilder.addReceiptSection(getTotalPrice());
    }

}
