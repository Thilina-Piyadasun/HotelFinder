package com.cgn.reservation.dao;

import javax.persistence.*;

/**
 * Created by acer on 4/10/2017.
 */
@Entity
@Table(name = "room", schema = "", catalog = "hoteldb")
public class RoomEntity {
    private byte id;
    private String type;
    private int price;
    private String city;

    @Id
    @Column(name = "id")
    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
