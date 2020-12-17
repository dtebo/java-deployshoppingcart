package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.ShoppingCartApplicationTest;
import com.lambdaschool.shoppingcart.models.CartItem;
import com.lambdaschool.shoppingcart.models.Product;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.repository.CartItemRepository;
import com.lambdaschool.shoppingcart.repository.ProductRepository;
import com.lambdaschool.shoppingcart.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartApplicationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartItemServiceImplTestWithDB {
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void a_addToCart() {
        String itemName = "PEN";
        User cartUser = userService.findUserById(1L);
        Product product = productService.findProductById(1L);

        CartItem addToCart = cartItemService.addToCart(cartUser.getUserid(),
                product.getProductid(),
                cartUser.getComments());

        addToCart.setQuantity(addToCart.getQuantity() + 1);
        if(cartUser.getComments() != null){
            addToCart.setComments(cartUser.getComments());
        }

        assertNotNull(addToCart);
        assertEquals(itemName,
                addToCart.getProduct().getName());

    }

    @Test
    public void b_removeFromCart() {
        String itemName = "PEN";
        User cartUser = userService.findUserById(1L);
        Product product = productService.findProductById(1L);

        CartItem removeFromCart = cartItemService.removeFromCart(cartUser.getUserid(),
                product.getProductid(),
                cartUser.getComments());

        removeFromCart.setQuantity(removeFromCart.getQuantity() - 1);
        if(cartUser.getComments() != null){
            removeFromCart.setComments(cartUser.getComments());
        }

        assertNotNull(removeFromCart);
        assertEquals(3,
                removeFromCart.getQuantity());
    }
}