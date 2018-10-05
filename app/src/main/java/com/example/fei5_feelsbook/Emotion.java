/*
 * Copyright (c) Fei Yang, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package com.example.fei5_feelsbook;

import java.util.Date;

/**
 * The type Emotion.
 */
public abstract class Emotion {

    private Date date;
    private String feeling;
    private String comment;

    /**
     * Set feeling.
     *
     * @param feeling the feeling
     */
//feeling
    public void setFeeling(String feeling){
        this.feeling = feeling;
    }

    /**
     * Gets feeling.
     *
     * @return the feeling
     */
    public String getFeeling() {
        return this.feeling;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
//comment
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Set date.
     *
     * @param date the date
     */
//date
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Is important boolean.
     *
     * @return the boolean
     */
//No method body implemented! We leave that up to the subclasses (they MUST implement it)
    public abstract Boolean isImportant();

    @Override
    public String toString() {
        return this.feeling + "|" + this.comment + " | " + this.date.toString();
    }

}
