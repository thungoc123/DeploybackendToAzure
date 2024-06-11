package com.example.SWP391.repository;

import com.example.SWP391.entity.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetailsRepository extends JpaRepository<EventDetails, Integer> {
}
