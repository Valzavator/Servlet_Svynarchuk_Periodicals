package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Maksym Svynarchuk
 * <p>
 * Class that represents type of periodicals
 * @see Periodical
 */
public class PeriodicalType implements Serializable {
    private Integer id;
    private String name;
    private String description;

    public static class Builder {
        private final PeriodicalType periodicalType;

        public Builder() {
            periodicalType = new PeriodicalType();
        }

        public Builder setId(Integer id) {
            periodicalType.setId(id);
            return this;
        }

        public Builder setName(String name) {
            periodicalType.setName(name);
            return this;
        }

        public Builder setDescription(String description) {
            periodicalType.setDescription(description);
            return this;
        }

        public PeriodicalType build() {
            return periodicalType;
        }

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public PeriodicalType() {
    }

    public PeriodicalType(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodicalType that = (PeriodicalType) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PeriodicalType.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
