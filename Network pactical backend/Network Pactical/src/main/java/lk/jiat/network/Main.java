package lk.jiat.network;

import lk.jiat.network.api.config.AppConfig;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;
import java.io.File;

public class Main {
    private static final String API_PATH = "/api/v1";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(SERVER_PORT);
        tomcat.getConnector();

        // 1. webapp folder එක සම්බන්ධ කිරීම
        String webappPath = new File("src/main/webapp").getAbsolutePath();
        var context = tomcat.addWebapp("", webappPath);

        // 2. Jersey Servlet එක හරහා AppConfig එක සම්බන්ධ කිරීම
        tomcat.addServlet(context, "API_Servlet", new ServletContainer(new AppConfig()));
        context.addServletMappingDecoded(API_PATH + "/*", "API_Servlet");

        tomcat.start();
        System.out.println("Server Started: http://localhost:" + SERVER_PORT + API_PATH);
        tomcat.getServer().await();
    }
}