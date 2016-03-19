package com.eMusicStore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eMusicStore.daoInter.ProductInter;
import com.eMusicStore.model.Product;

@Transactional
@Repository("productdao")
public class ProductDao implements ProductInter {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();

	}

	@Override
	public Product getProductById(int productId) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, productId);
		return product;
	}

	@Override
	public List<Product> getAllProduct() {
		Session session = sessionFactory.getCurrentSession();
	
		Query query = session.createQuery("from Product");
		List<Product> products = query.list();
		session.flush();
		return products;
	}

	@Override
	public void deleteProduct(int productId) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getProductById(productId));
		session.flush();

	}

}