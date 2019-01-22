package dojo.supermarket.model;

import dojo.supermarket.ReceiptPrinter;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupermarketTest {

    private SupermarketCatalog catalog;
    private Product toothbrush;
    private Product apples;
    private ShoppingCart cart;
    private Teller teller;

    @BeforeEach
    private void setUp() {
        catalog = new FakeCatalog();
        toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);
        cart = new ShoppingCart();
        teller = new Teller(catalog);
    }

    @Test
    void no_special_offer() {
        cart.addItemQuantity(apples, 2.5);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }


//    Buy two toothbrushes, get one free. Normal toothbrush price is €0.99
//            20% discount on apples, normal price €1.99 per kilo.
//            10% discount on rice, normal price €2.49 per bag
//    Five tubes of toothpaste for €7.49, normal price €1.79
//    Two boxes of cherry tomatoes for €0.99, normal price €0.69 per box.

    @Test
    void buy_two_toothbrushes_get_one_free_normal_toothbrush_price_is_0_99() {
        cart.addItemQuantity(toothbrush, 2);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, toothbrush, 0.99);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }
}
