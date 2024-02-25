package com.example.clouddiploma.repository;

import com.example.clouddiploma.model.File;
import com.example.clouddiploma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<File,Integer> {

    void deleteByFileNameAndUser(String fileName, User users);

    List<File> findAll();

    File findByFileNameAndUser(String fileName, User users);

    @Modifying
    @Query("update File f set f.fileName = :newName where f.fileName = :filename and f.user = :user")
    void editFileNameByUser(@Param("user") User users, @Param("filename") String filename, @Param("newName") String newName);


    @Query(value = "SELECT * FROM files WHERE users_id = :user LIMIT :lim", nativeQuery = true)
    List<File> findBylimit (@Param("user") Long id,@Param("lim") Integer lim);

}
