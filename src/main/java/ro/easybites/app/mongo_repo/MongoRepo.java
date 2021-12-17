package ro.easybites.app.mongo_repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.easybites.app.model.Codes;
import ro.easybites.app.model.Reteta;
import ro.easybites.app.model.SimpleUser;

import java.util.List;

@Repository
public interface MongoRepo extends MongoRepository<Reteta, String> {

}
