package com.broochem.restserver.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("layout")
public class LayoutResource {
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String panelList() {
		String json = "["
						+ "{\"id\": 1, \"name\":\"document\"},"
						+ "{\"id\": 2, \"name\":\"tree\"}"
					+ "]";
		return json;
	}
	
}
