package app.entity;

import app.enums.CatType;
import app.enums.CatColor;
import app.enums.*;

import javax.persistence.*;

@Entity
@Table(name = "cats2")
public class CatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "passportowner")
    private int passportOwner;

    @Column(name = "date")
    private String date;
    @Column(name = "breed")
    private CatType breed;
    @Column(name = "color")
    private CatColor color;
    @Column(name = "passport_code")
    private int passportCode;

    public CatEntity() {
    }

    public CatEntity(int passportOwner, String date, CatType breed, CatColor color, int passportCode) {
        this.passportOwner = passportOwner;
        this.date = date;
        this.breed = breed;
        this.color = color;
        this.passportCode = passportCode;
    }

    public int getPassportCode() {
        return passportCode;
    }

    public String getDate() {
        return date;
    }

    public CatType getBreed() {
        return breed;
    }

    public CatColor getColor() {
        return color;
    }

    public int getPassportOwner() {
        return passportOwner;
    }

    public int getId() {
        return id;
    }
}
