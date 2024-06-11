package com.example.SWP391.service.imp;

import com.example.SWP391.payload.Request.InsertEventRequest;
import org.springframework.web.multipart.MultipartFile;

public interface EventServiceImp {
    boolean insertEvent(InsertEventRequest request);
}
