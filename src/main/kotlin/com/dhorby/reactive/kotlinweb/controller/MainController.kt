package com.dhorby.reactive.kotlinweb.controller

import com.dhorby.reactive.kotlinweb.service.DataService
import com.dhorby.reactive.kotlinweb.service.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @Autowired
    lateinit var employeeService:DataService

    @GetMapping("/{id}")
    fun getName(@PathVariable ("id") id:Int): String {
        return employeeService.findEmployee(id).name
    }

}