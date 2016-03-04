package org.cacois.JettyJerseyCassandra.resources;

import com.datastax.driver.mapping.annotations.PartitionKey;
import org.cacois.JettyJerseyCassandra.data.User;
import org.cacois.JettyJerseyCassandra.data.UserDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by cacois on 3/4/16.
 */
@Path("/users")
public class UserResource {

    private final UserDAO dao;

    @Inject
    public UserResource(UserDAO dao) {
        this.dao = dao;
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public User hello(@PathParam("id") Integer id, @Context SecurityContext sc) {
        return dao.get(id);
    }
}
