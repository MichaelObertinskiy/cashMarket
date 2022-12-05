package com.example.cashMarket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "commodities")
@Data
@ToString(of = {"id", "uuid", "barcode", "name", "taxgroup", "groupOfGoods", "cost"})
@EqualsAndHashCode(of = {"id"})
public class Commodity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Integer id;


    
    @Column(name = "uuid", updatable = false, columnDefinition = "VARCHAR(255)")
    private UUID uuid;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "name")
    private String name;
    @Column(name = "taxgroup")
    private String taxgroup;
    @Column(name = "groupOfGoods")
    private String groupOfGoods;
    private String trademark;
    private Double cost;
    private Double amount;

    @Column(updatable = false, columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(updatable = false, columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Column(updatable = false, columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;

    public Commodity() {

    }

    public Commodity(
            Integer id,
            UUID uuid,
            String barcode,
            String name,
            String taxgroup,
            String groupOfGoods,
            String trademark,
            Double cost,
            Double amount,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        this.id = id;
        this.uuid = uuid;
        this.barcode = barcode;
        this.name = name;
        this.taxgroup = taxgroup;
        this.groupOfGoods = groupOfGoods;
        this.trademark = trademark;
        this.cost = cost;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", taxgroup='" + taxgroup + '\'' +
                ", groupOfGoods='" + groupOfGoods + '\'' +
                ", trademark='" + trademark + '\'' +
                ", cost=" + cost +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxgroup() {
        return taxgroup;
    }

    public void setTaxgroup(String taxgroup) {
        this.taxgroup = taxgroup;
    }

    public String getGroupOfGoods() {
        return groupOfGoods;
    }

    public void setGroupOfGoods(String groupOfGoods) {
        this.groupOfGoods = groupOfGoods;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
