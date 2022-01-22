package com.portfolioAPI.portfolioapi.Model;

import lombok.*;


import javax.persistence.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class CustomerModel {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )

    private long id;

    @NonNull
    private String name;
    @NonNull
    private String email;


}
