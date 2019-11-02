package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Maksym Svynarchuk
 * <p>
 * Class that represents frequency of periodical
 * @see Periodical
 */
public class Frequency implements Serializable {
    private Integer id;
    private String name;

    public Frequency() {
    }

    public Frequency(Integer id, String name) {
        setId(id);
        setName(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frequency frequency = (Frequency) o;
        return Objects.equals(getId(), frequency.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Frequency.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
