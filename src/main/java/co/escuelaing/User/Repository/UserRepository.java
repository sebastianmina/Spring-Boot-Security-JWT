package co.escuelaing.User.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.escuelaing.User.Data.User;

public interface UserRepository extends MongoRepository<User, String> {
    
}
