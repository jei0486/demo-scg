//package com.demo.scg.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import static org.springframework.cloud.gateway.support.RouteMetadataUtils.CONNECT_TIMEOUT_ATTR;
//import static org.springframework.cloud.gateway.support.RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR;
//
//@Configuration
//public class RouteConfig {
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder routeBuilder){
//        return routeBuilder.routes()
//                .route("test1", r -> {
//                    return r.host("*.somehost.org").and().path("/somepath")
//                            .filters(f -> f.addRequestHeader("header1", "header-value-1"))
//                            .uri("http://someuri")
//                            .metadata(RESPONSE_TIMEOUT_ATTR, 200)
//                            .metadata(CONNECT_TIMEOUT_ATTR, 200);
//                })
//                .build();
//    }
//
//}
