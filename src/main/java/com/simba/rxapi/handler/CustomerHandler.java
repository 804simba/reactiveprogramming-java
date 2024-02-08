package com.simba.rxapi.handler;

import com.simba.rxapi.dao.CustomerDao;
import com.simba.rxapi.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customers = customerDao.getAllCustomers();
        return  ServerResponse.ok().body(customers, Customer.class);
    }

    public Mono<ServerResponse> findCustomerById(ServerRequest request) {
        int customerId = Integer.parseInt(request.pathVariable("id"));
        // customerDao.getAllCustomers().filter(customer -> customer.getId() == customerId).take(1).single();
        Mono<Customer> customerMono =  customerDao.getAllCustomers().filter(customer -> customer.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + " : " + dto.getName());
        return ServerResponse.ok().body(saveResponse, String.class);
    }
}
