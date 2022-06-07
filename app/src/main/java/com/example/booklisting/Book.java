package com.example.booklisting;

public class Book {
private String title;
private String authors;
private String publisher;
private long publisherDate;
private String textSnippet;
private String description;
private int pageCount;
private String thumbnailLink;
private String linkToPreview;

    public Book(String title, String authors, String publisher, long publisherDate, String textSnippet, String description, int pageCount, String thumbnailLink, String linkToPreview) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publisherDate = publisherDate;
        this.textSnippet = textSnippet;
        this.description = description;
        this.pageCount = pageCount;
        this.thumbnailLink = thumbnailLink;
        this.linkToPreview = linkToPreview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public long getPublisherDate() {
        return publisherDate;
    }

    public void setPublisherDate(long publisherDate) {
        this.publisherDate = publisherDate;
    }

    public String getTextSnippet() {
        return textSnippet;
    }

    public void setTextSnippet(String textSnippet) {
        this.textSnippet = textSnippet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
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
