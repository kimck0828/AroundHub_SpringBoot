package com.kimck0828.aroundhub.aroundhub_springboot.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delete")
public class DeleteController {
    @DeleteMapping("/{id}")
    public String deleteDefault(@PathVariable String id) {
        return id;
    }
}
