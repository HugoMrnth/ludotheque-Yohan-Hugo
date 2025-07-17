package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Jeu;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
@RequiredArgsConstructor
public class JeuController {

    private final JeuService jeuService;

    @GetMapping
    public List<Jeu> findJeuxWithNbExemplaires(@RequestParam(name = "titre", required = false, defaultValue = "TOUS") String titre) {
        return jeuService.listeJeuxCatalogue(titre);
    }
}
