package com.eMusicStore.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private int cartId;
	private Map<String, CartItem> cartItems;

	private double grandTotal;
	private CartItem item;

	private Cart() {
		cartItems = new HashMap<String, CartItem>();
		grandTotal = 0;

	}

	public Cart(int cartId) {
		this();
		this.cartId = cartId;

	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public void addCartItem(CartItem item) {
	
		
		int produc= item.getProduct().getProductId();
		String productId =Integer.toString(produc);
		
		if (cartItems.containsKey(productId)) {
			CartItem existingCartItem = cartItems.get(productId);
			existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
			
			cartItems.put(productId, existingCartItem);
		} else {

			cartItems.put(productId, item);

		}

		updateGrandTotal();

	}

	public void removeCartItem (CartItem item){

		int produc= item.getProduct().getProductId();
		String productId =Integer.toString(produc);
	cartItems.remove(productId);
	updateGrandTotal();
}

public void updateGrandTotal(){
	grandTotal=0;
	for(CartItem item:cartItems.values()){
		grandTotal=grandTotal+item.getTotalPrice();
	}
	
	
}
	
	
	
}
