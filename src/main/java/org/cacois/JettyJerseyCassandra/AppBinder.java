package org.cacois.JettyJerseyCassandra;

import com.datastax.driver.core.Cluster;
import com.typesafe.config.Config;
import org.cacois.JettyJerseyCassandra.data.UserDAO;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.cacois.JettyJerseyCassandra.services.StringService;

import javax.inject.Singleton;

public class AppBinder extends AbstractBinder {
    final Config configuration;

    public AppBinder(Config configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(configuration).to(Config.class);
        bind(new StringService()).to(StringService.class);
        bind(UserDAO.class).to(UserDAO.class);
        bind(Cluster.builder().addContactPoint(configuration.getString("Cassandra.Host")).withPort(configuration.getInt("Cassandra.Port")).build())
                .to(Cluster.class);
//                .in(Singleton.class);
    }
}
