package com.store.catalog.service.creditcard.impl;

import com.store.catalog.common.exception.CheckException;
import com.store.catalog.model.CreditCardDTO;
import com.store.catalog.model.VerifCCResult;
import com.store.catalog.service.creditcard.CreditCardService;

/**
 * Created by zouheir on 02/12/2016.
 */
public class CreditCardServiceImpl implements CreditCardService {


    public VerifCCResult verifyCreditCard(final CreditCardDTO creditCardDto) throws CheckException {
    	try { 
            Integer.parseInt(creditCardDto.getCreditCardNumber()); 
        } catch(NumberFormatException e) { 
            throw new CheckException("number is not number");
        } catch(NullPointerException e) {
        	throw new CheckException("number is null");
        }
    	
    	
        throw new RuntimeException("not yet implemented");
        
    }

}
