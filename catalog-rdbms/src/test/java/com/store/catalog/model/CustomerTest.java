package com.store.catalog.model;

import org.junit.Test;

import com.store.catalog.common.exception.CheckException;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

/**
 * Created by zouheir on 04/12/2016.
 */
public class CustomerTest {
		
    @Test (expected = CheckException.class)
    public void testNullPassword() throws Exception {
    	Customer customer = new Customer();
    	customer.setPassword(null);
    }

    @Test (expected = CheckException.class)
    public void testVidePassword() throws Exception {
    	Customer customer = new Customer();
    	customer.setPassword("");
    }

    @Test (expected = CheckException.class)
    public void testSamePassword() throws Exception {
    	Customer customer = new Customer();
    	customer.setPassword("password1");
    	customer.setPassword("password1");
    }
    

}