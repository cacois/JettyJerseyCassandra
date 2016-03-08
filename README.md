# JettyJerseyCassandra

This project is a simple base project for Java applications using a Jetty+Jersey+Cassandra stack.

## Setup

### Configuration

App configuration is in the file named configuration.conf.

### Cassandra

You'll need a Cassandra instance running locally on port 9042. Alternatively, you can change the app config in configuration.conf to point to the host/port of your Cassandra instance.

If you are on a Docker-capable system, you can start a local Docker container running Cassandra with:

    $ docker run --name cassandra -p 9042:9042 -d cassandra