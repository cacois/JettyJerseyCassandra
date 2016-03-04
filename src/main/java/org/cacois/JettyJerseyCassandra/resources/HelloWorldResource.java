package org.cacois.JettyJerseyCassandra.resources;

import org.cacois.JettyJerseyCassandra.services.StringService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

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
