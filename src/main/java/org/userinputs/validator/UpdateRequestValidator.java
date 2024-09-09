package org.userinputs.validator;

import org.userinputs.exception.UpdateRequestMalFormedException;
import org.userinputs.exception.UserNotFoundException;

import java.util.Map;

import static org.userinputs.service.impl.UserServiceImpl.DEFAULT_VALUE_VARIABLE;
import static org.userinputs.service.impl.UserServiceImpl.USER_ID_VARIABLE;

public class UpdateRequestValidator {



    public static  void  validateUpdate(Map<String, String> updateDetails,Map<String, String> requiredFileds ) throws UpdateRequestMalFormedException, UserNotFoundException, InstantiationException, IllegalAccessException {
        if(!updateDetails.containsKey(USER_ID_VARIABLE)){
            throw new UpdateRequestMalFormedException("User id filed is removed , please try getting missing fileds again");
        }

        if(!(updateDetails.size()== requiredFileds.size()&&updateDetails.keySet().containsAll(requiredFileds.keySet()))){
            throw new UpdateRequestMalFormedException("Requested fields and update fileds Doesn't Match , please try getting missing fileds again");
        }
        if(updateDetails.containsValue(DEFAULT_VALUE_VARIABLE)){
            throw new UpdateRequestMalFormedException("Requested fields are not update");
        }

    }
}
