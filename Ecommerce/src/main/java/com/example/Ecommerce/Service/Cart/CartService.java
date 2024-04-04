package com.example.Ecommerce.Service.Cart;

import com.example.Ecommerce.DTO.Request.CartItemRequest;
import com.example.Ecommerce.Entity.Cart.Cart;
import com.example.Ecommerce.Entity.Cart.CartItem;
import com.example.Ecommerce.Entity.Products.Products;
import com.example.Ecommerce.Repository.Cart.CartItemsRepository;
import com.example.Ecommerce.Repository.Cart.CartRepository;
import com.example.Ecommerce.Repository.ProductRepo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
@Service
public class CartService implements ICartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Transactional
    public ResponseEntity<?> CreateCart(){
        Cart cart = new Cart();
        cart.setCreateAt(LocalDate.now());
        cartRepository.save(cart);
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }
    public ResponseEntity<?> CreateCartItem(CartItemRequest cartItemRequest){
        Optional<Cart> cart = cartRepository.findById(cartItemRequest.getIdCart());
        if(cart.isEmpty()){
            return new ResponseEntity<>("Khong tim thay san pham ",HttpStatus.NOT_FOUND);
        }
        Optional<CartItem> cartItem = cartItemsRepository.GetByCartAndProduct(cartItemRequest.getIdProduct(), cartItemRequest.getIdCart());
        if(cartItem.isPresent()){
            cartItem.get().setQuantity(cartItemRequest.getQuantity()+cartItem.get().getQuantity());
            cartItemsRepository.save(cartItem.get());
            return new ResponseEntity<>("Luu thanh cong ", HttpStatus.OK);
        }
        else {
            CartItem newCartItem = new CartItem();
            newCartItem.setQuantity(cartItemRequest.getQuantity());
            newCartItem.setCart(cart.get());

            newCartItem.setStatus(1);
            Optional<Products> product=productRepository.findById(cartItemRequest.getIdProduct());
            newCartItem.setProduct(product.get());
            cartItemsRepository.save(newCartItem);
            return new ResponseEntity<>("Luu thanh cong ", HttpStatus.OK);
        }
    }
}
