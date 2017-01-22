package com.gmonetix.gmonetix.helper;

import android.graphics.Bitmap;

public class JsonData {

    private int id;
    private String date,link,slug,title,content,author,featured_media,comment_status,categories;

    public JsonData( int id, String date, String title, String featured_media , String link, String slug,
                    String author, String comment_status, String categories){
        this.setId(id);
        this.setDate(date);
        this.setTitle(title);
        this.setFeatured_media(featured_media);
        this.setLink(link);
        this.setSlug(slug);
        this.setAuthor(author);
        this.setComment_status(comment_status);
        this.setCategories(categories);
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public String getFeatured_media() {
        return featured_media;
    }

    public void setFeatured_media(String featured_media) {
        this.featured_media = featured_media;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
