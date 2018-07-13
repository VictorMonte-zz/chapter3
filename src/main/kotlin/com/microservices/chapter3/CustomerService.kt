package com.microservices.chapter3

interface CustomerService{
    fun getCustomer(id : Int) : Customer
    fun createCustomer(customer: Customer)
    fun deleteCustomer(id : Int)
    fun updateCustomer(id: Int, customer: Customer) : Customer
    fun searchCustomer(nameFilter : String) : List<Customer>
}