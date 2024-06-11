package com.example.SWP391.repository;

import com.example.SWP391.entity.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventImageRepository extends JpaRepository<EventImage, Integer> {

}

