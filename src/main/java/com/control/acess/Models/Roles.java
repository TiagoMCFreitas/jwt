package com.control.acess.Models;

import com.control.acess.Enums.RoleName;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleName cargo;

    public Roles() {
    }

    public Roles(RoleName cargo) {
        this.cargo = cargo;
    }

    @JsonCreator
    public static Roles fromString(String cargo) {
        return new Roles(RoleName.valueOf(cargo));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getCargo() {
        return cargo;
    }

    public void setCargo(RoleName cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", cargo=" + cargo +
                '}';
    }
}