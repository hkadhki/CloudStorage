package com.example.clouddiploma.service;

import com.example.clouddiploma.dto.GetFiles;
import com.example.clouddiploma.exceptions.*;
import com.example.clouddiploma.jwt.JwtGenerator;
import com.example.clouddiploma.model.File;
import com.example.clouddiploma.model.User;
import com.example.clouddiploma.repository.AuthRepository;
import com.example.clouddiploma.repository.FileRepository;
import com.example.clouddiploma.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CloudService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    public CloudService(FileRepository fileRepository, UserRepository userRepository, JwtGenerator jwtGenerator, AuthRepository authRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    public void uploadFile(String authToken, String filename, MultipartFile file) throws ErrorUploadFileException {
        User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Upload file: Unauthorized");
            throw new UnauthorizedErrorException("Upload file: Unauthorized");
        }

        if (file.isEmpty()){
            log.error("Файл отсутствует");
            throw new ErrorInputDataException("Файл отсутствует");
        }

        try{
            fileRepository.saveAndFlush(new File(filename,file.getContentType(), (int) file.getSize(),file.getBytes(),user));
            log.info("Success upload file. User {}", user.getUsername());
        } catch (IOException e) {
            log.error("ошибка записи");
            throw new ErrorUploadFileException("ошибка записи");
        }
    }

    public void deleteFile(String authToken, String filename) throws ErrorDeletFileException {
        User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Upload file: Unauthorized");
            throw new UnauthorizedErrorException("Upload file: Unauthorized");
        }
        if (fileRepository.findByFileNameAndUser(filename, user)==null){
            log.error("файл с данным именем не существует");
            throw new ErrorInputDataException("файл с данным именем не существует");
        }

        try {
            fileRepository.deleteByFileNameAndUser(filename,user);
            log.info("Success delete file. User {}", user.getUsername());
        }catch (Exception e){
            log.error("ошибка удаления");
            throw new ErrorDeletFileException("ошибка удаления");
        }

    }

    public byte[] downloadFile(String authToken, String filename) throws ErrorUploadFileException {
        User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Upload file: Unauthorized");
            throw new UnauthorizedErrorException("Upload file: Unauthorized");
        }

        if (fileRepository.findByFileNameAndUser(filename, user)==null){
            log.error("файл с данным именем не существует");
            throw new ErrorInputDataException("файл с данным именем не существует");
        }

        try {
            byte []  file = fileRepository.findByFileNameAndUser(filename, user).getFile();
            log.info("Success download file. User {}", user.getUsername());
            return file;
        }catch (Exception e){
            log.error("ошибка звгрузки");
            throw new ErrorUploadFileException("ошибка звгрузки");
        }
    }

    public void editFile(String authToken, String filename, String newFilename) throws ErrorDeletFileException {
        User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Upload file: Unauthorized");
            throw new UnauthorizedErrorException("Upload file: Unauthorized");
        }

        if (fileRepository.findByFileNameAndUser(filename, user)==null){
            log.error("файл с данным именем не существует");
            throw new ErrorInputDataException("файл с данным именем не существует");
        }

        try {
            fileRepository.editFileNameByUser(user,filename,newFilename);
            log.info("Success edit file. User {}", user.getUsername());
        }catch (Exception e){
            log.error("ошибка измменения");
            throw new ErrorDeletFileException("ошибка измменения");
        }
    }


    public List<GetFiles> getList(String authToken, Integer limit) throws ErrorGettingFileListException {
        User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Upload file: Unauthorized");
            throw new UnauthorizedErrorException("Upload file: Unauthorized");
        }

        try {
            return filesToGetFiles(fileRepository.findBylimit(user.getId(),limit));
        }catch (Exception e){
            log.error("ошибка чтения");
            throw new ErrorGettingFileListException("ошибка чтения");
        }
    }


    public List<GetFiles> filesToGetFiles(List<File> files){
        List<GetFiles> list = new ArrayList<>();
        for (File f : files){
            list.add(new GetFiles(f));
        }
        return list;
    }

    private User getUserByAuthToken(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            final String authTokenWithoutBearer = authToken.split(" ")[1];
            final String username = authRepository.getUsernameByToken(authTokenWithoutBearer);
            return userRepository.findByUsername(username);
        }
        return null;
    }


}