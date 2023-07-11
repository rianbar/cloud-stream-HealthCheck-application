package com.async.application.repositories;

import com.async.application.models.AppModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppRepository extends JpaRepository<AppModel, UUID> {
}
