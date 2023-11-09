package com.trollMarket.TrollMarket.controller;


import com.trollMarket.TrollMarket.dto.utility.ValidationDTO;
import com.trollMarket.TrollMarket.utility.MapperHelper;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRestController {


     List<ValidationDTO> getErrors(List <ObjectError> errors){
        var dto = new ArrayList<ValidationDTO>();
        for(var error : errors){
            String fieldName = MapperHelper.getStringField(error.getArguments()[0],"defaultMessage");
            fieldName = (fieldName.equals(""))? "object" : fieldName;
            String message = error.getDefaultMessage();
            dto.add(new ValidationDTO(fieldName, message));
        }
        return dto;
    }
}
