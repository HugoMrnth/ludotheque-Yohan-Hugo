package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.AdresseRepository;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{
	@NonNull
	private ClientRepository clientRepository;
	@NonNull
	private AdresseRepository adresseRepository;
	
	@Override
	public Client ajouterClient(ClientDTO clientDto)  {

		Client client = new Client();
		Adresse adresse = new Adresse();
		BeanUtils.copyProperties(clientDto, client);
		BeanUtils.copyProperties(clientDto, adresse);
		client.setAdresse(adresse);
		Client newClient = null;
		try {
			newClient = clientRepository.save(client);
		}catch(DataIntegrityViolationException ex) {
			throw new EmailClientAlreadyExistException();
		}

		return newClient;
	}

	@Override
	public List<Client> trouverClientsParNom(String nom) {

		return clientRepository.findByNomStartsWith(nom);
	}

	@Override
	/* S2010 - Modification complète d'un client */
	public Client modifierClient(String id, ClientDTO clientDto) {
		//Client client = clientRepository.findById(noClient).orElseThrow(()->new DataNotFound("Client", noClient));
		Client client = new Client();
		client.setId(id);
		client.setAdresse(new Adresse());
		BeanUtils.copyProperties(clientDto, client);
		BeanUtils.copyProperties(clientDto, client.getAdresse());
		Client clientBD = null;
		try {
			clientBD = clientRepository.save(client);
		} catch (OptimisticLockingFailureException e) {//thrown if entity is assumed to be present but does not exists in db
			//e.printStackTrace();
			throw new DataNotFound("Client", id);
		}

		return clientBD;
	}

	@Override
	public Client trouverClientParId(String id)  {

		Optional<Client> optClient = clientRepository.findById(id);
		if(optClient.isEmpty()) {
			throw new DataNotFound("Client", id);
		}
		return optClient.get();
	}

	@Override
	public Client modifierAdresse(String id, AdresseDTO adresseDto) {
		Client client = clientRepository.findById(id).orElseThrow(()->new DataNotFound("Client", id));

		BeanUtils.copyProperties(adresseDto, client.getAdresse());

		adresseRepository.save(client.getAdresse());

		return client;

	}

}
