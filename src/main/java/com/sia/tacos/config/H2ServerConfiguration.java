package com.sia.tacos.config;


/**
 * ClassName: H2ServerConfiguration
 * Package: com.sia.tacos.config
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/21 11:45
 * @Version 1.0
 */

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2ServerConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
