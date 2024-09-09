package org.userinputs.service;

import org.userinputs.exception.UpdateFailedException;
import org.userinputs.exception.UpdateRequestMalFormedException;
import org.userinputs.exception.UserNotFoundException;
import org.userinputs.model.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
     Map<String,String> getMissingFileds(String userID, String tableName) throws UserNotFoundException, InstantiationException, IllegalAccessException;

     List<UserDTO> getAllUsers();

     UserDTO updateUserDetails( Map<String, String> updateDetails) throws UserNotFoundException, UpdateFailedException, UpdateRequestMalFormedException, InstantiationException, IllegalAccessException;
}
