package org.userinputs.service;


import org.userinputs.external.request.User;
import org.userinputs.model.UserDTO;

public interface ExternalSystemService {
    void sendUserInfo(UserDTO userDTO);
}
