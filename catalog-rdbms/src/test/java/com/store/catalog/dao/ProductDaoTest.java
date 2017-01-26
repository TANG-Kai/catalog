package com.store.catalog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.store.catalog.model.Category;
import com.store.catalog.model.Item;
import com.store.catalog.model.Product;
import com.store.catalog.utils.ConstantUtils;

import static com.store.catalog.utils.ConstantUtils.*;

public class ProductDaoTest extends AbstractBaseDaoTestCase {

    
    @Autowired
    private ProductDao productDao ;
    
    @Autowired
    private CategoryDao categoryDao ;    
    
    private Product product = null;
    
    
    @Before
    public void setUp(){
    	loadProduct();
    }
    
    @After
    public void tearDown(){
    	categoryDao = null;
    	productDao = null;
    }

    @Test
    public void testCreateProduct() throws Exception {
    	productDao.save(product);
    	assertTrue("primary key assigned", product.getId()!= null);
    }    
   
    @Test
    public void testUpdateProduct() throws Exception {
    	productDao.save(product);
    	product.setName(ConstantUtils.PRODUCT_NAME+ "MDF");
    	product.setDescription(ConstantUtils.PRODUCT_DESCRIPTION+ "MDF");
    	productDao.save(product);    	
    	
    	Product catMdf = productDao.findOne(product.getId());
    	
    	assertNotNull(catMdf);
    	assertEquals(product, catMdf);
    }    
    
    
    @Test
    public void testGetProduct() throws Exception {
    	productDao.save(product);
    	Product cat = productDao.findOne(product.getId());
    	

    	assertNotNull(cat);
    	assertEquals(product, cat);
    }   

    
    @Test
    public void testRemoveProduct() throws Exception {
    	productDao.save(product);
    	Iterable<Product> lst = productDao.findAll();
    	
    	assertTrue(getIterableSize(lst) == 1);
    	
    	Product cat2 = new Product();
        cat2.setId(new Random().nextLong());
        cat2.setCategory(getCategory2());
    	cat2.setName(ConstantUtils.PRODUCT_NAME + "2");
    	cat2.setDescription(ConstantUtils.CATEGORY_DESCRIPTION + "2");
    	
    	
    	productDao.save(cat2);
    	
    	lst = productDao.findAll();

    	assertTrue(getIterableSize(lst) == 2);
    	
    	productDao.delete(product);
    	
    	lst = productDao.findAll();

    	assertTrue(getIterableSize(lst) == 1);
    	

    	productDao.delete(cat2);
    	
    	lst = productDao.findAll();

    	assertTrue(getIterableSize(lst) == 0);
    }

    
    
    @Test
    public void testGetProducts() throws Exception {
    	productDao.save(product);
    	Iterable<Product> lst = productDao.findAll();
    	
    	assertTrue(getIterableSize(lst) == 1);
    	
    	Product cat2 = new Product();
        cat2.setId(new Random().nextLong());
        cat2.setCategory(getCategory2());
    	cat2.setName(ConstantUtils.PRODUCT_NAME + "2");
    	cat2.setDescription(ConstantUtils.CATEGORY_DESCRIPTION + "2");
    	
    	
    	productDao.save(cat2);
    	
    	lst = productDao.findAll();

    	assertTrue(getIterableSize(lst) == 2);
    }    
    

    @Test
    public void testGetProductsWithCategoryId() throws Exception {
    	productDao.save(product);
    	Iterable<Product> lst = productDao.findAll();
    	
    	assertTrue(getIterableSize(lst) == 1);
    	
    	Product cat2 = new Product();
        cat2.setId(new Random().nextLong());
        cat2.setCategory(getCategory2());
    	cat2.setName(ConstantUtils.PRODUCT_NAME + "2");
    	cat2.setDescription(ConstantUtils.CATEGORY_DESCRIPTION + "2");
    	Product cat3 = new Product();
        cat3.setId(new Random().nextLong());
        cat3.setCategory(getCategory2());
    	cat3.setName(ConstantUtils.PRODUCT_NAME + "3");
    	cat3.setDescription(ConstantUtils.CATEGORY_DESCRIPTION + "3");
    	
    	
    	productDao.save(cat2);
    	productDao.save(cat3);
    	
    	lst = productDao.findAll();

    	assertTrue(getIterableSize(lst) == 3);
    	
    	lst = productDao.findByCategoryId(product.getCategory().getId());

    	assertTrue(getIterableSize(lst) == 1);
    	
    	//TODO: How to access the content of this list?
    	
    	
    }    

    
    @Test
    public void testGetProductsByCategoryName() throws Exception {
    	productDao.save(product);
    	Iterable<Product> lst = productDao.findAll();
    	
    	assertTrue(getIterableSize(lst) == 1);
    	
    	Product cat2 = new Product();
        cat2.setId(new Random().nextLong());
        cat2.setCategory(getCategory2());
    	cat2.setName(ConstantUtils.PRODUCT_NAME + "2");
    	cat2.setDescription(ConstantUtils.CATEGORY_DESCRIPTION + "2");
    	Product cat3 = new Product();
        cat3.setId(new Random().nextLong());
        cat3.setCategory(getCategory2());
    	cat3.setName(ConstantUtils.PRODUCT_NAME + "3");
    	cat3.setDescription(ConstantUtils.CATEGORY_DESCRIPTION + "3");
    	
    	
    	productDao.save(cat2);
    	productDao.save(cat3);

    	
    	lst = productDao.findAll();

    	assertTrue(getIterableSize(lst) == 3);
    	
    	lst = productDao.findByCategoryName(product.getCategory().getName());

    	assertTrue(getIterableSize(lst) == 1);
    }        
	
	
	private Category getCategory() {
		Category category = new Category();
        category.setId(new Random().nextLong());
        category.setName(CATEGOY_NAME);
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryDao.save(category);
		return category;
	}    
	
	private Category getCategory2() {
		Category category = new Category();
        category.setId(new Random().nextLong());
        category.setName("catName2");
        category.setDescription("description2");

        categoryDao.save(category);
		return category;
	}    
    
	
    /**
     * 
     * create an instanciated object. The one declared as private field in the test class
     */	
	private void loadProduct() {
    	product = new Product();
        product.setId(new Random().nextLong());
    	product.setName(PRODUCT_NAME);
    	product.setDescription(PRODUCT_DESCRIPTION);
    	product.setCategory(getCategory());
	}
	
}
