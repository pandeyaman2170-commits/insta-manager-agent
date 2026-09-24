package com.clipgrowth.clipgrowth_backend;

import java.net.URI;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(@Value("${DATABASE_URL}") String databaseUrl) throws Exception {
        URI uri = new URI(databaseUrl);
        String[] userInfo = uri.getUserInfo().split(":", 2);
        int port = uri.getPort() == -1 ? 5432 : uri.getPort();

        String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + port + uri.getPath()
                + (uri.getQuery() != null ? "?" + uri.getQuery() : "");

        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(userInfo[0])
                .password(userInfo[1])
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}