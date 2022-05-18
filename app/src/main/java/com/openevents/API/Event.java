package com.openevents.API;

public class Event {
    private Integer id;
    private String name;
    private Integer owner_id;
    private String date;
    private String image;
    private String location;
    private String description;
    private String eventStart_date;
    private String eventEnd_date;
    private Integer n_participators;
    private String slug;
    private String type;

    public Event(Integer id, String name, Integer owner_id, String date, String image, String location, String description, String eventStart_date, String eventEnd_date, Integer n_participators, String slug, String type) {
        this.id = id;
        this.name = name;
        this.owner_id = owner_id;
        this.date = date;
        this.image = image;
        this.location = location;
        this.description = description;
        this.eventStart_date = eventStart_date;
        this.eventEnd_date = eventEnd_date;
        this.n_participators = n_participators;
        this.slug = slug;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventStart_date() {
        return eventStart_date;
    }

    public void setEventStart_date(String eventStart_date) {
        this.eventStart_date = eventStart_date;
    }

    public String getEventEnd_date() {
        return eventEnd_date;
    }

    public void setEventEnd_date(String eventEnd_date) {
        this.eventEnd_date = eventEnd_date;
    }

    public Integer getN_participators() {
        return n_participators;
    }

    public void setN_participators(Integer n_participators) {
        this.n_participators = n_participators;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
