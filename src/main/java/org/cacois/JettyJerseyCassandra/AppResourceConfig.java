package org.cacois.JettyJerseyCassandra;

import com.typesafe.config.Config;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class AppResourceConfig extends ResourceConfig {
    public AppResourceConfig(Config configuration) {
        register(RolesAllowedDynamicFeature.class);
        register(new AppBinder(configuration));
        packages("org.cacois.JettyJerseyCassandra.resources");
    }
}
