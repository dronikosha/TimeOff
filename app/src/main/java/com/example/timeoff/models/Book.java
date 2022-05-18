package com.example.timeoff.models;

public class Book {
    public String people;
    public String date;
    public String start_time;
    public String end_time;

    public Book(String people, String date, String start_time, String end_time) {
        this.people = people;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Book() {}

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
