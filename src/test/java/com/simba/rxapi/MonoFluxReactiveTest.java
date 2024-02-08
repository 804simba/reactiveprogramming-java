package com.simba.rxapi;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxReactiveTest {
    @Test
    public void testMono() {
        Mono<?> monoString = Mono.just("simba").then(Mono.error(new RuntimeException("Exception occurred"))).log();
        monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> fluxString = Flux.just("simba", "ngonadi", "spring boot").log();
        fluxString.subscribe(System.out::println);
    }

}