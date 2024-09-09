package org.userinputs.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.userinputs.exception.UpdateFailedException;
import org.userinputs.exception.UpdateRequestMalFormedException;
import org.userinputs.repository.MandatoryFieldsRepository;
import org.userinputs.repository.UserRepository;
import org.userinputs.entity.MandatoryFieldsEntity;
import org.userinputs.entity.UserEntity;
import org.userinputs.exception.UserNotFoundException;
import org.userinputs.mapper.UserMapper;
import org.userinputs.model.UserDTO;
import org.userinputs.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.userinputs.validator.UpdateRequestValidator;

import java.lang.reflect.Field;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public  static final String USER_ID_VARIABLE= "userId";
    public static final String DEFAULT_VALUE_VARIABLE= "<Please enter value>";


    private final UserRepository userRepository;

    private final MandatoryFieldsRepository mandatoryFieldsRepository;


    public UserServiceImpl(UserRepository userRepository, MandatoryFieldsRepository mandatoryFieldsRepository) {
        this.userRepository = userRepository;
        this.mandatoryFieldsRepository = mandatoryFieldsRepository;
    }

    public Map<String,String> getMissingFileds(String userID,String tableName) throws UserNotFoundException, InstantiationException, IllegalAccessException {

        Map<String , String> requiredFiledMap = new HashMap<>();
        Optional<UserEntity> user = userRepository.findById(Long.valueOf(userID));
        if(user.isEmpty()){
            throw new UserNotFoundException("User Not found for "+userID);
        }
        List<MandatoryFieldsEntity> requiredFileds = mandatoryFieldsRepository.findByTablename(tableName);
        mandatoryFieldsRepository.findByTablename(tableName);
        userRepository.findAll();
        if(CollectionUtils.isEmpty(requiredFileds)){
            return Collections.emptyMap();
        }
        Field[] declaredFields = user.get().getClass().getDeclaredFields();
        for(Field filed : declaredFields){
            if(requiredFileds.stream().anyMatch(reqF-> StringUtils.equalsIgnoreCase(reqF.getReqfieldname(),filed.getName()))){
                Object value = getFieldValue(filed, user.get());
                if(value==null||(value instanceof String &&StringUtils.isBlank((String)value))){
                    requiredFiledMap.put(filed.getName(),DEFAULT_VALUE_VARIABLE);
                }
            }

        }
        if(!CollectionUtils.isEmpty(requiredFiledMap)){
            requiredFiledMap.put(USER_ID_VARIABLE,String.valueOf(user.get().getId()));
        }
        return requiredFiledMap;
    }

    private static Object getFieldValue(Field field, Object target) {
        try {
            field.setAccessible(true);
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserDTO> getAllUsers(){
        List<UserDTO> userList = new ArrayList<>();
          userRepository.findAll().forEach(userEntity -> {
              userList.add(UserMapper.map(userEntity));
          });
          return userList;
    }

    public UserDTO updateUserDetails( Map<String, String> updateDetails) throws UserNotFoundException, UpdateFailedException, UpdateRequestMalFormedException, InstantiationException, IllegalAccessException {

        UpdateRequestValidator.validateUpdate(updateDetails,getMissingFileds(updateDetails.get(USER_ID_VARIABLE), "usersDetails"));
        Optional<UserEntity> user = userRepository.findById(Long.valueOf(updateDetails.get(USER_ID_VARIABLE)));

        if (user.isEmpty()) {
            throw new UserNotFoundException("User Not found for " + updateDetails.get(USER_ID_VARIABLE));
        }
        Field[] declaredFields = user.get().getClass().getDeclaredFields();
        int updatedFiled = 0;
        for (Field filed : declaredFields) {
            if (updateDetails.keySet().stream().anyMatch(reqF -> StringUtils.equalsIgnoreCase(reqF, filed.getName()))) {
                setFieldValue(filed, user.get(), updateDetails.get(filed.getName()));
                    updatedFiled++;
            }
        }
        if (updatedFiled == updateDetails.size()-1) {
            return UserMapper.map(userRepository.save(user.get()));
        }
        throw new UpdateFailedException("Object value mapping failed");
    }


    private void setFieldValue(Field field, UserEntity userEntity,Object value) {
        try {
            field.setAccessible(true);
            field.set(userEntity,value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }



}
