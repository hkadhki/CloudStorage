package com.example.clouddiploma.controller;

import com.example.clouddiploma.dto.GetFiles;
import com.example.clouddiploma.exceptions.ErrorDeletFileException;
import com.example.clouddiploma.exceptions.ErrorGettingFileListException;
import com.example.clouddiploma.exceptions.ErrorUploadFileException;
import com.example.clouddiploma.service.CloudService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/cloud")
@RestController
public class CloudController {
    private final CloudService cloudService;

    public CloudController(CloudService cloudService) {
        this.cloudService = cloudService;
    }


    @PostMapping("/file")
    public ResponseEntity<Void> uploadFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename, MultipartFile file) throws ErrorUploadFileException {
        cloudService.uploadFile(authToken, filename, file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/file")
    public ResponseEntity<Void> deleteFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename) throws  ErrorDeletFileException {
        cloudService.deleteFile(authToken, filename);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/file")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename) throws ErrorUploadFileException {
        return ResponseEntity.ok().body(new ByteArrayResource(cloudService.downloadFile(authToken, filename)));
    }

    @PutMapping("/file")
    public ResponseEntity<Void> editFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename, @RequestBody String newfileName) throws ErrorDeletFileException {
        cloudService.editFile(authToken, filename, newfileName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public List<GetFiles> getList(@RequestHeader("auth-token") String authToken, @RequestParam("limit") Integer limit) throws ErrorGettingFileListException {
        return cloudService.getList(authToken, limit);
    }

}