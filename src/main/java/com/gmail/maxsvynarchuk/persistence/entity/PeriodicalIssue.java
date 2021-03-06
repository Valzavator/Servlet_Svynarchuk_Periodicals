package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Class that represents periodical issue
 *
 * @author Maksym Svynarchuk
 * @see Periodical
 */
public class PeriodicalIssue implements Serializable {
    private static final long serialVersionUID = 6582640959890366280L;

    private Long id;
    private String name;
    private String issueNumber;
    private LocalDate publicationDate;
    private String description;
    private Periodical periodical;

    public static class Builder {
        private final PeriodicalIssue periodicalIssue;

        public Builder() {
            periodicalIssue = new PeriodicalIssue();
        }

        public Builder setId(Long id) {
            periodicalIssue.setId(id);
            return this;
        }

        public Builder setName(String name) {
            periodicalIssue.setName(name);
            return this;
        }

        public Builder setIssueNumber(String issueNumber) {
            periodicalIssue.setIssueNumber(issueNumber);
            return this;
        }

        public Builder setPublicationDate(LocalDate publicationDate) {
            periodicalIssue.setPublicationDate(publicationDate);
            return this;
        }

        public Builder setDescription(String description) {
            periodicalIssue.setDescription(description);
            return this;
        }

        public Builder setPeriodical(Periodical periodical) {
            periodicalIssue.setPeriodical(periodical);
            return this;
        }

        public PeriodicalIssue build() {
            return periodicalIssue;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public PeriodicalIssue() {
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

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodicalIssue that = (PeriodicalIssue) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PeriodicalIssue.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("issueNumber=" + issueNumber)
                .add("publicationDate=" + publicationDate)
                .add("description='" + description + "'")
                .add("periodical=" + periodical)
                .toString();
    }
}
