package org.userinputs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userID;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String birthplace;
    private String sex;
    private String currentAddress;
}
