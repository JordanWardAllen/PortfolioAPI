package com.portfolioAPI.portfolioapi.Model;

import lombok.*;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Customer {

    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;

}
