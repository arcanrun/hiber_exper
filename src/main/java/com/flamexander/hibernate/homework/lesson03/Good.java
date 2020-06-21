package com.flamexander.hibernate.homework.lesson03;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "good")
public class Good implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @ManyToMany
    @JoinTable(name = "buyers_goods", joinColumns = @JoinColumn(name = "good_id"), inverseJoinColumns = @JoinColumn(name = "buyer_id"))
    List<Buyer> buyers;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }




    public int getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Good() {
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public Good(String title, int price) {
        this.title = title;
        this.price = price;
    }
}
