package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Class that represents periodical of some type published by some publisher
 *
 * @author Maksym Svynarchuk
 * @see PeriodicalType
 * @see Frequency
 * @see Publisher
 */
public class Periodical implements Serializable {
    private static final long serialVersionUID = -8852341448368620250L;
    private static final int PRICE_SCALE = 2;

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Publisher publisher;
    private Frequency frequency;
    private PeriodicalType periodicalType;

    public static class Builder {
        private final Periodical periodical;

        public Builder() {
            periodical = new Periodical();
        }

        public Builder setId(Long id) {
            periodical.setId(id);
            return this;
        }

        public Builder setName(String name) {
            periodical.setName(name);
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            periodical.setPrice(price);
            return this;
        }

        public Builder setDescription(String description) {
            periodical.setDescription(description);
            return this;
        }

        public Builder setPublisher(Publisher publisher) {
            periodical.setPublisher(publisher);
            return this;
        }

        public Builder setFrequency(Frequency frequency) {
            periodical.setFrequency(frequency);
            return this;
        }

        public Builder setPeriodicalType(PeriodicalType periodicalType) {
            periodical.setPeriodicalType(periodicalType);
            return this;
        }

        public Periodical build() {
            return periodical;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Periodical() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.scale() != PRICE_SCALE)
            this.price = price.setScale(PRICE_SCALE, RoundingMode.HALF_EVEN);
        else
            this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public PeriodicalType getPeriodicalType() {
        return periodicalType;
    }

    public void setPeriodicalType(PeriodicalType periodicalType) {
        this.periodicalType = periodicalType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodical that = (Periodical) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Periodical.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("description='" + description + "'")
                .add("publisher=" + publisher)
                .add("frequency=" + frequency)
                .add("periodicalType=" + periodicalType)
                .toString();
    }
}
