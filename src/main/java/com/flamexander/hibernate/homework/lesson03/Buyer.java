package com.flamexander.hibernate.homework.lesson03;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "buyer")
public class Buyer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "buyers_goods", joinColumns = @JoinColumn(name = "buyer_id"), inverseJoinColumns = @JoinColumn(name = "good_id"))
    private List<Good> goods;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void buy(Good good){
        if(good.getBuyers().contains(this)){
            return;
        }
        goods.add(good);
    }

    public Buyer(String name) {
        this.name = name;
    }

    public Buyer() {
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
