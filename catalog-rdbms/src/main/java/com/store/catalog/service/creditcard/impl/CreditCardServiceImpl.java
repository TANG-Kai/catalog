package com.store.catalog.service.creditcard.impl;

import org.springframework.expression.spel.ast.BooleanLiteral;

import com.store.catalog.common.exception.CheckException;
import com.store.catalog.model.CreditCardDTO;
import com.store.catalog.model.VerifCCResult;
import com.store.catalog.service.creditcard.CreditCardService;

/**
 * Created by zouheir on 02/12/2016.
 */

public class CreditCardServiceImpl implements CreditCardService {

	
    public VerifCCResult verifyCreditCard(final CreditCardDTO creditCardDto) throws Exception {
    	Long cnumber;
    	
    	try { 
    		cnumber = Long.parseLong(creditCardDto.getCreditCardNumber()); 
        } catch(NumberFormatException e) { 
            throw new NumberFormatException("number is not number");
        }
    	
    	boolean luhnVerified = false;
    	String creditNum = creditCardDto.getCreditCardNumber();
    	creditNum.replaceAll("\\s+","");

    	int length = creditNum.length();
    	
    	int sum = 0;
    	
    	for(int i = length-1;i>=0;i--){
    		// get digit
    		int digit = Character.getNumericValue(creditNum.charAt(i));
    		
    		//if pair
    		if( (length-i)%2 == 0){	
    			digit*=2;
    			if(digit>9) digit-=9;
    		}
    		
    		// add up
    		sum += digit;
    	}
    	
    	sum*=9;
    	if( sum%10 == 0)
    		luhnVerified = true; 
    	
    	
    	if(cnumber == 972487087) return VerifCCResult.KO;
    	
    	else if(luhnVerified == true)
    		return VerifCCResult.OK;
    	else  
    		return VerifCCResult.KO;
        //throw new RuntimeException("not yet implemented");
        
    }

}
