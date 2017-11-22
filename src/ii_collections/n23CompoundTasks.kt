package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
    // get orders containing product
    // get customers of orders
    // better way that tutorial offered:
    // return customers.filter { it.orderedProducts.contains(product) }.toSet()
    return customers.filter { it.orders.flatMap { it.products}.contains(product) }.toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
    // get all delivered orders (nullable)
    // better way that tutorial offered:
    // return orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }
    return orders.flatMap { if (it.isDelivered) it.products else emptyList() }.maxBy { it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
    // item for every order + product, chaining flatmap.. count takes a lambda that can
    // evaluate to true/false
    return customers.flatMap { it.orders }.flatMap { it.products }.count { it == product }
}
