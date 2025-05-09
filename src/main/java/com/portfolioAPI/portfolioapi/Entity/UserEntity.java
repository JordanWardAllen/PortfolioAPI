package com.portfolioAPI.portfolioapi.Entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table
public class UserEntity {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;


    @NonNull
    @Column(unique = true)
    private String name;


    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String password;


    private String alias;

}
