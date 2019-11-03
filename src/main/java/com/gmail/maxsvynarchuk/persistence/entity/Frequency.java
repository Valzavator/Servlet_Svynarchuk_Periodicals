package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Class that represents frequency of periodical
 *
 * @author Maksym Svynarchuk
 * @see Periodical
 */
public class Frequency implements Serializable {
    private static final long serialVersionUID = -7403178438448003318L;

    private Integer id;
    private String name;
    private String meaning;

    public static class Builder {
        private final Frequency frequency;

        public Builder() {
            frequency = new Frequency();
        }

        public Builder setId(Integer id) {
            frequency.setId(id);
            return this;
        }

        public Builder setName(String name) {
            frequency.setName(name);
            return this;
        }

        public Builder setMeaning(String meaning) {
            frequency.setMeaning(meaning);
            return this;
        }

        public Frequency build() {
            return frequency;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Frequency() {
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

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
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
                .add("meaning='" + meaning + "'")
                .toString();
    }
}
