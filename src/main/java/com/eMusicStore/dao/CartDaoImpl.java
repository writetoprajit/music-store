package com.eMusicStore.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eMusicStore.model.Cart;

@Repository
public class CartDaoImpl implements CartDao {

	private Map<String, Cart> listOfCarts;

	public CartDaoImpl() {
		listOfCarts = new HashMap<String, Cart>();
	}

	public Cart create(Cart cart) {
		if (listOfCarts.keySet().contains(cart.getCartId())) {
			throw new IllegalArgumentException(String.format(
					"Can not create a cart. A cart with the given id(%) " + "already " + "exists", cart.getCartId()));
		}
		
		listOfCarts.put(Integer.toString(cart.getCartId()), cart);

		return cart;
	}

	public Cart read(int cartId) {
		return listOfCarts.get(cartId);
	}

	public void update(int cartId, Cart cart) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format(
					"Can not update cart. The cart with the given id(%) " + "does not " + "exist", cart.getCartId()));
		}

		listOfCarts.put(Integer.toString(cartId), cart);
	}

	public void delete(int cartId) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(
					String.format("Can not delete cart. A cart with the given id(%) " + "does not " + "exist", cartId));
		}

		listOfCarts.remove(cartId);
	}
}
