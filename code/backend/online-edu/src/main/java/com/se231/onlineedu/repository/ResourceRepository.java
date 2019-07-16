package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource,Long> {
}
