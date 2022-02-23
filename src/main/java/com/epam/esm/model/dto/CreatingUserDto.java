package com.epam.esm.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreatingUserDto {

    String email;

    String password;

    String firstName;

    String lastName;

}
