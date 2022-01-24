package com.portfolioAPI.portfolioapi.Model;

import lombok.*;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Customer {

    private UUID id;

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;

}
