package fr.testapp.airdeve2.model;

import java.util.ArrayList;
import java.util.Date;

public class EventCommentModel
{
    private int id;
    private UserModel author;
    private String authorId;
    private int eventId;
    private String media;
    private String caption;
    private String text;
    private EventModel event;
    private String[] hashtag;
    private int likes;
    private Date createdAt;
    private Date updatedAt;
    private String payload;

    public EventCommentModel(UserModel author, EventModel event, String text, int likes, Date createdAt)
    {
        this.author = author;
        this.event = event;
        this.text = text;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    public EventCommentModel(int id, UserModel author, String authorId, EventModel event, int likes, Date createdAt)
    {
        this.id = id;
        this.author = author;
        this.authorId = authorId;
        this.event = event;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }

    public String[] getHashtag() {
        return hashtag;
    }

    public void setHashtag(String[] hashtag) {
        this.hashtag = hashtag;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
