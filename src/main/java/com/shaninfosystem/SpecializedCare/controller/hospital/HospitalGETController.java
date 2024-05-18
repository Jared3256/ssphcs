package com.shaninfosystem.SpecializedCare.controller.hospital;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HospitalGETController
{
    @GetMapping("/test1")
    public ResponseEntity<String> test()
    {
        return ResponseEntity.ok("This is a test secured endpoint");
    }
    
    @GetMapping(path = "/admin")
    public ResponseEntity<String> adminOnlyURl()
    {
        return ResponseEntity.ok("This is an admin url");
    }
}
