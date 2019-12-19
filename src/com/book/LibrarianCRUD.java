package com.book;

import javafx.scene.control.CheckBox;

public class LibrarianCRUD {
    private Integer id;
    private String university_id;
    private String first_name;
    private String last_name;

    private CheckBox lib_crud;

    public LibrarianCRUD(Integer id, String university_id, String first_name, String last_name, CheckBox lib_crud) {
        this.id = id;
        this.university_id = university_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.lib_crud = lib_crud;
    }

    public LibrarianCRUD() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public CheckBox getLib_crud() {
        return lib_crud;
    }

    public void setLib_crud(CheckBox lib_crud) {
        this.lib_crud = lib_crud;
    }
}
