package com.Liary.Database;

/**
 * Created by Laur on 2/5/2015.
 */
public class BlackListEntry {
    private String lied_person;
    private Integer num_lies;

    // Constructor for the Lie class
    public BlackListEntry( String i_lied_person, Integer i_num_lies) {
        super();
        this.lied_person = i_lied_person;
        this.num_lies = i_num_lies;
    }

    public int getNumLies() {
        return num_lies;
    }
    public void setNumLies(int i_num_lies) { this.num_lies = i_num_lies; }

    public String getPersonLied() {
        return lied_person;
    }
    public void setPersonLied(String i_lied_person) {
        this.lied_person = i_lied_person;
    }
}
