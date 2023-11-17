package com.example.csis3175_grp6_proj.models;

public class BookingConfirmation {
    private String Topic;
    private String Information;

    public BookingConfirmation(String topic, String information) {
        Topic = topic;
        Information = information;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }
}
