package com.chubby.notsochubby.models.entities;

import org.threeten.bp.LocalDateTime;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "news", foreignKeys = @ForeignKey(entity = NewsCategory.class,
        parentColumns = "id",
        childColumns = "catId",
        onDelete = CASCADE),
        indices = @Index(value = "catId"))
public class News {
    @PrimaryKey
    private int id;
    private int catId;
    private String title;
    private String htmlText;
    private String imagePath;
    private LocalDateTime publishAt;

    public News(int id, int catId, String title, String htmlText, String imagePath, LocalDateTime publishAt) {
        this.id = id;
        this.catId = catId;
        this.title = title;
        this.htmlText = htmlText;
        this.imagePath = imagePath;
        this.publishAt = publishAt;
    }

    public int getId() {
        return id;
    }


    public int getCatId() {
        return catId;
    }

    public String getTitle() {
        return title;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public LocalDateTime getPublishAt() {
        return publishAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setPublishAt(LocalDateTime publishAt) {
        this.publishAt = publishAt;
    }
}
