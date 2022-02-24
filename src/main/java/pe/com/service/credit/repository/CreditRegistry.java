package pe.com.service.credit.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.service.credit.model.Credit;

@Repository
public interface CreditRegistry  extends ReactiveMongoRepository<Credit, String>{

}
