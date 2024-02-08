package com.simba.rxapi.service;

import com.simba.rxapi.dao.CustomerDao;
import com.simba.rxapi.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> loadAllCustomers() {
        long start = getCurrentTime();
        var customers = customerDao.getCustomers();
        long end = getCurrentTime();
        System.out.println("Total execution time: " + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long start = getCurrentTime();
        var customers = customerDao.getCustomersStream();
        long end = getCurrentTime();
        System.out.println("Total execution time: " + (end - start));
        return customers;
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
