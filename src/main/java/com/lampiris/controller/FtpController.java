package com.lampiris.controller;

import com.lampiris.service.FtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/ftp")
public class FtpController {

    @Autowired
    private FtpService ftpService;

    @PostMapping("/{fileName}")
    public ResponseEntity<Void> uploadFile(@PathVariable String fileName) {
        ftpService.exportToFTP(fileName);
        return ResponseEntity.noContent().build();
    }
}
