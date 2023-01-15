package com.example.cashMarket.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@ToString(of = {"firstName", "lastName"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Integer id;
    @Column(name = "uuid", updatable = false, nullable = false, columnDefinition = "VARCHAR(255)")
    private String uuid;
    @Column(name = "role")
    private String role;
    @Column(name = "is_blocked")
    private Boolean isBlocked;
    @Column(name = "code")
    private String code;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "user")
    private Set<PriceList> priceList;

    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<PriceList> getPriceList() {
        return priceList;
    }

    public void setPriceList(Set<PriceList> priceList) {
        this.priceList = priceList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
