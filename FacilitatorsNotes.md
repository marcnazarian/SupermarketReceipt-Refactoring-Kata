# Facilitators notes

If you haven't done the kata yet stop reading and try it out first.


## about the Teller
The code is unclear in that the teller calls back to the cart in order to build a receipt, this seems like a failed effort in encapsulation but it makes the teller expose the productCatalog, the Receipt and all the offers to the [Cart](https://www.websequencediagrams.com/?lz=dGl0bGUgU3VwZXIgbWFya2V0IHByaWNpbmcga2F0YSwgbGVzcyBjb3VwbGluZwoKQ2xpZW50LT5DYXJ0OiBhZGQgaXRlbQABFgANClRlbGxlcgAqBm9mZmVyAAkRY2hlY2tvdXQgY2FydAoAKgYAXghnZXRJdGVtcwAOCVJlY2VpcAB5B3Byb2R1YwAqCgABHABlCmFsY3VsYXRlRGlzY291bgApFgAUCQoKCg&s=napkin). It is actually a lot better just to [inline this method in the teller](https://www.websequencediagrams.com/?lz=dGl0bGUgU3VwZXIgbWFya2V0IHByaWNpbmcga2F0YSwgc3RhcnRpbmcgc3RhdGUKCkNsaWVudC0-Q2FydDogYWRkIGl0ZW0AARYADQpUZWxsZXIAKgZvZmZlcgAJEWNoZWNrb3V0IGNhcnQKACoGAF4IZ2V0SXRlbXMADglSZWNlaXAAeQdwcm9kdWMAKgoAARwAgTAHaGFuZGxlT2ZmZXJzADgIcywAgRQGcywgY2F0YWxvZwpDYXJ0AF8ORGlzY291bnQKCg&s=napkin). Then start moving out other parts of the logic.

## about the offers
There are a lot of smells here. One of which is the *argument* parameter to teller.addSpecialOffer, which means different things for different offers and is sometimes not used.

The enum is a not a great fit at this stage - it cannot be polymorphic, forcing a switch statement. An alternative design is to create 3 classes TenPercentOffer, ThreeForTwoOffer, NForAmountOffer (configured with 2 and 5 for now). The danger is to lose the one-stop-api that is currently in place. This can be reinstated with 4 methods on some class like SpecialOffers

## about the ReceiptPrinter

The receiptPrinter knows everything about Discount, ReceiptItem and Receipt. There are violations of the law of Demeter particularly in getting the products name. There are several ways of improving this

1. Making each item just return a bare data structure (this is lightweight in for instance TypeScript, less so in Java) 
2. Extract a ColumnPrinter taking care of whitespace between the left and right, then move the logic concerning discount, receiptItem, and receipt to the respective classes. like

```
String discount = discount.getDiscountSection(columnPrinter)  
```

3. Visitor Pattern: Create a ReceiptVisitor (that currently has only one implementation). It has 3-4 methods taking the information from Discount, from the receipt and the the ProductItem

## The null special value of the discount
The idea that no discount is represented by the value null can be replaced by a NullObject in combination with the Visitor pattern 

 