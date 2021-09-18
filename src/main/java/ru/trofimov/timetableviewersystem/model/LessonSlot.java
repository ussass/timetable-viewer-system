package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.MyEntity;

import javax.persistence.*;

@Entity
@Table(name = "lesson_slot")
public class LessonSlot implements MyEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_slot_id")
    private Long id;

    @Column(name = "lesson_slot_number")
    private int number;

    @Column(name = "min_start")
    private int minStart;

    public LessonSlot() {
    }

    public LessonSlot(Long id) {
        this.id = id;
    }

    public LessonSlot(int number, int minStart) {
        this.number = number;
        this.minStart = minStart;
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

    public int getMinStart() {
        return minStart;
    }

    public String lessonTime(){
        int end = minStart + 90;
        return "(" + minStart / 60 + ":" + (minStart % 60 == 0 ? "00" : minStart % 60) + " - " +
                end / 60 + ":" + (end % 60 == 0 ? "00" : end % 60) + ")";
    }

    public void setMinStart(int minStart) {
        this.minStart = minStart;
    }

    @Override
    public String toString() {
        return "LessonSlot{" +
                "id=" + id +
                ", number=" + number +
                ", lessonTime=" + lessonTime() +
                '}';
    }
}
