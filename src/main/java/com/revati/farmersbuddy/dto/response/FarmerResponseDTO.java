package com.revati.farmersbuddy.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmerResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String village;

}
