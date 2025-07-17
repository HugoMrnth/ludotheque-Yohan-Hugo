package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Location;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface LocationRepository extends MongoRepository<Location, Integer> {
//    @Query("SELECT l FROM Location l WHERE l.exemplaire.codebarre = :codebarre")
//    Location findLocationByCodebarreWithJeu(@Param("codebarre") String codebarre);

    @Query("{ 'exemplaire.codebarre': ?0 }")
    Location findByCodebarre(String codebarre);
}
