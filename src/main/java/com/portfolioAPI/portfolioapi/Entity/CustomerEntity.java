package com.portfolioAPI.portfolioapi.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {

    @Id @Column
    private UUID id;

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
}
