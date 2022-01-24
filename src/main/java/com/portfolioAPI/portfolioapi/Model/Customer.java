package com.portfolioAPI.portfolioapi.Model;

import lombok.*;


import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
@Builder
public class Customer {

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

    private UUID id;

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;




}
