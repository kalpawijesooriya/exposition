package com.Exposition.basigarcia.navigationdrawervideotutorial;

/**
 * Created by Kalpa Wijesooriya on 12/26/2017.
 */

public class member {

    private String name;
    private String position;
    private String image;
    private String contactno;

    public member() {
    }
    public member(String name, String position, String image,String contactno) {
        this.name = name;
        this.contactno = contactno;
        this.position = position;
        this.image = image;
    }
    public void setcontactno(String contactno) {this.contactno = contactno;}
    public String getcontactno() {return contactno;}
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public String getposition() {
        return position;
    }
    public void setposition(String position) {
        this.position = position;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
