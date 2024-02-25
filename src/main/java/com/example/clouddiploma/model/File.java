package com.example.clouddiploma.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "files")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(nullable = false)
    private Integer size;

    @Lob
    @Column(nullable = false)
    private byte[] file;

    @ManyToOne
    private User user;

    public File(String fileName, String fileType, Integer size, byte[] file, User user) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
        this.file = file;
        this.user = user;
    }

}
