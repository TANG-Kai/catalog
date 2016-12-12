package com.store.catalog.service.creditcard.impl;

import org.junit.Test;
import org.postgresql.core.SetupQueryRunner;

import com.store.catalog.common.exception.CheckException;
import com.store.catalog.model.CreditCardDTO;
import com.store.catalog.service.creditcard.CreditCardService;

import static org.junit.Assert.*;

import org.aspectj.lang.annotation.Before;

/**
 * Created by zouheir on 02/12/2016.
 */
public class CreditCardServiceImplTest {

	private CreditCardService ccs= new CreditCardServiceImpl();;
	
	@Test(expected = CheckException.class)
	public void testNumberIsNumber() throws Exception{
		CreditCardDTO dto = new CreditCardDTO();
		dto.setCreditCardNumber("ABBCD");
		ccs.verifyCreditCard(dto);
		
	}
	
    @Test
    public void testVerifyCreditCard() throws Exception {
        throw new Exception("not yet implemented");
    }
}
