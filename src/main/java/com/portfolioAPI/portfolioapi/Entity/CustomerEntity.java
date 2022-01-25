package com.portfolioAPI.portfolioapi.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table
public class CustomerEntity {

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
    @Column
    private Long id;

    @Column(
            name = "name",
            length = 36
    )
    @NonNull
    private String name;

    @Column(
            name = "email",
            length = 50
    )
    @NonNull
    private String email;

    //Encoding to be implemented later
    @Column(
            name = "password",
            length = 16
    )
    @NonNull
    private String password;
}
