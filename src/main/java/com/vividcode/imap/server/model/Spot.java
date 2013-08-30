package com.vividcode.imap.server.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(length = 2000)
    private String description;
    private String owner;
    private Date created;
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;
    @ManyToOne
    private User creator;
    @ManyToOne
    private TypeSpot type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public TypeSpot getType() {
        return type;
    }

    public void setType(TypeSpot type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Spot{id=" + id + ", title='" + title + '\'' + ", type=" + type + '}';
    }
}
