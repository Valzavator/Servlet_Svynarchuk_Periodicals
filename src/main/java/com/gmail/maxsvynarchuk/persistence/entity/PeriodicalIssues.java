package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Maksym Svynarchuk
 * <p>
 * Class that represents periodical issue
 * @see Periodical
 */
public class PeriodicalIssues implements Serializable {
    private Long id;
    private String name;
    private Long issueNumber;
    private Date publicationDate;
    private String description;
    private Periodical periodical;

    public static class Builder {
        private final PeriodicalIssues periodicalIssues;

        public Builder() {
            periodicalIssues = new PeriodicalIssues();
        }

        public Builder setId(Long id) {
            periodicalIssues.setId(id);
            return this;
        }

        public Builder setName(String name) {
            periodicalIssues.setName(name);
            return this;
        }

        public Builder setIssueNumber(Long issueNumber) {
            periodicalIssues.setIssueNumber(issueNumber);
            return this;
        }

        public Builder setPublicationDate(Date publicationDate) {
            periodicalIssues.setPublicationDate(publicationDate);
            return this;
        }

        public Builder setDescription(String description) {
            periodicalIssues.setDescription(description);
            return this;
        }

        public Builder setPeriodical(Periodical periodical) {
            periodicalIssues.setPeriodical(periodical);
            return this;
        }

        public PeriodicalIssues build() {
            return periodicalIssues;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public PeriodicalIssues() {
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

    public Long getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Long issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
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
        PeriodicalIssues that = (PeriodicalIssues) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PeriodicalIssues.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("issueNumber=" + issueNumber)
                .add("publicationDate=" + publicationDate)
                .add("description='" + description + "'")
                .add("periodical=" + periodical)
                .toString();
    }
}
