package pe.com.service.credit.service.impl;

import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import pe.com.service.credit.model.Credit;
import pe.com.service.credit.model.dto.CreditDto;
import pe.com.service.credit.repository.CreditRegistry;
import pe.com.service.credit.service.CreditService;
import pe.com.service.credit.utils.CreditUtil;
import pe.com.service.credit.utils.DateUtil;
import pe.com.service.credit.utils.ParamReport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

	private final static Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);
	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;
	@Autowired
	private CreditRegistry creditRegistry;

	@Override
	public Flux<CreditDto> read() {
		logger.info("inside methode read");
        return creditRegistry.findAll().map(CreditUtil::entityToDto);
	}

	@Override
	public Mono<CreditDto> create(Mono<CreditDto> creditDtoMono) {
		logger.info("inside methode create");

		var newCreditDtoMono = creditDtoMono.map(p -> {
			var today = LocalDateTime.now();
			p.setDateStart(DateUtil.toDate(today));
			p.setDateLimit(DateUtil.toDate(today.plusDays(15)));
			return p;
		});

		return newCreditDtoMono.map(CreditUtil::dtoToEntity).flatMap(creditRegistry::save)
				.map(CreditUtil::entityToDto);
	}

	@Override
	public Mono<CreditDto> update(Mono<CreditDto> creditDtoMono, String id) {
		logger.info("inside methode update");
		return creditRegistry.findById(id)
				.flatMap(p -> creditDtoMono.map(CreditUtil::dtoToEntity).doOnNext(e -> e.setId(id)))
				.flatMap(creditRegistry::save).map(CreditUtil::entityToDto);
	}

	@Override
	public Mono<Void> delete(String id) {
		return creditRegistry.deleteById(id);
	}

	@Override
	public Mono<CreditDto> findById(String id) {
		return creditRegistry.findById(id).map(CreditUtil::entityToDto);
	}

	@Override
	public Flux<CreditDto> reportBetween(Date init, Date end, String productName) {
		logger.info("inside methode reportBetween ");
		Query query = new Query();
		query.addCriteria(Criteria.where("dateStart").gte(DateUtil.toLocalDateTime(init))
				.lte(DateUtil.toLocalDateTime(end)).and("nameProduct").is(productName));
		return reactiveMongoTemplate.find(query, Credit.class).map(CreditUtil::entityToDto);
	}

	@Override
	public Flux<CreditDto> reportBetweenInterval(ParamReport paramReport) {
		logger.info("inside methode reportBetweenInterval");
		Query query = new Query();
		query.addCriteria(Criteria.where("dateStart").gte(DateUtil.toLocalDateTime(paramReport.getInit()))
				.lte(DateUtil.toLocalDateTime(paramReport.getEnd())).and("nameProduct")
				.is(paramReport.getProductName()));
		return reactiveMongoTemplate.find(query, Credit.class).map(CreditUtil::entityToDto);
	}

}
