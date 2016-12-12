package com.store.catalog.service.creditcard.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.postgresql.core.SetupQueryRunner;

import com.store.catalog.common.exception.CheckException;
import com.store.catalog.model.CreditCardDTO;
import com.store.catalog.model.VerifCCResult;
import com.store.catalog.service.creditcard.CreditCardService;

import static org.junit.Assert.*;

import org.aspectj.lang.annotation.Before;

/**
 * Created by zouheir on 02/12/2016.
 */
public class CreditCardServiceImplTest {

	private CreditCardService ccs= new CreditCardServiceImpl();;
	
	@Test(expected = NumberFormatException.class)
	public void testNumberIsNumber() throws Exception{
		CreditCardDTO dto = new CreditCardDTO();
		dto.setCreditCardNumber("ABBCD");
		ccs.verifyCreditCard(dto);
		
	}

	@Test
	public void testNumberIs0() throws Exception{
		CreditCardDTO dto = new CreditCardDTO();
		dto.setCreditCardNumber("0");
		ccs.verifyCreditCard(dto);
		assertTrue("returned is not 0 when set 0",ccs.verifyCreditCard(dto) == VerifCCResult.OK);
	}
	
	@Test
	public void testNumberIs34() throws Exception{
		CreditCardDTO dto = new CreditCardDTO();
		dto.setCreditCardNumber("34");
		ccs.verifyCreditCard(dto);
		assertTrue("not valid when set 34",ccs.verifyCreditCard(dto) == VerifCCResult.OK);
	}
	@Test
	public void testNumberIs042() throws Exception{
		CreditCardDTO dto = new CreditCardDTO();
		dto.setCreditCardNumber("042");
		ccs.verifyCreditCard(dto);
		assertTrue("not valid when set 042",ccs.verifyCreditCard(dto) == VerifCCResult.OK);
	}

	@Test
	public void testNumberIs972487086() throws Exception{
		CreditCardDTO dto = new CreditCardDTO();
		dto.setCreditCardNumber("972487086");
		ccs.verifyCreditCard(dto);
		assertTrue("not valid when set 972487086",ccs.verifyCreditCard(dto) == VerifCCResult.OK);
	}
	
	

	@Test
	public void testNumberIs972487087() throws Exception{
		CreditCardDTO dto = new CreditCardDTO();
		dto.setCreditCardNumber("972487087");
		ccs.verifyCreditCard(dto);
		assertTrue("not valid when set 972487087",ccs.verifyCreditCard(dto) == VerifCCResult.KO);
	}
	
	@Test
	public void testNumberPassLuhn() throws Exception{
		CreditCardDTO dto = new CreditCardDTO();
		dto.setCreditCardNumber("4563960122001999");
		ccs.verifyCreditCard(dto);
		assertTrue("not valid when set 4563 9601 2200 1999",ccs.verifyCreditCard(dto) == VerifCCResult.OK);
	}
	
	@Test
	public void testNumberPassNotLuhn() throws Exception{
		CreditCardDTO dto = new CreditCardDTO();
		dto.setCreditCardNumber("4563960122001997");
		ccs.verifyCreditCard(dto);
		assertTrue("not valid when set 4563 9601 2200 1997",ccs.verifyCreditCard(dto) == VerifCCResult.KO);
	}
	
    @Test
    @Ignore
    public void testVerifyCreditCard() throws Exception {
        throw new Exception("not yet implemented");
    }
}
