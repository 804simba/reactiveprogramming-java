package com.simba.rxapi.dao;

import com.simba.rxapi.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void delayExecution(int index) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 50)
                .peek(CustomerDao::delayExecution)
                .peek(index -> System.out.println("Processing index count " + index))
                .mapToObj(index -> new Customer(index, "Customer" + index)).collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream() {
        return Flux.range(1, 50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(index -> System.out.println("Processing index count in stream flow " + index))
                .map(index -> new Customer(index, "Customer" + index));
    }

    public Flux<Customer> getAllCustomers() {
        return Flux.range(1, 50)
                .doOnNext(index -> System.out.println("Processing index count in stream flow " + index))
                .map(index -> new Customer(index, "Customer" + index));
    }
}
