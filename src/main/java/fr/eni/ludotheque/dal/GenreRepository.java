package fr.eni.ludotheque.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import fr.eni.ludotheque.bo.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}