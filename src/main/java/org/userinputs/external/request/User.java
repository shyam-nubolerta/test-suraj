package org.userinputs.external.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String birthdate;
    @NotBlank
    private String birthplace;
    @NotBlank
    private String sex;
    @NotBlank
    private String currentAddress;
}
