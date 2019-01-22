package dojo.supermarket.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupermarketTest {

    private static final double DOUBLE_DELTA_PRECISION = 0.000001;
    private static final double PRICE_OF_ONE_TOOTHBRUSH = 0.99;
    private static final double PRICE_OF_APPLE_PER_KILO = 1.99;
    private static final double PRICE_OF_RICE_PER_BAG = 2.49;
    private static final double PRICE_OF_ONE_TOOTHPASTE = 1.79;
    private static final double PRICE_OF_ONE_CHERRY_TOMATOES_BOX = 0.69;

    private SupermarketCatalog catalog;
    private Product toothbrush;
    private Product apples;
    private Product rice;
    private Product toothpaste;
    private Product cherry_tomatoes;
    private Teller teller;
    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        catalog = new FakeCatalog();

        toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, PRICE_OF_ONE_TOOTHBRUSH);

        apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, PRICE_OF_APPLE_PER_KILO);

        rice = new Product("rice", ProductUnit.Each);
        catalog.addProduct(rice, PRICE_OF_RICE_PER_BAG);

        toothpaste = new Product("toothpaste", ProductUnit.Each);
        catalog.addProduct(toothpaste, PRICE_OF_ONE_TOOTHPASTE);

        cherry_tomatoes = new Product("cherry tomatoes", ProductUnit.Each);
        catalog.addProduct(cherry_tomatoes, PRICE_OF_ONE_CHERRY_TOMATOES_BOX);

        teller = new Teller(catalog);
        cart = new ShoppingCart();
    }

    @Test
    void no_special_offer() {
        double appleQuantity = 2.5;
        cart.addItemQuantity(apples, appleQuantity);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Assertions.assertEquals(appleQuantity * PRICE_OF_APPLE_PER_KILO, receipt.getTotalPrice().doubleValue());
    }

    @Test
    void buy_two_toothbrushes_get_one_free() {
        int quantityOfToothbrushes = 2;
        cart.addItemQuantity(toothbrush, quantityOfToothbrushes);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, toothbrush, PRICE_OF_ONE_TOOTHBRUSH);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Assertions.assertEquals(PRICE_OF_ONE_TOOTHBRUSH, receipt.getTotalPrice().doubleValue());
    }

    @Test
    void test_20_percent_discount_on_apples() {
        double appleQuantity = 2.5;
        cart.addItemQuantity(apples, appleQuantity);
        double percentageOfDiscount = 20.00;
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, apples, percentageOfDiscount);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Assertions.assertEquals(getDiscountWithPercentage(appleQuantity * PRICE_OF_APPLE_PER_KILO, percentageOfDiscount), receipt.getTotalPrice().doubleValue(), DOUBLE_DELTA_PRECISION);
    }

    private double getDiscountWithPercentage(double priceBeforeDiscount, double percentageOfDiscount) {
        return priceBeforeDiscount * (1 - percentageOfDiscount / 100);
    }

    @Test
    void test_10_percent_discount_on_rice() {
        int numberOfRiceBags = 3;
        cart.addItemQuantity(rice, numberOfRiceBags);
        double percentageOfDiscount = 10.00;
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, rice, percentageOfDiscount);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Assertions.assertEquals(getDiscountWithPercentage(numberOfRiceBags * PRICE_OF_RICE_PER_BAG, percentageOfDiscount), receipt.getTotalPrice().doubleValue(), DOUBLE_DELTA_PRECISION);
    }

    @Test
    void five_tubes_of_toothpaste_for_7_49_normal_price_1_79() {
        cart.addItemQuantity(toothpaste, 5);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, toothpaste, 7.49);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Assertions.assertEquals(7.49, receipt.getTotalPrice().doubleValue());
    }

    @Test
    void two_boxes_of_cherry_tomatoes_for_0_99_normal_price_0_69_per_box() {
        cart.addItemQuantity(cherry_tomatoes, 2);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, cherry_tomatoes, 0.99);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Assertions.assertEquals(0.99, receipt.getTotalPrice().doubleValue());
    }

}
