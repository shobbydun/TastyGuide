package com.example.tastyguide;

import com.google.firebase.firestore.auth.User;

public class myModel extends MyRecipeModel {
    String name,ing,desc;
    String documentId;


    public myModel(){}
    public myModel(String name, String ing, String desc) {
        this.name = name;
        this.ing = ing;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getDocumentId() {
        return documentId;

    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

}
