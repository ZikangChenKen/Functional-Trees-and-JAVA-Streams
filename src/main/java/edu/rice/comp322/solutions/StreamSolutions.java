package edu.rice.comp322.solutions;

import edu.rice.comp322.provided.streams.models.Customer;
import edu.rice.comp322.provided.streams.models.Order;
import edu.rice.comp322.provided.streams.models.Product;
import edu.rice.comp322.provided.streams.provided.DiscountObject;
import edu.rice.comp322.provided.streams.repos.CustomerRepo;
import edu.rice.comp322.provided.streams.repos.OrderRepo;
import edu.rice.comp322.provided.streams.repos.ProductRepo;

import java.util.*;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * A class containing all of the students implemented solutions to the stream problems.
 */
public class StreamSolutions {

    /**
     * Use this function with the appropriate streams solution test to ensure repos load correctly.
     */
    public static List<Long> problemZeroSeq(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        List<Long> counts = new ArrayList<>();
        counts.add(customerRepo.findAll().stream().count());
        counts.add(orderRepo.findAll().stream().count());
        counts.add(productRepo.findAll().stream().count());

        return counts;
    }

    /**
     * Use this function with the appropriate streams solution test to ensure repos load correctly.
     */
    public static List<Long> problemZeroPar(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        List<Long> counts = new ArrayList<>();
        counts.add(customerRepo.findAll().parallelStream().count());
        counts.add(orderRepo.findAll().parallelStream().count());
        counts.add(productRepo.findAll().parallelStream().count());

        return counts;
    }

    // TODO: Implement problem one using sequential streams

    /**
     * Calculate the companies maximum possible revenue from all online sales during the month of
     * February (Sequential).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return the companies maximum possible revenue from all online sales during the month of February
     */
    public static Double problemOneSeq(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        Double result =
                orderRepo.findAll().stream()
                        // filter the month
                        .filter(x -> x.getOrderDate().getMonthValue() == 2)
                        // get the total price
                        .flatMap(x -> x.getProducts().stream()).mapToDouble(x -> x.getFullPrice()).sum();

        return result;
    }

    // TODO: Implement problem one using parallel streams
    /**
     * Calculate the companies maximum possible revenue from all online sales during the month of
     * February (Parallel).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return the companies maximum possible revenue from all online sales during the month of February
     */
    public static Double problemOnePar(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        Double result =
                orderRepo.findAll().stream().parallel()
                        // filter the month
                        .filter(x -> x.getOrderDate().getMonthValue() == 2)
                        // get the total price
                        .flatMap(x -> x.getProducts().parallelStream()).mapToDouble(x -> x.getFullPrice()).sum();

        return result;
    }

    // TODO: Implement problem two using sequential streams

    /**
     * Get the order IDs of the 5 most recently placed orders (Sequential).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return the order IDs of the 5 most recently placed orders
     */
    public static Set<Long> problemTwoSeq(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return orderRepo.findAll().stream()
                // reverse the orderDate to get the most recent orders
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                // limit the orders to 5
                .limit(5)
                .map(x -> x.getId())
                .collect(Collectors.toSet());
    }

    // TODO: Implement problem two using parallel streams
    /**
     * Get the order IDs of the 5 most recently placed orders (Parallel).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return the order IDs of the 5 most recently placed orders
     */
    public static Set<Long> problemTwoPar(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return orderRepo.findAll().stream().parallel()
                // reverse the orderDate to get the most recent orders
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                // limit the orders to 5
                .limit(5)
                .map(x -> x.getId())
                .collect(Collectors.toSet());
    }

    // TODO: Implement problem three using sequential streams

    /**
     * Count the number of distinct customers making purchases (Sequential).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return the number of distinct customers making purchases
     */
    public static Long problemThreeSeq(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return orderRepo.findAll().stream()
                .map(x -> x.getCustomer())
                // using distinct() to get the distinct customers
                .distinct()
                .count();
    }

    // TODO: Implement problem three using parallel streams
    /**
     * Count the number of distinct customers making purchases (Parallel).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return the number of distinct customers making purchases
     */
    public static Long problemThreePar(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return orderRepo.findAll().stream().parallel()
                .map(x -> x.getCustomer())
                // using distinct() to get the distinct customers
                .distinct()
                .count();
    }

    // TODO: Implement problem four using sequential streams

    /**
     * Calculate the total discount for all orders placed in March 2021 (Sequential).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return the total discount for all orders placed in March 2021
     */
    public static Double problemFourSeq(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return orderRepo.findAll().stream()
                // filter the year and month
                .filter(x -> x.getOrderDate().getMonthValue() == 3)
                // using discountObject to get the discount
                .mapToDouble(x -> x.getProducts().stream().mapToDouble(f -> new DiscountObject(x.getCustomer(), f)
                        .getDiscount()).sum())
                .sum();

    }

    // TODO: Implement problem four using parallel streams
    /**
     * Calculate the total discount for all orders placed in March 2021 (Parallel).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return the total discount for all orders placed in March 2021
     */
    public static Double problemFourPar(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return orderRepo.findAll().stream().parallel()
                // filter the year and month
                .filter(x -> x.getOrderDate().getMonthValue() == 3 && x.getOrderDate().getYear() == 2021)
                // using discountObject to get the discount
                .mapToDouble(x -> x.getProducts().parallelStream().mapToDouble(f ->
                        new DiscountObject(x.getCustomer(), f)
                        .getDiscount()).sum())
                .sum();
    }

    // TODO: Implement problem five using sequential streams

    /**
     * Create a mapping between customers IDs and the total dollar amount they spent on products (Sequential).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return a mapping between customers IDs and the total dollar amount they spent on products
     */
    public static Map<Long, Double> problemFiveSeq(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return orderRepo.findAll().stream()
                // using toMap to create the mapping
                .collect(Collectors.toMap(x -> x.getCustomer().getId(), f -> f.getProducts().stream()
                        .mapToDouble(y -> y.getFullPrice()).sum(), Double::sum));
    }

    // TODO: Implement problem five using parallel streams
    /**
     * Create a mapping between customers IDs and the total dollar amount they spent on products (Parallel).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return a mapping between customers IDs and the total dollar amount they spent on products
     */
    public static Map<Long, Double> problemFivePar(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return orderRepo.findAll().stream().parallel()
                // using toMap to create the mapping
                .collect(Collectors.toMap(x -> x.getCustomer().getId(), f -> f.getProducts().parallelStream()
                        .mapToDouble(y -> y.getFullPrice()).sum(), Double::sum));
    }

    // TODO: Implement problem six using sequential streams

    /**
     * Create a mapping between product categories and the average cost of an item in that category (Sequential).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return a mapping between product categories and the average cost of an item in that category
     */
    public static Map<String, Double> problemSixSeq(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return productRepo.findAll().stream()
                // using averagingDouble to calculate the average price
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(Product::getFullPrice)));
    }

    // TODO: Implement problem six using parallel streams
    /**
     * Create a mapping between product categories and the average cost of an item in that category (Parallel).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return a mapping between product categories and the average cost of an item in that category
     */
    public static Map<String, Double> problemSixPar(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return productRepo.findAll().stream().parallel()
                // using averagingDouble to calculate the average price
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(Product::getFullPrice)));
    }

    // TODO: Implement problem seven using sequential streams
    /**
     * Create a mapping between products IDs in the tech category
     * and the IDs of the customers which ordered them (Sequential).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return a mapping between products IDs in the tech category and the IDs of
     * the customers which ordered them.
     */
    public static Map<Long, Set<Long>> problemSevenSeq(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return productRepo.findAll().stream()
                // filter the products that have category tech
                .filter(x -> x.getCategory().equals("Tech"))
                .collect(Collectors.toMap(a -> a.getId(), b -> b.getOrders().stream()
                        .map(c -> c.getCustomer().getId())
                        .collect(Collectors.toSet())));
    }

    // TODO: Implement problem seven using parallel streams
    /**
     * Create a mapping between products IDs in the tech category
     * and the IDs of the customers which ordered them (Parallel).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return a mapping between products IDs in the tech category and the IDs of
     * the customers which ordered them.
     */
    public static Map<Long, Set<Long>> problemSevenPar(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        return productRepo.findAll().stream().parallel()
                // filter the products that have category tech
                .filter(x -> x.getCategory().equals("Tech"))
                .collect(Collectors.toMap(a -> a.getId(), b -> b.getOrders().parallelStream()
                        .map(c -> c.getCustomer().getId())
                        .collect(Collectors.toSet())));
    }

    // TODO: Implement problem eight using sequential streams
    /**
     * Create a mapping between the IDs of customers without membership tiers and their sales
     * utilization rate (Sequential).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return a mapping between the IDs of customers without membership tiers and their sales utilization rate
     */
    public static Map<Long, Double> problemEightSeq(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        // create a mapping between customer ID and the total products they bought
        var sum = orderRepo.findAll().stream()
                .filter(a -> a.getCustomer().getTier().equals(0))
                .collect(Collectors.groupingBy(b -> b.getCustomer().getId(),
                        Collectors.summingDouble(b -> b.getProducts().size())));
        // using the count of products that get discount divide by the total products to calculate the utilization
        return orderRepo.findAll().stream()
                .filter(a -> a.getCustomer().getTier().equals(0))
                .collect(Collectors.groupingBy(c -> c.getCustomer().getId(),
                        Collectors.summingDouble(c -> c.getProducts().stream()
                                .filter(f -> new DiscountObject(c.getCustomer(), f).getDiscount() != 0)
                                .count() / sum.get(c.getCustomer().getId()))));
    }

    // TODO: Implement problem eight using parallel streams
    /**
     * Create a mapping between the IDs of customers without membership tiers and their sales
     * utilization rate (Parallel).
     * @param customerRepo customer repository
     * @param orderRepo order repository
     * @param productRepo product repository
     * @return a mapping between the IDs of customers without membership tiers and their sales utilization rate
     */
    public static Map<Long, Double> problemEightPar(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
        // create a mapping between customer ID and the total products they bought
        var sum = orderRepo.findAll().parallelStream()
                .filter(a -> a.getCustomer().getTier().equals(0))
                .collect(Collectors.groupingBy(b -> b.getCustomer().getId(),
                        Collectors.summingDouble(b -> b.getProducts().size())));
        // using the count of products that get discount divide by the total products to calculate the utilization
        return orderRepo.findAll().parallelStream()
                .filter(a -> a.getCustomer().getTier().equals(0))
                .collect(Collectors.groupingBy(c -> c.getCustomer().getId(),
                        Collectors.summingDouble(c -> c.getProducts().parallelStream()
                                .filter(f -> new DiscountObject(c.getCustomer(), f).getDiscount() != 0)
                                .count() / sum.get(c.getCustomer().getId()))));
    }
}
