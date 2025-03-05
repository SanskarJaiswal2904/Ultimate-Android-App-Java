package com.example.recyclerviewdemo;
//author,title,description,publishedat,url,urltoimage
public class Items {
    String author,title,description,publishedAt;
    int urlToImage;

    public Items(String author, String title, String description, String publishedAt,int urlToImage) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.urlToImage = urlToImage;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(int urlToImage) {
        this.urlToImage = urlToImage;
    }
}
