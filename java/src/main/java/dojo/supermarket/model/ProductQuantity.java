package dojo.supermarket.model;

public class ProductQuantity {
    private final Product product;
    private final double quantity;

    public ProductQuantity(Product product, double weight) {
        this.product = product;
        this.quantity = weight;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    public String quantityDescription() {
        return ProductUnit.Each.equals(product.getUnit())
                    ? String.format("%x", (int) quantity)
                    : String.format("%.3f", quantity);
    }
}
