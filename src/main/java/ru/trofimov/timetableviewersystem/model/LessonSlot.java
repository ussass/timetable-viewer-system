package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.Entity;

public class LessonSlot implements Entity<Long> {
    private Long id;
    private int number;
    private int minStart;

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
