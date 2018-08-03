package com.dhorby.reactive.kotlinweb.service

import org.springframework.stereotype.Service

data class Employee(val employeeId:Int, val name:String)

val employees = mapOf(1 to Employee(1, "John Smith"))

@Service
class DataService {
    fun findEmployee(employeeId:Int): Employee  = employees.getOrDefault(employeeId,Employee(0, "Unknown"))
}