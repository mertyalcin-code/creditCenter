package com.mertyalcin.creditcenter.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name = "id")
    private long id;

    @Column(name = "credit_amount")
    private double creditAmount;

    @Column(name="creation_time")
    private  LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private  Customer customer;
}
