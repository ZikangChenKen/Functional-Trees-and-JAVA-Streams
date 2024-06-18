package edu.rice.comp322.provided.streams.provided;

import edu.rice.comp322.provided.streams.models.Customer;
import edu.rice.comp322.provided.streams.models.Product;

/**
 * The discount object helps you calculate the optimal discounts on a product which a
 * given customer can achieve.
 */
public class DiscountObject {

    public Customer purchaser;

    public Product product;

    public Double truePrice;

    public Double fullPrice;

    public Double discount;

    /**
     * Constructor for a discount object. A discount object is used to help determine
     * things like how much a customer will pay for a specific product, which is then
     * used to calculate discount.
     */
    public DiscountObject(Customer purchaser, Product product) {
        this.purchaser = purchaser;
        this.product = product;

        /*
         * Do not assume any sales prices are better than original by default.
         */
        Double bestPrice = this.product.getFullPrice();

        // If a is sale running
        if (this.product.getSalePrice() != null && this.product.getSalePrice() < this.product.getFullPrice()) {
            bestPrice = this.product.getSalePrice();
        }

        // If a user has a tier and the product has a price for that tier
        if (this.purchaser.getTier() == 1 &&
            this.product.getTierOnePrice() != null &&
            this.product.getTierOnePrice() < this.product.getFullPrice()) {
            bestPrice = this.product.getTierOnePrice();
        } else if (this.purchaser.getTier() == 2 &&
            this.product.getTierTwoPrice() != null &&
            this.product.getTierTwoPrice() < this.product.getFullPrice()) {
            bestPrice = this.product.getTierTwoPrice();
        }


        this.truePrice = bestPrice;
        this.fullPrice = this.product.getFullPrice();
        this.discount = this.fullPrice - this.truePrice;
    }

    public Double getFullPrice() {
        return this.fullPrice;
    }

    public Double getPurchasedPrice() {
        return this.truePrice;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public String toString() {
        return "" + this.purchaser.getName() + " purchased " + this.product.getName() +
            " with price " + this.truePrice + " with full item price " + this.fullPrice ;
    }

}
