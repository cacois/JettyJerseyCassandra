package org.cacois.JettyJerseyCassandra.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/hello")
public class HelloWorldResource {

    public HelloWorldResource() {}

    @GET
    @Produces("application/json")
    @Path("{name}")
    public String hello(@PathParam("name") String name, @Context SecurityContext sc) {
        return "Hello, " + name;
    }
}
