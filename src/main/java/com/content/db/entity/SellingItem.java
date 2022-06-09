package com.content.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SellingItem")

public class SellingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String manufacturer;
    private Integer productionYear;
    private String type;
    private String caliber;
    private String brand;
    private String escapement;
    private String winding;
    private String photoUrl;
    public SellingItem(String title, String manufacturer, Integer productionYear, String type, String caliber, String brand, String escapement, String winding, String photoUrl) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.productionYear = productionYear;
        this.type = type;
        this.caliber = caliber;
        this.brand = brand;
        this.escapement = escapement;
        this.winding = winding;
        this.photoUrl = photoUrl;
    }
}
