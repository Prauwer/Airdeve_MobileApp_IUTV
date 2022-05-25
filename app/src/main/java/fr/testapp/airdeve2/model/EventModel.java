package fr.testapp.airdeve2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventModel
{
    private String name;
    private String description;
    private String imageUrl;
    private String place;
    private int like = 0;
    private int capacity;
    private List<UserModel> organizers;
    private Date createAt;
    private Date salesStart;
    private Date salesEnd;
    private Date date;
    private int ticketSold;
    private int ticketPrice;
    private int moneyNeeded;
    private String category;
    private ArrayList<EventCommentModel> commentList;
    private ArrayList<TicketTypeModel> ticketTypes = new ArrayList(5);

    public EventModel(){}

    public EventModel(String name, String description, String imageUrl, int like, String place, String category,
                      int capacity, List<UserModel> organizers, Date salesStart, Date salesEnd, Date date,
                      int ticketSold, int ticketPrice, int moneyNeeded)
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.like = like;
        this.place = place;
        this.category = category;
        this.capacity = capacity;
        this.organizers = organizers;
        this.salesStart =salesStart;
        this.salesEnd = salesEnd;
        this.ticketPrice = ticketPrice;
        this.ticketSold = ticketSold;
        this.moneyNeeded = moneyNeeded;
        this.date = date;
    }

    public EventModel(String name, String description, String imageUrl, int capacity, Date salesStart, Date salesEnd, List<UserModel> organizers, int moneyNeeded, String place, String category)
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.capacity = capacity;
        this.salesStart=salesStart;
        this.salesEnd = salesEnd;
        this.organizers = organizers;
        this.moneyNeeded = moneyNeeded;
        this.place = place;
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<UserModel> getOrganizers() {
        return organizers;
    }

    public void setOrganizers(List<UserModel> organizers) {
        this.organizers = organizers;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTicketSold() {
        return ticketSold;
    }

    public void setTicketSold(int ticketSold) {
        this.ticketSold = ticketSold;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getMoneyNeeded() {
        return moneyNeeded;
    }

    public void setMoneyNeeded(int moneyNeeded) {
        this.moneyNeeded = moneyNeeded;
    }

    public Date getSalesStart() {
        return salesStart;
    }

    public void setSalesStart(Date salesStart) {
        this.salesStart = salesStart;
    }

    public Date getSalesEnd() {
        return salesEnd;
    }

    public void setSalesEnd(Date salesEnd) {
        this.salesEnd = salesEnd;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public ArrayList<EventCommentModel> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<EventCommentModel> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", place='" + place + '\'' +
                ", like=" + like +
                ", capacity=" + capacity +
                ", organizers=" + organizers +
                ", createAt=" + createAt +
                ", salesStart=" + salesStart +
                ", salesEnd=" + salesEnd +
                ", date=" + date +
                ", ticketSold=" + ticketSold +
                ", ticketPrice=" + ticketPrice +
                ", moneyNeeded=" + moneyNeeded +
                ", category='" + category + '\'' +
                ", commentList=" + commentList +
                ", ticketTypes=" + ticketTypes +
                '}';
    }

    public ArrayList<TicketTypeModel> getTicketTypes() {
        return ticketTypes;
    }

    public void addTicket(TicketTypeModel ticketType) {
        this.ticketTypes.add(ticketType);
    }

    public void delTickets(){this.ticketTypes = new ArrayList(5);}
}
