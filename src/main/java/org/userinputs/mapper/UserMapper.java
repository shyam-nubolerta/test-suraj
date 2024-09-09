package org.userinputs.mapper;

import lombok.extern.slf4j.Slf4j;
import org.userinputs.entity.UserEntity;
import org.userinputs.model.UserDTO;

@Slf4j
public class UserMapper {

    public static UserDTO map (UserEntity userEntity ){
        return new UserDTO(userEntity.getId(),userEntity.getFirstName(), userEntity.getLastName(), userEntity.getBirthdate(),userEntity.getBirthplace(), userEntity.getSex(), userEntity.getCurrentaddress());

    }
}
