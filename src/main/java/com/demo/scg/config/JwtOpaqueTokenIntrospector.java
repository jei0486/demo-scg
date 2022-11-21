//package com.demo.scg.config;
//
//import com.nimbusds.jwt.JWT;
//import com.nimbusds.jwt.JWTClaimsSet;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
//import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
//import org.springframework.security.oauth2.server.resource.introspection.NimbusReactiveOpaqueTokenIntrospector;
//import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
//import reactor.core.publisher.Mono;
//
//import static org.springframework.security.core.authority.AuthorityUtils.NO_AUTHORITIES;
//
//@Configuration
//public class JwtOpaqueTokenIntrospector implements ReactiveOpaqueTokenIntrospector {
//    private ReactiveOpaqueTokenIntrospector delegate =
//            new NimbusReactiveOpaqueTokenIntrospector("http://localhost:8080/auth/realms/demo/protocol/openid-connect/token/introspect",
//                    "demo-client",
//                    "be7efd17-348c-4c63-aee9-3435bddba64f");
//    private ReactiveJwtDecoder jwtDecoder = new NimbusReactiveJwtDecoder(new ParseOnlyJWTProcessor());
//
//    public Mono<OAuth2AuthenticatedPrincipal> introspect(String token) {
//        return this.delegate.introspect(token)
//                .flatMap(principal -> this.jwtDecoder.decode(token))
//                .map(jwt -> new DefaultOAuth2AuthenticatedPrincipal(jwt.getClaims(), NO_AUTHORITIES));
//    }
//
//    private static class ParseOnlyJWTProcessor implements Converter<JWT, Mono<JWTClaimsSet>> {
//        public Mono<JWTClaimsSet> convert(JWT jwt) {
//            try {
//                return Mono.just(jwt.getJWTClaimsSet());
//            } catch (Exception e) {
//                return Mono.error(e);
//            }
//        }
//    }
//
//    @Bean
//    public ReactiveOpaqueTokenIntrospector introspector() {
//        return new JwtOpaqueTokenIntrospector();
//    }
//}
