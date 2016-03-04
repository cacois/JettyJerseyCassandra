package org.cacois.JettyJerseyCassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.typesafe.config.Config;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppBootstrap {
    final Config configuration;
    final Logger logger = LoggerFactory.getLogger(App.class);

    public AppBootstrap(Config configuration) {
        this.configuration = configuration;
    }

    public void start() throws Exception {
        // Configure Cassandra
        Session session = Cluster.builder().addContactPoint(configuration.getString("Cassandra.Host")).withPort(configuration.getInt("Cassandra.Port")).build().connect();
        session.execute("CREATE KEYSPACE IF NOT EXISTS testks " +
                        "WITH replication= {'class': 'SimpleStrategy', 'replication_factor': 1}");
        session.execute("CREATE TABLE IF NOT EXISTS testks.users (" +
                            "id int PRIMARY KEY," +
                            "name text" +
                        ");");

        // insert 5k users for testing
        for(int i=1;i<5000;i++) {
            session.execute("INSERT INTO testks.users (id, name) VALUES (" + i + ", " + "'name" + i + "')");
        }

        // Static Content
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase(this.getClass().getResource("/html").getPath());

        // Jersey
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        ServletContainer jerseyServletContainer = new ServletContainer(new AppResourceConfig(configuration));
        ServletHolder jerseyServletHolder = new ServletHolder(jerseyServletContainer);
        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet(jerseyServletHolder, "/api/*");

        // Metrics
//        ServletHolder metricsServletHolder = new ServletHolder(new AdminServlet());
//        servletContextHandler.addEventListener(new MetricsServletContextListener());
//        servletContextHandler.addServlet(metricsServletHolder, "/admin/*");

        // Wire up Jetty
        HandlerCollection handlerList = new HandlerCollection();
        handlerList.setHandlers(new Handler[]{ resourceHandler, servletContextHandler });
        Server server = new Server(configuration.getInt("Server.Port"));
        server.setHandler(handlerList);

        server.start();
        server.join();
    }
}
