package com.project.account.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExampleController {

    @RequestMapping(value = "/example", method = {RequestMethod.GET, RequestMethod.POST})
    public List<String> test() throws CustomException {
//        if(true) {
//            throw new CustomException("exception");
//        }
        return List.of("a","b");
    }
}
