package com.example.accessingdatajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class ExampleService {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public void showCustomers(){

    }
}
