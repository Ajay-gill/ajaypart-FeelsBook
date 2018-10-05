package com.example.ajaypart_feelsbook;

import java.util.Date;

public class Emotion {
    protected String name;
    protected Date date;
    protected String comment;

    public  Emotion(String Name,Date date){
        this.name = Name;
        this.date = date;

    }
    public  void setComment(String comment) { //throws  CommentTooLongException{
//        if (this. comment.length() > 100){
//            throw new CommentTooLongException();
//        }
        this.comment =  comment;

    }

    public void setDate(Date date) {

        this.date = date;
    }

    public String getComment() {
        return this.comment;
    }

    public Date getDate() {
        return this.date;
    }
    public String getName() {
        return this.name;
    }

}

