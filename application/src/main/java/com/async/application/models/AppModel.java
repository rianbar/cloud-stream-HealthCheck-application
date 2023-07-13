package com.async.application.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "tb_domain")
@Getter
@Builder
public class AppModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String address;

    protected AppModel() {
        //for JPA-Hibernate
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
