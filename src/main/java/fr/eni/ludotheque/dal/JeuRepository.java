package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Jeu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JeuRepository extends MongoRepository<Jeu, String> {

    @Query("{ 'titre': { $regex: ?0, $options: 'i' } }")
    List<Jeu> findAllJeuxAvecNbExemplaires(@Param("titre") String titre);

    @Query(value = "{ '_id': ?0 }", fields = "{ 'tarifJour': 1 }")
    Float findTarifJour(@Param("jeuId") String jeuId);

    Jeu findByReference(String reference);
}
