package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {
	@Query("{ 'nom': { $regex: '^?0', $options: 'i' } }")
	List<Client> findByNomStartsWith(String nom);

	Client findByNoTelephone(String telephone);
}