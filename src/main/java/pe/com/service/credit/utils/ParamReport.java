package pe.com.service.credit.utils;

import java.util.Date;

import lombok.Data;

@Data
public class ParamReport {
	private String productName;
    private Date init;
    private Date end;
}
