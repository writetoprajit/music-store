package com.eMusicStore.dao;

import com.eMusicStore.model.Cart;

public interface CartDao {
	Cart create (Cart cart);
	
	Cart read(int cartId);
	
	void update(int cartId,Cart cart);
	void delete(int cartId);
}
