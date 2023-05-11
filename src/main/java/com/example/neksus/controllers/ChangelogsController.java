package com.example.neksus.controllers;

import com.example.neksus.models.Changelogs;
import com.example.neksus.services.ChangelogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangelogsController {

    @Autowired
    ChangelogsService changelogService;


    @PostMapping("/addChangelog")
    public ResponseEntity<?> addVideo(@RequestBody Changelogs changelog) {
        if (changelogService.addChangelog(changelog)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
