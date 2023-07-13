package com.async.scheduler.domain.repositories;

import com.async.scheduler.domain.model.AppModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppRepository extends JpaRepository<AppModel, UUID> {
}
