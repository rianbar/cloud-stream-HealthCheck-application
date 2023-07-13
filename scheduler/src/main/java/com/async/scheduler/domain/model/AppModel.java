package com.async.scheduler.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "tb_app_schedule")
public class AppModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String address;

    protected AppModel(){
        //for JPA-HIBERNATE
    }

    public AppModel(UUID id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void update(AppModel app) {
        this.name = app.getName();
        this.address = app.getAddress();
    }
}
