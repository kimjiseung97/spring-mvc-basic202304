package com.example.mvc.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    //DataSource설정 : 데이터 베이스 연결 정보
    //url : dbms가 설치된 경로
    //username : db계정명
    //password : db password
    //driver class : dbms마다 설치한 커넥터 드라이버

    //커넥션 풀 설정
    // : db 접속시 사용하는 리소스를 관리하는 프로그램

//    @Bean
//    public DataSource dataSource(){
//        HikariConfig config = new HikariConfig();
//        config.setUsername("root");
//        config.setPassword("1234");
//        config.setJdbcUrl("jdbc:mariadb://localhost:3307/spring");
//        config.setDriverClassName("org.mariadb.jdbc.Driver");
//
//        return new HikariDataSource(config);
//    }

}
