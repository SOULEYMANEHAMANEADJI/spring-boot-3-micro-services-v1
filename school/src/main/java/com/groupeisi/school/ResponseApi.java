package com.groupeisi.school;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi<T> {
    private int status;      // HTTP status code (ex: 200, 201, 404)
    private String message;  // Message explicite ("success", "not found", etc)
    private T data;          // payload (Student, List<Student>, null)
}
