package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.CartItem;
import com.bookstore.bookstore.models.ShoppingCart;
import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.services.BookService;
import com.bookstore.bookstore.services.CartItemService;
import com.bookstore.bookstore.services.ShoppingCartService;
import com.bookstore.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @RequestMapping("/addItem")
    public String addItem(
            @ModelAttribute("book") Book book,
            @ModelAttribute("qty") String qty,
            Model model, Principal principal
    ){
        User user = userService.findByUsername(principal.getName());
        book = bookService.findOne(book.getId());

        if (Integer.parseInt(qty) > book.getInStockNumber()){
            model.addAttribute("insufficientStock", true);
                    return  "forward:/bookDetail?id="+book.getId();
        }

        CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
        model.addAttribute("addBookSuccess", true);

        return "forward:/bookDetail?id="+book.getId();
    }

    @RequestMapping("/removeItem")
    public String removeItem(@RequestParam("id") Long id) {
        cartItemService.removeCartItem(cartItemService.findById(id));

        return "forward:/shoppingCart/cart";
    }

    @RequestMapping("/cart")
    public String shoppingCart(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", shoppingCart);

        return "shoppingCart";
    }

    @RequestMapping("/updateCartItem")
    public String updateShoppingCart(
        @ModelAttribute("id") Long cartItemId,
        @ModelAttribute("qty") int qty){
        CartItem cartItem = cartItemService.findById(cartItemId);
        cartItem.setQty(qty);
        cartItemService.updateCartItem(cartItem);

        return "forward:/shoppingCart/cart";
    }
}
