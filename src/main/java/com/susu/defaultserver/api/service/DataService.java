package com.susu.defaultserver.api.service;

import com.susu.defaultserver.api.entity.MediaData;
import com.susu.defaultserver.api.repository.MediaDataRepository;
import com.susu.defaultserver.api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {

    private final MediaDataRepository mediaDataRepository;
    private final PostRepository postRepository;

    public List<MediaData> getAllData() {
        return mediaDataRepository.findAll();
    }

}
