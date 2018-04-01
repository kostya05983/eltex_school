package Baskets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="Credentials")
public class Credentials implements Serializable{
    @Id
    @Column(name="id")
    private UUID id;
    @Column(name="surname")
    private String surname;
    @Column(name="name")
    private String name;
    @Column(name="patronymic")
    private String patronymic;
    @Column(name="email")
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return "id="+this.id+" Имя "+this.name+" Фамилия "+this.surname+" Отчество "+this.patronymic+" email="+this.email;
    }
}
