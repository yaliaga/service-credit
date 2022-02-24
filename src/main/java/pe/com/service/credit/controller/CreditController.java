package pe.com.service.credit.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import pe.com.service.credit.model.dto.CreditDto;
import pe.com.service.credit.service.impl.CreditServiceImpl;
import pe.com.service.credit.utils.ParamReport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credits")
public class CreditController {
	@Autowired
	CreditServiceImpl creditService;
	
	@GetMapping
	public Flux<CreditDto> read() {
		return this.creditService.read();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<CreditDto> create(@RequestBody Mono<CreditDto> creditDtoMono) {
		return this.creditService.create(creditDtoMono);
	}

	@PutMapping("/{id}")
	public Mono<CreditDto> update(@RequestBody Mono<CreditDto> creditDtoMono, @PathVariable String id) {
		return this.creditService.update(creditDtoMono, id);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable String id) {
		return this.creditService.delete(id);
	}

	@GetMapping("/{id}")
	public Mono<CreditDto> findById(@PathVariable String id) {
		return creditService.findById(id);
	}

	@GetMapping("/report-between")
	public Flux<CreditDto> reportBetween(
			@RequestParam("dateInit") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateInit,
			@RequestParam("dateEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateEnd,
			@RequestParam("productName") String productName) {
		return this.creditService.reportBetween(dateInit, dateEnd, productName);
	}

	@GetMapping("/report-between-date")
	public Flux<CreditDto> reportBetweenInterval(@RequestBody ParamReport paramReport) {
		return this.creditService.reportBetweenInterval(paramReport);
	}
}
