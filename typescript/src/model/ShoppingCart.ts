import {Map} from "typescript"
import {Product} from "./Product"
import {SupermarketCatalog} from "./SupermarketCatalog"
import * as _ from "lodash"
import {ProductQuantity} from "./ProductQuantity"
import {Discount} from "./Discount"

type ProductQuantities = { [p: Product]: number }

public class ShoppingCart {

    private readonly  items: ProductQuantity[] = [];
    _productQuantities: ProductQuantities = {};


    getItems(): ProductQuantity[] {
        return _.clone(this.items);
    }

    addItem(product: Product): void {
        this.addItemQuantity(product, 1.0);
    }

    productQuantities(): ProductQuantities {
        return this._productQuantities;
    }


    public addItemQuantity(product: Product, quantity: number): void {
        this.items.push(new ProductQuantity(product, quantity));
        if (this._productQuantities[product]) {
            this._productQuantities[product] += quantity;
        } else {
            this._productQuantities[product] = quantity;
        }
    }

    handleOffers(receipt: Receipt,  offers: OffersByProduct, catalog: SupermarketCatalog ):void {
        for (const p: Product in this.productQuantities().keySet()) {
            const quantity: number = this._productQuantities[p];
            if (offers.containsKey(p)) {
                const offer : Offer = offers[p];
                const unitPrice: number= catalog.getUnitPrice(p);
                let quantityAsInt = quantity;
                let discount : Discount|null = null;
                let x = 1;
                if (offer.offerType == SpecialOfferType.ThreeForTwo) {
                    x = 3;

                } else if (offer.offerType == SpecialOfferType.TwoForAmount) {
                    x = 2;
                    if (quantityAsInt >= 2) {
                        const total = offer.argument * quantityAsInt / x + quantityAsInt % 2 * unitPrice;
                        const discountN = unitPrice * quantity - total;
                        discount = new Discount(p, "2 for " + offer.argument, discountN);
                    }

                } if (offer.offerType == SpecialOfferType.FiveForAmount) {
                    x = 5;
                }
                const numberOfXs = quantityAsInt / x;
                if (offer.offerType == SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
                    const discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
                    discount = new Discount(p, "3 for 2", discountAmount);
                }
                if (offer.offerType == SpecialOfferType.TenPercentDiscount) {
                    discount = new Discount(p, offer.argument + "% off", quantity * unitPrice * offer.argument / 100.0);
                }
                if (offer.offerType == SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
                    const discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
                    discount = new Discount(p, x + " for " + offer.argument, discountTotal);
                }
                if (discount != null)
                    receipt.addDiscount(discount);
            }

        }
    }
}
