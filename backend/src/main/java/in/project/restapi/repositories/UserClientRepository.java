package in.project.restapi.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.project.restapi.models.UserClientModel;



@Repository
public interface UserClientRepository extends MongoRepository<UserClientModel, Long> {

    public UserClientModel findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
