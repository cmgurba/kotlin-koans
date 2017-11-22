package ii_collections

fun example6() {
    listOf(1, 3).sum() == 4
    listOf("a", "b", "cc").sumBy { it.length } == 4
}

fun Customer.getTotalOrderPrice(): Double {
    // Return the sum of prices of all products that a customer has ordered.
    // Note: a customer may order the same product for several times.
    // orders may have multiple products, but also different orders
    // can have the same product.  flatmap of products goes over all products
    // while orderedProducts is a set.
    return orders.flatMap { it.products }.sumByDouble { it.price }
}
