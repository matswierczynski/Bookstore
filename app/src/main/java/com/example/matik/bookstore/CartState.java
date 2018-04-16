package com.example.matik.bookstore;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class CartState {
    private static final CartState ourInstance = new CartState();
    private static final TreeMap<Integer, Integer> booksInCart = new TreeMap<>();
    private static final int DEFAULT_NO_OF_ITEMS = 1;
    static final int MINIMAL_NO_OF_ONE_ITEM_IN_CART = 1;

    static CartState getInstance() {
        return ourInstance;
    }

    private CartState() {
    }

    public void addBook(int bookId) {
        if (booksInCart.containsKey(bookId))
            booksInCart.replace(bookId, booksInCart.get(bookId) + 1);
        else
            booksInCart.put(bookId, DEFAULT_NO_OF_ITEMS);
    }

    public void removeBook(int bookId) {
        if (booksInCart.containsKey(bookId))
            booksInCart.remove(bookId);
    }

    public void decreaseQuantity(int bookId) {
        if (booksInCart.containsKey(bookId) &&
                booksInCart.get(bookId) > MINIMAL_NO_OF_ONE_ITEM_IN_CART)
            booksInCart.replace(bookId, booksInCart.get(bookId) - 1);
    }

    public TreeMap<Integer, Integer> getAll() {
        return new TreeMap<>(booksInCart);
    }

    public double getAllProductsPrice(Context context) {
        List<Double> productsPricesWithQuantity = new ArrayList<>();
        booksInCart.forEach((k, v) ->
                productsPricesWithQuantity.add(
                        AppDatabase.getInstance(context).bookDAO().
                                loadById(k).getPrice() * v));
        return productsPricesWithQuantity.stream().
                mapToDouble(Double::doubleValue).sum();
    }
}
