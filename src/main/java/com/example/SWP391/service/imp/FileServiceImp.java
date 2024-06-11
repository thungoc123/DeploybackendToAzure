package com.example.SWP391.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {
    boolean saveFile(MultipartFile file);
}
