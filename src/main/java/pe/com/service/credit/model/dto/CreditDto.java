package pe.com.service.credit.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditDto {
	private String id;
    private String clientId;
    private String nameProduct;
    private double creditLine;
    private double availableBalance;
    private double spending;
    private double payments;
    private Date dateStart;
    private Date dateLimit;
    private int numberDueTotal;
    private int numberDuePending;
}
