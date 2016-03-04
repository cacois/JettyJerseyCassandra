package org.cacois.JettyJerseyCassandra.data;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import javax.inject.Inject;

/**
 * Created by cacois on 3/4/16.
 */
public class UserDAO {

    private final Session session;
    private final MappingManager manager;
    private final Mapper mapper;
    private final Cluster cluster;

    @Inject
    public UserDAO(Cluster cluster){
        this.cluster = cluster;
        this.session = cluster.connect();
        this.manager = new MappingManager(session);
        this.mapper = manager.mapper(User.class);
    }

    public User get(Integer id) {
        User user = (User)mapper.get(id);
        return user;
    }
}
