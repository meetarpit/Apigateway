package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
public class GatwayConfig {

	@Autowired
	CustomFilter customFilter;
	
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes().route(r->r.path("/doctor/**").filters(f->f.filter(customFilter)).uri("lb://DOCTOR-SERVICE")).build();
	
	}
	
	@Bean
	public HttpClient httpClient() {
		return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
	}
	
//	@Bean
//	public GlobalFilter globalFilter() {
//		return (exchange,chain)->{
//			System.out.println("Before Filter");
//			return chain.filter(exchange).then(Mono.fromRunnable(()->{
//				System.out.println("After Filter");
//			}));
//		};		
//	}
}
