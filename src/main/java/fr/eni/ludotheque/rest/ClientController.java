package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.AdresseRepository;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {
    private final ClientRepository clientRepository;
    private final AdresseRepository adresseRepository;

    private final ClientService clientService;
    private final JeuService jeuService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable String id) {
        return clientService.trouverClientParId(id);
    }

    @GetMapping("/search")
    public List<Client> searchClient(@RequestParam String nom) {
        return clientService.trouverClientsParNom(nom);
    }

    @PostMapping
    public Client addClient(@RequestBody ClientDTO clientDTO) {
        return clientService.ajouterClient(clientDTO);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable String id, @RequestBody Client client) {
        client.setId(id);
        client.setAdresse(adresseRepository.save(client.getAdresse()));
        return clientRepository.save(client);
    }

    @PatchMapping("/{id}/adresse")
    public Client updateClientAdresse(@PathVariable String id, @RequestBody Adresse adresse) {
        Client client = clientRepository.findById(id).orElseThrow();
        client.setAdresse(adresseRepository.save(adresse));
        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        clientRepository.deleteById(id);
    }
}
