package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.*;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.FactureRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import fr.eni.ludotheque.dal.LocationRepository;
import fr.eni.ludotheque.dto.LocationDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
	@NonNull
	final private LocationRepository locationRepository;

	@NonNull
	final private JeuRepository jeuRepository;

	@NonNull
	final private ExemplaireRepository exemplaireRepository;

	@NonNull
	final private FactureRepository factureRepository;


	@Override
	public Location ajouterLocation(LocationDTO locationDto  ) {
		Exemplaire exemplaire = exemplaireRepository.findByCodebarre(locationDto.getCodebarre());

		Client client = new Client();
		client.setId(locationDto.getClientId());

//		Location location = new Location(LocalDateTime.now(), client, exemplaire);
        Location location = Location.builder()
				.dateDebut(LocalDateTime.now())
				.client(client)
				.exemplaire(exemplaire)
				.build();

//		Float tarifJour = jeuRepository.findTarifJour(exemplaire.getJeu().getId());
		location.setTarifJour(jeuRepository.findById(exemplaire.getJeu().getId()).orElseThrow().getTarifJour());
		Location newLoc = locationRepository.save(location);

		return newLoc;
	}

	@Override
	public Facture retourExemplaires(List<String> codebarres) {
		Facture facture = new Facture();
		//facture
		Location location = null;
		float prix = 0;
		for(String codebarre : codebarres) {
			location = locationRepository.findByCodebarre(codebarre);
			location.setDateRetour(LocalDateTime.now());
			facture.addLocation(location);
			//TODO : save date retour
			long nbJours = ChronoUnit.DAYS.between(location.getDateDebut(), location.getDateRetour()) +1;
			prix += (nbJours * location.getTarifJour());
		}
		facture.setPrix(prix);
        return factureRepository.save(facture);
	}

	public Facture payerFacture( String id){
		Facture facture = factureRepository.findById(id).orElse(null);
		facture.setDatePaiement(LocalDateTime.now());
		Facture factureBD = factureRepository.save(facture);
		return factureBD;
	}

	public Location trouverParCodebarre(String codebarre) {
        return locationRepository.findByCodebarre(codebarre);
    }

}
