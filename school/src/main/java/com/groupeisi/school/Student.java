package com.groupeisi.school;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Integer schoolId;
}