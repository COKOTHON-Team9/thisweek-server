package com.susu.defaultserver.api.repository;

import com.susu.defaultserver.api.entity.MediaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaDataRepository extends JpaRepository<MediaData, Long> {
}
