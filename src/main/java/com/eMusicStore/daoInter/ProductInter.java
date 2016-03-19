
package com.eMusicStore.daoInter;

import java.util.List;

import com.eMusicStore.model.Product;


public interface ProductInter {

	public void addProduct(Product product);

	public Product getProductById(int productId);

	public List<Product> getAllProduct();

	public void deleteProduct(int productId);

}
