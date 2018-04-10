package pl.edu.uj.cenuj.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@TableGenerator(name = "user", initialValue = 3)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user")
    private Long id;
    @Size(min = 2, max = 20, message = "{user.name.info}")
    private String name;
    private String surname;
    @Min(value = 18, message = "Wiek powinien być powyżej 18 lat.")
    @Max(value = 110, message = "Wiek powinien być poniżej 110 lat.")
    private int age;

    public User() {
    }

    public User(Long id, String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
