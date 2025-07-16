package fr.eni.ludotheque.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import fr.eni.ludotheque.bo.Adresse;

public interface AdresseRepository extends MongoRepository<Adresse, String> {

}