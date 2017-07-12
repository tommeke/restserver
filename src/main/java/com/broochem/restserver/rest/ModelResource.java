package com.broochem.restserver.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.tuple.Pair;

import com.broochem.restserver.database.ModelProviderFactory;

@Path("model")
public class ModelResource {
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String modelList() {
		String json = "[";
		List<Pair<Long,String>> modelList = ModelProviderFactory.getInstance().createModelProvider().getAllModels();
		for ( Pair<Long,String> modelPair : modelList) {
			if ( json.length() > 1) {
				json += ",";
			}
			json += "{";
			json += "\"id\":" + modelPair.getKey();
			json += ",\"name\":\"" + modelPair.getValue();
			json += "\"}";
		}
		json += "]";
		return json;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String pathMethod(@PathParam("id") long modelId) {
		return ModelProviderFactory.getInstance().createModelProvider().getModel(modelId);
	}
	
	/*
	
	@GET
	@Path("param")
	@Produces(MediaType.TEXT_PLAIN)
	public String paramMethod(@QueryParam("name") String name) {
	  return "Hello, " + name;
	}

	 */
	
	@POST
	@Path("post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String postMethod(@FormParam("name") String name) {
	  return "<h2>Hello, " + name + "</h2>";
	}
}
