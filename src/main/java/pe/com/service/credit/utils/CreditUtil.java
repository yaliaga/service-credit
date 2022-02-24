package pe.com.service.credit.utils;

import org.springframework.beans.BeanUtils;

import pe.com.service.credit.model.Credit;
import pe.com.service.credit.model.dto.CreditDto;

public class CreditUtil {
	 public static CreditDto entityToDto(Credit credit){
	        var creditDto=new CreditDto();
	        BeanUtils.copyProperties(credit,creditDto);
	        return creditDto;
	    }
	    public static Credit dtoToEntity(CreditDto creditDto){
	        var entity=new Credit();
	        BeanUtils.copyProperties(creditDto,entity);
	        return entity;
	    }
}
