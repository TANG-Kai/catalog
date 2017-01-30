package com.store.catalog.ws.rest.view.rdbms;

import com.store.catalog.model.ProductDTO;
import com.store.catalog.service.catalog.CatalogService;

import ch.qos.logback.core.status.Status;

import com.store.catalog.model.CategoryDTO;
import com.store.catalog.model.ItemDTO;

import org.postgresql.translation.messages_bg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ZCadi on 26/10/2015.
 */

@Path("/catalog")
public class CatalogResource {



    @Autowired CatalogService catalogServiceImpl;

    public CatalogService getCatalogServiceImpl() {
        return catalogServiceImpl;
    }

    public void setCatalogServiceImpl(CatalogService catalogServiceImpl) {
        this.catalogServiceImpl = catalogServiceImpl;
    }


//TO TEST: use http://localhost:10080/catalog/catalog/categories in navigator
    @Path("/categories")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDTO> getCategories() {
    	return catalogServiceImpl.findCategories();
    }

//TO TEST: http://localhost:10080/catalog/catalog/category/6 in navigator
    @Path("/category/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("id") Long id) throws Exception {
    	  return Response.ok(catalogServiceImpl.findCategory(id)).build();
    }

    
    @Path("/category")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(CategoryDTO categoryDTO) throws Exception {
    	return Response.ok(catalogServiceImpl.createCategory(categoryDTO)).build();

    }

    @Path("/category")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(CategoryDTO categoryDTO) throws Exception {
    	Long id = categoryDTO.getId();
    	if( catalogServiceImpl.findCategory(id)!=null ) 
    	{
    		catalogServiceImpl.updateCategory(categoryDTO);
    		return Response.ok(catalogServiceImpl.findCategory(id)).build();
    	}
    	else
    		return Response.status(Status.ERROR).build();

    }


    @Path("/category/{id}")
    @DELETE
    public Response deleteCategory(@PathParam("id") Long id) throws Exception {
    	if( catalogServiceImpl.findCategory(id)!=null ) 
    	{
    		catalogServiceImpl.deleteCategory(id);;
    		return Response.ok().build();
    	}
    	else
    		return Response.status(Status.ERROR).build();
    	

    }


    public Response getProducts() throws Exception {

    	return null;
    }


    public Response getProduct(@PathParam("id") Long id) throws Exception {

    	return null;
    }


    public Response createProduct(ProductDTO productDTO) throws Exception {

    	return null;
    }


    public Response updateProduct(ProductDTO productDTO) throws Exception {

    	return null;

    }


    public Response deleteProduct(@PathParam("id") Long id) throws Exception {

    	return null;

    }


    public Response getItems() throws Exception {

    	return null;
    }


    public Response getItem(@PathParam("id") Long id) throws Exception {

    	return null;
    }


    public Response createItem(ItemDTO itemDTO) throws Exception {

    	return null;

    }


    public Response updateItem(ItemDTO itemDTO) throws Exception {

    	return null;

    }


    public Response deleteItem(@PathParam("id") Long id) throws Exception {

    	return null;

    }


    public Response getItemsByName(@PathParam("name") String name) throws Exception {

    	return null;
    }

}
