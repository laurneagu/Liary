package com.Liary.Utils;

import com.Liary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laur on 2/16/2015.
 */
public class CreditsPerson {
    private String name;
    private String role;
    private int pic_source;
    private Integer id;

    // Constructor for the Credits Person class
    public CreditsPerson(String i_name, String i_role,int i_pic_source) {
        super();
        this.name = i_name;
        this.role = i_role;
        this.pic_source = i_pic_source;
    }

    public int getId() {
        return id;
    }
    public void setId(int i_id) { this.id = i_id; }

    public String getName() {
        return name;
    }
    public void setName(String i_name) {
        this.name = i_name;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String i_role) {
        this.role = i_role;
    }

    public int getPicSource() {
        return pic_source;
    }
    public void setPicSource(int i_pic_source) {
        this.pic_source = i_pic_source;
    }

    public static List<CreditsPerson> Init(){
        List<CreditsPerson> creditsPers = new ArrayList<CreditsPerson>();
        creditsPers.add(new CreditsPerson("Laur Neagu", "- developer -",R.drawable.laur));
        creditsPers.add(new CreditsPerson("Alex Encica", "- design architect -", R.drawable.alex));
        creditsPers.add(new CreditsPerson("Adriana Leonte", "- application flow -", R.drawable.adriana));
        creditsPers.add(new CreditsPerson("Andrei Ursache", "- logo creator -", R.drawable.andrei));
        creditsPers.add(new CreditsPerson("Others", "\n- font - CONFN.TTF \n- logo - ZillionDesigns \n- glogow - log in logo", R.drawable.others));

        return  creditsPers;
    }
}
