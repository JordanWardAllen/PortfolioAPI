package com.portfolioAPI.portfolioapi.Model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {

    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;

}
