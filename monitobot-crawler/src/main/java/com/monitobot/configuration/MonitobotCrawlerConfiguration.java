package com.monitobot.configuration;

import io.smallrye.config.ConfigMapping;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
@ConfigMapping(prefix = "monitobot")
public interface MonitobotCrawlerConfiguration {

    Search search();
    interface Search {

        Google google();
        interface Google {
            String baseUrl();
            String customSearchEngineId();
            String apiKey();
        }
    }
}
