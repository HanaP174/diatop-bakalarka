package com.diatop.model.order;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.util.annotation.NonNull;

import java.time.LocalDate;

@Data
@Generated
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @Column("id")
    private Long id;

    @NonNull
    @Column("user_id")
    private Long userId;

    @Column("appointment_date")
    private LocalDate appointmentDate;

    @Column("delivery_date")
    private LocalDate deliveryDate;

    @Column("street")
    private String street;

    @Column("street_number")
    private String streetNumber;

    @Column("city")
    private String city;

    @Column("zipcode")
    private String zipcode;

    @Column("note")
    private String note;

    @Column("personal_delivery")
    private Boolean personalDelivery;
}
