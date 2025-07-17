package fr.eni.ludotheque.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import fr.eni.ludotheque.exceptions.DataNotFound;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JeuServiceImpl implements JeuService{
	@NonNull
	private JeuRepository jeuRepository;

	@NonNull
	private ExemplaireRepository exemplaireRepository;

	@Override
	public void ajouterJeu(Jeu jeu) {

		jeuRepository.save(jeu);


	}


	@Override
	public Jeu trouverJeuParNoJeu(String id) {
		Optional<Jeu> optJeu = jeuRepository.findById(id);

		if(optJeu.isEmpty()) {
			throw new DataNotFound("Jeu", id);
		}
		return optJeu.get();

	}


//	@Override
//	public List<Jeu> listeJeuxCatalogue(String filtreTitre) {
//		List<Jeu> jeux = jeuRepository.findAllJeuxAvecNbExemplaires(filtreTitre);
//
//		for(Jeu jeu : jeux) {
//			int nbExemplairesDisponibles = exemplaireRepository.nbExemplairesDisponibleByNoJeu(jeu.getId());
//			jeu.setNbExemplairesDisponibles(nbExemplairesDisponibles);
//		}
//
//		return jeux;
//	}

	@Override
	public List<Jeu> listeJeuxCatalogue(String filtreTitre) {
		List<Jeu> jeux;

		if (filtreTitre == null || filtreTitre.trim().isEmpty() || filtreTitre.equalsIgnoreCase("TOUS")) {
			jeux = jeuRepository.findAll(); // retourne tous les jeux
		} else {
			jeux = jeuRepository.findAllJeuxAvecNbExemplaires(filtreTitre);
		}

		for (Jeu jeu : jeux) {
			int nbExemplairesDisponibles = exemplaireRepository.nbExemplairesDisponibleByNoJeu(jeu.getId());
			jeu.setNbExemplairesDisponibles(nbExemplairesDisponibles);
		}

		return jeux;
	}
}
