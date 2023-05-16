package com.estore.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offerId;

    @Column(name = "offer_name")
    private String offerName;

    @Column(name = "offer_description")
    private String offerDescription;

    @Column(name = "discount")
    private double discount;

    @Column(name = "is_offer_active")
    private boolean isOfferActive;

    @Column(name = "offer_start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime offerStartDate;





    @Column(name = "offer_end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime offerEndDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "offers")
    private List<Product> products;


    @Column(name="date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateCreated;


    @JsonIgnore
    @OneToMany(mappedBy = "offers")
    private List<Product> product;


    @Column(name="last_updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime lastUpdated; ;

    public Offers(int offerId, String offerName) {
    }

    public Offers(int offerId, String offerName, boolean offerActive) {
    }
}
