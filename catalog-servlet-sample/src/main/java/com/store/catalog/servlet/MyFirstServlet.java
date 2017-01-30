package com.store.catalog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class MyFirstServlet extends HttpServlet {
	
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
    	HttpServletRequest httpRequest = (HttpServletRequest) servletRequest; 
    	String reqMethod = httpRequest.getMethod();
        PrintWriter writer = servletResponse.getWriter();
    	if(reqMethod.equalsIgnoreCase("POST")){//add using post, supposing that is read like 1+2+3
    		double sum = 0;

			//writer.println(((Double)sum).toString());
			
    		String content = servletRequest.getReader().readLine();


//			writer.println(content);
    		
    		String[] nums = content.split("\\+");
//			writer.println(nums.length);
    		
    		for(String num : nums){ 
    			if(!num.isEmpty())
    				sum+= Double.parseDouble(num);
    		}

			writer.println(sum);
    	}
    	else if(reqMethod.equalsIgnoreCase("GET")){//add using params

    		double sum = 0;
    		
    		Map<String, String[]> params = servletRequest.getParameterMap();
    		
    		
    		for( String[] strlst :  params.values()){
    			for(String str : strlst)
    				sum+= Double.parseDouble(str);
    		}		
    		    		
			writer.println(((Double)sum).toString());    		
    	}
    	else{
    		writer.println("wow what is this huh? should not have happened");
    	}
    }
}
