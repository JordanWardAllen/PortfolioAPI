package com.portfolioAPI.portfolioapi.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    @Email
    @Column(unique = true)
    private String email;

    @NonNull
    private String password;

    private String alias;
}
