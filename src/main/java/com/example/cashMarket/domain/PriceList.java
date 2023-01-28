package com.example.cashMarket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "price_lists")
@Data
@ToString(of = {"id",})
@EqualsAndHashCode(of = {"id"})
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "uuid", updatable = false, columnDefinition = "VARCHAR(255)")
    private String uuid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "amount_of_positions")
    private Integer amountOfPositions;
    @Column(name = "weight")
    private Double weight;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.ALL
        })
    @JoinTable(name = "price_list_commodities",
    joinColumns = @JoinColumn(name = "price_list_id"),
    inverseJoinColumns = @JoinColumn(name = "commodity_id"))
    private List<Commodity> commodities = new ArrayList<>();

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Column(name = "closed_at", columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;

    public PriceList() {
    }

    public PriceList(
            String uuid,
            User user,
            Double totalPrice,
            Integer amountOfPositions,
            Double weight,
            LocalDateTime createdAt
    ) {
        this.uuid = uuid;
        this.user = user;
        this.totalPrice = totalPrice;
        this.amountOfPositions = amountOfPositions;
        this.weight = weight;
        this.createdAt = createdAt;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAmountOfPositions() {
        return amountOfPositions;
    }

    public void setAmountOfPositions(Integer amountOfPositions) {
        this.amountOfPositions = amountOfPositions;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }

    public void addCommodity(Commodity commodity) {
        this.commodities.add(commodity);
        commodity.getPriceLists().add(this);
    }

    public void removeCommodity(int commodityId) {
        Commodity commodity = this
                        .commodities
                        .stream()
                        .filter(t -> t.getId() == commodityId)
                        .findFirst()
                        .orElse(null);
        while(commodities.contains(commodity)){
            this.commodities.remove(commodity);
            assert commodity != null;
            commodity.getPriceLists().remove(this);
        }
//        if(commodity != null) {
//            this.commodities.remove(commodity);
//            commodity.getPriceLists().remove(this);
//        }
    }
}
