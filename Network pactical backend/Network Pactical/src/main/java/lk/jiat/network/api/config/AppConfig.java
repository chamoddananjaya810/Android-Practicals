package lk.jiat.network.api.config;

import org.glassfish.jersey.server.ResourceConfig;

public class AppConfig extends ResourceConfig {
    public AppConfig() {
        // ඔයාගේ API classes තියෙන තැන මෙතනින් කියනවා
        packages("lk.jiat.network.controller");
        packages("lk.jiat.network.middleware");
    }
}