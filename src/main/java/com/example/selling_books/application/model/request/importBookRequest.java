package com.example.selling_books.application.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class importBookRequest {

    long id;

    int quantities;
}
