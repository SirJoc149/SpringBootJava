package com.example.demo.resource;

import com.sun.istack.NotNull;

import javax.validation.constraints.Size;

public class SaveGraduateResource {
    @NotNull
    @Size(max=20)
    public String name;

    @NotNull
    @Size(max=20)
    public String apellido;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
