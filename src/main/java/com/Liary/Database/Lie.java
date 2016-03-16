package com.Liary.Database;

/**
 * Created by Laur on 1/25/2015.
 */

public class Lie {
    private String text;
    private String lied_person;
    private Integer category;
    private Integer id;

    // Constructor for the Lie class
    public Lie(String i_text, String i_lied_person, Integer i_category) {
        super();
        this.text = i_text;
        this.lied_person = i_lied_person;
        this.category = i_category;
    }

    public int getId() {
        return id;
    }
    public void setId(int i_id) { this.id = i_id; }

    public String getText() {
        return text;
    }
    public void setText(String i_text) {
        this.text = i_text;
    }

    public String getPersonLied() {
        return lied_person;
    }
    public void setPersonLied(String i_lied_person) {
        this.lied_person = i_lied_person;
    }

    public Integer getCategory() {
        return category;
    }
    public void setCategory(Integer i_category) {
        this.category = i_category;
    }
}
