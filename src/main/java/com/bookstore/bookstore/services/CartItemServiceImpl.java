package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.*;
import com.bookstore.bookstore.repositories.BookToCartItemRepository;
import com.bookstore.bookstore.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookToCartItemRepository bookToCartItemRepository;

    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart){
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    public CartItem updateCartItem(CartItem cartItem) {
        BigDecimal bigDecimal = new BigDecimal(cartItem.getBook().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));

        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        cartItem.setItemSubtotal(bigDecimal);

        cartItemRepository.save(cartItem);

        return cartItem;
    }

    public CartItem addBookToCartItem(Book book, User user, int qty){
        List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());

        for (CartItem cartItem : cartItemList){
            if(book.getId() == cartItem.getBook().getId()){
                cartItem.setQty(cartItem.getQty() + qty);
                cartItem.setItemSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
                cartItemRepository.save(cartItem);
                return cartItem;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setBook(book);

        cartItem.setQty(qty);
        cartItem.setItemSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
        cartItem = cartItemRepository.save(cartItem);

        BookToCartItem bookToCartItem = new BookToCartItem();
        bookToCartItem.setBook(book);
        bookToCartItem.setCartItem(cartItem);
        bookToCartItemRepository.save(bookToCartItem);

        return cartItem;
    }

    public CartItem findById(Long id){
        return cartItemRepository.findById(id).orElse(null);
    }

    public void removeCartItem(CartItem cartItem){
        bookToCartItemRepository.deleteByCartItem(cartItem);
        cartItemRepository.delete(cartItem);
    }
}
