package pe.com.service.credit.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "credit")
public class Credit {
	
	@Id
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
