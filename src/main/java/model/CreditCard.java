package model;

import javax.persistence.*;

/**
 * Created by ramzes on 07.12.2016.
 */
@Entity
@Table(name = "CreditCard")
public class CreditCard {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="type")
    private String type;

    @Column(name = "number")
    private String number;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}//class
