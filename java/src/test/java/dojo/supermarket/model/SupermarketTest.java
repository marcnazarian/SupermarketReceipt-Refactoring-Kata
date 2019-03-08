package dojo.supermarket.model;

import dojo.supermarket.ReceiptPrinter;
import dojo.supermarket.model.offers.FiveForAmount;
import dojo.supermarket.model.offers.TenPercentDiscount;
import dojo.supermarket.model.offers.TwoForAmount;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupermarketTest {

    private SupermarketCatalog catalog;
    private Product toothbrush;
    private Product apples;
    private Product rice;
    private Product toothpaste;
    private Product cherry_tomatoes;
    private ShoppingCart cart;
    private Teller teller;

    @BeforeEach
    private void setUp() {
        catalog = new FakeCatalog();

        toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        rice = new Product("rice", ProductUnit.Each);
        catalog.addProduct(rice, 2.49);

        toothpaste = new Product("toothpaste", ProductUnit.Each);
        catalog.addProduct(toothpaste, 1.79);

        cherry_tomatoes = new Product("cherry tomatoes", ProductUnit.Each);
        catalog.addProduct(cherry_tomatoes, 0.69);

        cart = new ShoppingCart();
        teller = new Teller(catalog);
    }

    @Test
    void no_special_offer() {
        cart.addItemQuantity(apples, 2.5);
        teller.addSpecialOffer(new TenPercentDiscount(toothbrush, 10.0));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }

    @Test
    void buy_two_toothbrushes_get_one_free_normal_toothbrush_price_is_0_99() {
        cart.addItemQuantity(toothbrush, 2);
        teller.addSpecialOffer(new TwoForAmount(toothbrush, 0.99));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }

    @Test
    void discount_20_percent_on_apples_normal_price_is_1_99_per_kilo() {
        cart.addItemQuantity(apples, 2.5);
        teller.addSpecialOffer(new TenPercentDiscount(apples, 20.0));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }

    @Test
    void discount_10_percent_on_rice_normal_price_is_2_49_per_bag() {
        cart.addItemQuantity(rice, 3);
        teller.addSpecialOffer(new TenPercentDiscount(rice, 10.0));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }

    @Test
    void five_tubes_of_toothpaste_for_7_49_normal_price_is_1_79_per_tube() {
        cart.addItemQuantity(toothpaste, 5);
        teller.addSpecialOffer(new FiveForAmount(toothpaste, 7.49));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }

    @Test
    void two_boxes_of_cherry_tomatoes_for_0_99_normal_price_0_69() {
        cart.addItemQuantity(cherry_tomatoes, 2);
        teller.addSpecialOffer(new TwoForAmount(cherry_tomatoes, 0.99));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }

}
