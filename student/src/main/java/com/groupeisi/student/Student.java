package com.groupeisi.student;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_tbl")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50)
    private String lastName;

    @Email(message = "L'email doit être valide")
    @NotBlank(message = "L'email est obligatoire")
    @Size(max = 120)
    private String email;
    private Integer schoolId;
}
