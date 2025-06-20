package com.kushbhakkad.nseapicall.model;

import jakarta.persistence.*;

@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String smName;

    @Column(length = 1000)
    private String attchmntText;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getAttchmntText() {
        return attchmntText;
    }

    public void setAttchmntText(String attchmntText) {
        this.attchmntText = attchmntText;
    }
}
