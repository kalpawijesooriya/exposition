package com.Exposition.basigarcia.navigationdrawervideotutorial;

/**
 * Created by kalpa wijesooriya on 5/29/2017.
 */

public class ListItem {

    private String head;
    private String desc;
    private  String imageUrl;
    private String details;
    private  String imagemaga;



    public ListItem(String head, String desc, String imageUrl, String details) {
        this.head = head;
        this.desc = desc;
        this.details=details;
        this.imageUrl=imageUrl;
        this.imagemaga=imagemaga;


    }


    public void setImagemaga(String imagemaga) {
        this.imagemaga = imagemaga;
    }

    public String getImagemaga() {
        return imagemaga;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getDetails() {
        return details;}

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
