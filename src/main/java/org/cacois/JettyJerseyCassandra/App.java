package org.cacois.JettyJerseyCassandra;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.File;

public class App {
    public static void main(String[] args) throws Exception
    {
        if(args.length == 0) {
            System.out.println("Configuration file parameter is required");
            System.exit(0);
        }

        Config configuration = ConfigFactory.parseFile(new File(args[0]));

        AppBootstrap appBootstrap = new AppBootstrap(configuration);
        appBootstrap.start();
    }
}
