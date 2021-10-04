package com.example.onlineshoppostgres.service;

import com.example.onlineshoppostgres.model.Cart;
import com.example.onlineshoppostgres.model.Category;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.CartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CartServiceTest {

    private static final Product PRODUCT = new Product( "noName", BigDecimal.TEN, "description", Category.COSMETICS);

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    Cart cart = new Cart();

    @BeforeEach
    void setNewCart() {
        cart = new Cart();
    }



    private List<Cart> prepareMockData(){
       List<Cart> cartItems = new ArrayList<>();
        cartItems.add(new Cart(new Product("shampoo", new BigDecimal("13.5"), "refresh shampoo", Category.COSMETICS),1));
        cartItems.add(new Cart(new Product("milk", new BigDecimal("2.5"), "delicious milk", Category.GROCERIES), 1));
        cartItems.add(new Cart(new Product("apple", new BigDecimal("1.5"), "fresh apple", Category.FRUITS), 1));
        return cartItems;
    }

    @Test
    void testGetAllProductFromCart(){
        BDDMockito.given(cartRepository.findAll()).willReturn(prepareMockData());

       Assertions.assertEquals(cartService.getAllProductsFromCart().size(), 3);
    }

    @Test
    void testAddProductToCart(){

        Map<Product, Integer> cartItems = new HashMap<>();
        when(cartRepository.save(any())).thenReturn(cartItems.put(PRODUCT, 1));
        cartService.addProductToCart(PRODUCT, 1);
        Assertions.assertEquals( cartItems.size(), 1);
    }

    @Test
    public void testGetTotal(){

        when(cartRepository.findAll()).thenReturn(prepareMockData());
       BigDecimal result = cartService.getTotal();
       assertThat(result).isEqualByComparingTo(new BigDecimal("17.5"));
    }

    @Test
    public void testGetTotalWithQuantityZero(){
        //given
        List<Cart> expected = new ArrayList<>();
        expected.add(new Cart(new Product("", new BigDecimal("13.5"), "", Category.COSMETICS),0));
        expected.add(new Cart(new Product("", new BigDecimal("2.5"), "", Category.GROCERIES), 0));
        expected.add(new Cart(new Product("", new BigDecimal("1.5"), "", Category.FRUITS), 0));
        when(cartRepository.findAll()).thenReturn(expected);
        //when

        BigDecimal result = cartService.getTotal();
       //then
        assertThat(result).isEqualByComparingTo(new BigDecimal(0));
    }

    @Test
    public void testGetTotalWithName(){
        //given
        List<Cart> cartItems = new ArrayList<>();
        cartItems.add(new Cart(new Product("Shampoo", new BigDecimal("13.5"), "", Category.COSMETICS),1));
        cartItems.add(new Cart(new Product("Milk    ", new BigDecimal("2.5"), "", Category.GROCERIES), 1));
        cartItems.add(new Cart(new Product("Apple", new BigDecimal("1.5"), "", Category.FRUITS), 1));
        when(cartRepository.findAll()).thenReturn(cartItems);
        //when
        BigDecimal result = cartService.getTotal();
        assertThat(result).isEqualByComparingTo(new BigDecimal("17.5"));
    }

    @Test
    public void testGetTotalSpecialOffer(){
        List<Cart> cartItems = new ArrayList<>();
        cartItems.add(new Cart(new Product("", new BigDecimal(20), "", Category.COSMETICS),2));
        when(cartRepository.findAll()).thenReturn(cartItems);
        BigDecimal result = cartService.specialOffer(0.8);
        assertThat(result).isEqualByComparingTo(new BigDecimal("32.0"));
    }

    @Test
    public void testGetTotalSpecialOfferWithQuantityZero(){
        List<Cart> cartItems = new ArrayList<>();
        cartItems.add(new Cart(new Product("", new BigDecimal(20), "", Category.COSMETICS),0));
        when(cartRepository.findAll()).thenReturn(cartItems);
        BigDecimal result = cartService.specialOffer(0.8);
        assertThat(result).isEqualByComparingTo(new BigDecimal(0));
    }

}