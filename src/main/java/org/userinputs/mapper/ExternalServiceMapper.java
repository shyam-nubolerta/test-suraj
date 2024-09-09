package org.userinputs.mapper;

import org.userinputs.external.request.User;
import org.userinputs.model.UserDTO;

public class ExternalServiceMapper {

    public static User mapUser(UserDTO userDTO){
        return new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getBirthdate(), userDTO.getBirthplace(),
                userDTO.getSex(), userDTO.getCurrentAddress());
    }

}
