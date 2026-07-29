package com.pavankumar.farm2home.common.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file, String folderName);

}