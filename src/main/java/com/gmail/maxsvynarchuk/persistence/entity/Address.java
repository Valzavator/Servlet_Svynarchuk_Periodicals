package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Maksym Svynarchuk
 * <p>
 * Class that represents address of user
 * @see User
 */
public class Address implements Serializable {
    private Long id;
    private String country;
    private String city;
    private String postIndex;
    private String detailAddress;

    public static class Builder {
        private final Address address;

        public Builder() {
            address = new Address();
        }

        public Builder setId(Long id) {
            address.setId(id);
            return this;
        }

        public Builder setCountry(String country) {
            address.setCountry(country);
            return this;
        }

        public Builder setCity(String city) {
            address.setCity(city);
            return this;
        }

        public Builder setPostIndex(String postIndex) {
            address.setPostIndex(postIndex);
            return this;
        }

        public Builder setDetailAddress(String detailAddress) {
            address.setDetailAddress(detailAddress);
            return this;
        }

        public Address build() {
            return address;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(String postIndex) {
        this.postIndex = postIndex;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("country='" + country + "'")
                .add("city='" + city + "'")
                .add("postIndex='" + postIndex + "'")
                .add("detailAddress='" + detailAddress + "'")
                .toString();
    }
}
