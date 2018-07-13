package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController{

    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping(value = "/customers")
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter : String) =
            ResponseEntity(customerService.searchCustomer(nameFilter), HttpStatus.OK)

    @GetMapping(value = "/customer/{id}")
    fun getCustomer(@PathVariable id : Int) : ResponseEntity<Customer?>{
        val customer = customerService.getCustomer(id)
        val status = if (customer == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(customer, status)
    }

    @PostMapping(value = "/customer/")
    fun createCustomer(@RequestBody customer : Customer) = ResponseEntity(customerService.createCustomer(customer), HttpStatus.CREATED)

    @DeleteMapping(value = "/customer/{id}")
    fun deleteCustomer(@PathVariable id : Int) = ResponseEntity(customerService.deleteCustomer(id), HttpStatus.OK)

    @PutMapping(value = "/customers/{id}")
    fun updateCustomer(@PathVariable id : Int, @RequestBody customer: Customer) =
            ResponseEntity(customerService.updateCustomer(id, customer), HttpStatus.OK)
}