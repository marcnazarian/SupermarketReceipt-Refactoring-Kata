import {ProductUnit} from "./model/ProductUnit"
import {ReceiptItem} from "./model/ReceiptItem"
import {Receipt} from "./model/Receipt"

export class ReceiptPrinter {

    public constructor(private readonly columns: number = 40) {
    }

    public printReceipt( receipt: Receipt): string {
        const result = "";
        for (const item of receipt.getItems()) {
            let price = new Intl.NumberFormat('en-UK', {minimumFractionDigits: 2}).format(item.getTotalPrice());
            let quantity = ReceiptPrinter.presentQuantity(item);
            let name = item.getProduct().getName();
            let unitPrice = new Intl.NumberFormat('en-UK', {minimumFractionDigits: 2}).format(item.getPrice());

            let whitespaceSize = this.columns - name.length - price.length;
            let line = name + ReceiptPrinter.getWhitespace(whitespaceSize) + price + "\n";

            if (item.getQuantity() != 1) {
                line += "  " + unitPrice + " * " + quantity + "\n";
            }
            result.concat(line);
        }
        for (const discount of receipt.getDiscounts()) {
            let productPresentation = discount.getProduct().getName();
            let pricePresentation = new Intl.NumberFormat('en-UK', {minimumFractionDigits: 2}).format(discount.getDiscountAmount());
            let description = discount.getDescription();
            result.concat(description);
            result.concat("(");
            result.concat(productPresentation);
            result.concat(")");
            result.concat(ReceiptPrinter.getWhitespace(this.columns - 3 - productPresentation.length - description.length - pricePresentation.length));
            result.concat("-");
            result.concat(pricePresentation);
            result.concat("\n");
        }
        result.concat("\n");
        let pricePresentation = new Intl.NumberFormat('en-UK', {minimumFractionDigits: 2}).format(receipt.getTotalPrice());
        let total = "Total: ";
        let whitespace = ReceiptPrinter.getWhitespace(this.columns - total.length - pricePresentation.length);
        result.concat(total).concat(whitespace).concat(pricePresentation);
        return result;
    }

    private static presentQuantity( item: ReceiptItem): string  {
        return ProductUnit.Each == item.getProduct().getUnit()
            // TODO make sure this is the simplest way to make something similar to the java version
                ? new Intl.NumberFormat('en-UK', {maximumFractionDigits: 0}).format(item.getQuantity())
                : new Intl.NumberFormat('en-UK', {minimumFractionDigits: 3}).format(item.getQuantity());
    }

    private static getWhitespace(whitespaceSize: number): string {
        const whitespace = "";
        for (let i = 0; i < whitespaceSize; i++) {
            whitespace.concat(" ");
        }
        return whitespace;
    }
}
