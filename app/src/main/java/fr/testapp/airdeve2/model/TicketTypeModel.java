package fr.testapp.airdeve2.model;

import android.text.TextUtils;

public class TicketTypeModel {
    private int id;
    private int price;
    private int amount;
    private String name;
    private String description;

    public TicketTypeModel(int id, int price, int amount, String name, String description) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public TicketTypeModel(int price, int amount, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "TicketTypeModel{" +
                "id=" + id +
                ", price=" + price +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }
}
