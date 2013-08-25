package com.vividcode.imap.server.model;

import com.vividcode.imap.common.shared.type.LearnStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Learn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private Double note;
    private Date created;
    @ManyToOne
    private User owner;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Tag> tags;
    @Enumerated(EnumType.STRING)
    private LearnStatus status;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public LearnStatus getStatus() {
        return status;
    }

    public void setStatus(LearnStatus status) {
        this.status = status;
    }
}
