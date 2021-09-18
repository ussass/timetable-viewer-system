package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.MyEntity;

import javax.persistence.*;

@Entity
@Table(name = "classrooms")
public class Classroom implements MyEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Long id;

    @Column(name = "classroom_number")
    private int number;

    public Classroom() {
    }

    public Classroom(Long id) {
        this.id = id;
    }

    public Classroom(int number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
