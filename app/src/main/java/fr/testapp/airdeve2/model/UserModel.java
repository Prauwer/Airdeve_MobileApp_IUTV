package fr.testapp.airdeve2.model;

import java.util.ArrayList;
import java.util.Date;

public class UserModel
{
    private int id;
    private String username;
    private String profilePictureUrl;
    private String description;
    private int subscribers_count;
    private int subscription_count;
    private ArrayList<UserModel> followers = new ArrayList<>();
    private ArrayList<UserModel> following = new ArrayList<>();
    private ArrayList<EventCommentModel> eventComments = new ArrayList<>();
    private ArrayList<EventModel> eventLiked = new ArrayList<>();
    private ArrayList<EventCommentModel> eventCommentsLiked = new ArrayList<>();
    private String fb_account;
    private String twitter_account;
    private String fondPictureUrl;
    private Date createdAt;
    private Date updatedAt;
    private int nombreAvis;
    private String evaluation;
    private int nombreEtoiles;


    public UserModel(int id, String name, String url, String description, int subscribers_count, int subscription_count,
                     String fb_account, String twitter_account, String urlTwo, int nbAvis, String eval, int nbEtoiles)
    {
        this.id = id;
        this.username = name;
        this.profilePictureUrl = url;
        this.description = description;
        this.subscribers_count = subscribers_count;
        this.subscription_count = subscription_count;
        this.fb_account = fb_account;
        this.twitter_account = twitter_account;
        this.fondPictureUrl = urlTwo;
        this.nombreAvis = nbAvis;
        this.evaluation = eval;
        this.nombreEtoiles = nbEtoiles;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubscribers_count() {
        return subscribers_count;
    }

    public void setSubscribers_count(int subscribers_count) {
        this.subscribers_count = subscribers_count;
    }

    public int getSubscription_count() {
        return subscription_count;
    }

    public void setSubscription_count(int subscription_count) {
        this.subscription_count = subscription_count;
    }

    public String getFb_account() {
        return fb_account;
    }

    public void setFb_account(String fb_account) {
        this.fb_account = fb_account;
    }

    public String getTwitter_account() {
        return twitter_account;
    }

    public void setTwitter_account(String twitter_account) {
        this.twitter_account = twitter_account;
    }

    public String getFondPictureUrl(){
        return fondPictureUrl;
    }

    public void setFondPictureUrl(String fondPictureUrl){
        this.profilePictureUrl = fondPictureUrl;
    }

    public int getNombreAvis() {
        return nombreAvis;
    }



    public String getEvaluation() {
        return evaluation;
    }



    public int getNombreEtoiles() {
        return nombreEtoiles;
    }


    public ArrayList<UserModel> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<UserModel> followers) {
        this.followers = followers;
    }

    public ArrayList<UserModel> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<UserModel> following) {
        this.following = following;
    }

    public ArrayList<EventCommentModel> getEventComments() {
        return eventComments;
    }

    public void setEventComments(ArrayList<EventCommentModel> eventComments) {
        this.eventComments = eventComments;
    }

    public ArrayList<EventModel> getEventLiked() {
        return eventLiked;
    }

    public void setEventLiked(ArrayList<EventModel> eventLiked) {
        this.eventLiked = eventLiked;
    }

    public ArrayList<EventCommentModel> getEventCommentsLiked() {
        return eventCommentsLiked;
    }

    public void setEventCommentsLiked(ArrayList<EventCommentModel> eventCommentsLiked) {
        this.eventCommentsLiked = eventCommentsLiked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

