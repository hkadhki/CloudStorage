package com.example.clouddiploma.dto;

import com.example.clouddiploma.model.File;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetFiles {
    String filename;
    Integer size;

    public GetFiles(File file){
        this.filename = file.getFileName();
        this.size = file.getSize();
    }
}
