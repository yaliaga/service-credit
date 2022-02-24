package pe.com.service.credit.service;

import java.util.Date;

import pe.com.service.credit.model.dto.CreditDto;
import pe.com.service.credit.utils.ParamReport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

	Flux<CreditDto> read();

	Mono<CreditDto> create(Mono<CreditDto> creditDtoMono);

	Mono<CreditDto> update(Mono<CreditDto> creditDtoMono, String id);

	Mono<Void> delete(String id);

	Mono<CreditDto> findById(String id);

	Flux<CreditDto> reportBetween(Date init, Date end, String productName);

	Flux<CreditDto> reportBetweenInterval(ParamReport paramReport);

}
