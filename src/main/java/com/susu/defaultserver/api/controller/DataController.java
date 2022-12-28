package com.susu.defaultserver.api.controller;

import com.susu.defaultserver.api.entity.MediaData;
import com.susu.defaultserver.api.repository.MediaDataRepository;
import com.susu.defaultserver.api.service.DataService;
import com.susu.defaultserver.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DataController {

    private final DataService dataService;

    @GetMapping("/data")
    public ApiResponse getAllData() {
        return ApiResponse.success("result", dataService.getAllData());
    }
}
