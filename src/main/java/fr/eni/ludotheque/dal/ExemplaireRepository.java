package fr.eni.ludotheque.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.eni.ludotheque.bo.Exemplaire;

public interface ExemplaireRepository extends MongoRepository<Exemplaire, String> {

    @Query(value = "{ 'jeu.id': ?0, 'louable': true }", count = true)
    int nbExemplairesDisponibleByNoJeu(String jeuId);

    Exemplaire findByCodebarre(String codebarre);
}