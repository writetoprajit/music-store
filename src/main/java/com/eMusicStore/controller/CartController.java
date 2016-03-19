package com.eMusicStore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.eMusicStore.dao.CartDao;
import com.eMusicStore.dao.ProductDao;
import com.eMusicStore.model.Cart;
import com.eMusicStore.model.CartItem;
import com.eMusicStore.model.Product;

@Controller
@RequestMapping("/rest/cart")
public class CartController {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private ProductDao productDao;

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value = "cartId") int cartId) {

		return cartDao.read(cartId);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable(value = "cartId") int cartId, @RequestBody Cart cart) {

		cartDao.update(cartId, cart);
	}

	@RequestMapping(value = "/{cartId)", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "cartId") int cartId) {
		cartDao.delete(cartId);
	}

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable(value = "productId") int productId, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();

		Cart cart = cartDao.read(Integer.parseInt("sessionId"));

		if (cart == null) {
			cart = cartDao.create(new Cart(Integer.parseInt("sessionId")));
		}

		Product product = productDao.getProductById(productId);
		if (product == null) {
			throw new IllegalArgumentException(new Exception());
		}

		cart.addCartItem(new CartItem(product));

		cartDao.update(Integer.parseInt("sessionId"), cart);
	}

	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable int productId, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();
		Cart cart = cartDao.read(Integer.parseInt("sessionId"));

		if (cart == null) {
			cart = cartDao.create(new Cart(Integer.parseInt("sessionId")));
		}

		Product product = productDao.getProductById(productId);
		if (product == null) {
			throw new IllegalArgumentException(new Exception());
		}

		cart.removeCartItem(new CartItem(product));

		cartDao.update(Integer.parseInt("sessionId"), cart);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload")
	public void handleClientErrors(Exception e) {
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server")
	public void handleServerErrors(Exception e) {
	}

}
