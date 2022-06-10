package com.example.booklisting;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Book {
    private String title;
    private ArrayList<String> authors;
    private String publisher;
    private String publisherDate;
    private String description;
    private String thumbnailLink;
    private String linkToPreview;

    public Book(String title, ArrayList<String> authors, String publisher, String publisherDate, String description, String thumbnailLink, String linkToPreview) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publisherDate = publisherDate;
        this.description = description;
        this.thumbnailLink = thumbnailLink;
        this.linkToPreview = linkToPreview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    //format author arrays to easily readable string
    public String formatAuthorsToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < authors.size(); i++) {
            sb.append(authors.get(i));
            if (i != authors.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherDate() {
        return publisherDate;
    }

    public void setPublisherDate(String publisherDate) {
        this.publisherDate = publisherDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    public String getLinkToPreview() {
        return linkToPreview;
    }

    public void setLinkToPreview(String linkToPreview) {
        this.linkToPreview = linkToPreview;
    }
}
