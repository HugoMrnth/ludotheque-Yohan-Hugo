package fr.eni.ludotheque.config;

import fr.eni.ludotheque.bo.*;
import fr.eni.ludotheque.dal.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MongoDataImporter implements CommandLineRunner {

    private final GenreRepository genreRepository;
    private final AdresseRepository adresseRepository;
    private final ClientRepository clientRepository;
    private final JeuRepository jeuRepository;
    private final ExemplaireRepository exemplaireRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("🗑️ Suppression des collections...");

        // Nettoyage de toutes les collections concernées
        dropCollectionIfExists("exemplaires");
        dropCollectionIfExists("jeux");
        dropCollectionIfExists("clients");
        dropCollectionIfExists("adresses");
        dropCollectionIfExists("genres");

        log.info("🚀 Import des données MongoDB...");

        // 1. Genres
        Genre genre1 = Genre.builder().id("1").libelle("Jeu de plateau").build();
        Genre genre2 = Genre.builder().id("2").libelle("Jeu de cartes").build();
        Genre genre3 = Genre.builder().id("3").libelle("Jeu de stratégie").build();
        Genre genre4 = Genre.builder().id("4").libelle("Coopératif").build();
        Genre genre5 = Genre.builder().id("5").libelle("Jeu de dé").build();
        Genre genre6 = Genre.builder().id("6").libelle("Jeu d'enquete").build();
        genreRepository.saveAll(List.of(genre1, genre2, genre3, genre4, genre5, genre6));

        // 2. Adresses
        Adresse adresse1 = Adresse.builder().id("1").rue("rue des Cormorans").codePostal("79000").ville("Niort").build();
        Adresse adresse2 = Adresse.builder().id("2").rue("rue des marguerites").codePostal("79500").ville("Melle").build();
        adresseRepository.saveAll(List.of(adresse1, adresse2));

        // 3. Clients
        Client client1 = Client.builder().id("1").nom("Curie").prenom("Marie").email("marie.curie@example.com")
                .noTelephone("123456789").adresse(adresse1).build();
        Client client2 = Client.builder().id("2").nom("Einstein").prenom("Albert").email("albert.einstein@example.com")
                .noTelephone("0123456789").adresse(adresse2).build();
        clientRepository.saveAll(List.of(client1, client2));

        // 4. Jeux (entités principales)
        Jeu jeu1 = Jeu.builder()
                .id("1")
                .titre("Pandemic")
                .description("Descr pandemic")
                .reference("refPandemic")
                .duree(30)
                .ageMin(10)
                .tarifJour(12.5f)
                .nbExemplairesDisponibles(2)
                .genres(List.of(genre3, genre1))
                .build();

        Jeu jeu2 = Jeu.builder()
                .id("2")
                .titre("Welcome")
                .description("Descr welcome")
                .reference("refWelcome")
                .duree(30)
                .ageMin(10)
                .tarifJour(9.3f)
                .nbExemplairesDisponibles(2)
                .genres(List.of(genre2, genre3))
                .build();

        jeuRepository.saveAll(List.of(jeu1, jeu2));

        // 5. Exemplaires (avec jeux embarqués)
        Exemplaire exemplaire1 = Exemplaire.builder().id("1").jeu(jeu1).codebarre("1111111111111").louable(true).build();
        Exemplaire exemplaire2 = Exemplaire.builder().id("2").jeu(jeu1).codebarre("2222222222222").louable(false).build();
        Exemplaire exemplaire3 = Exemplaire.builder().id("3").jeu(jeu1).codebarre("3333333333333").louable(true).build();
        Exemplaire exemplaire4 = Exemplaire.builder().id("4").jeu(jeu2).codebarre("4444444444444").louable(true).build();
        Exemplaire exemplaire5 = Exemplaire.builder().id("5").jeu(jeu2).codebarre("5555555555555").louable(false).build();
        Exemplaire exemplaire6 = Exemplaire.builder().id("6").jeu(jeu2).codebarre("6666666666666").louable(true).build();

        exemplaireRepository.saveAll(List.of(
                exemplaire1, exemplaire2, exemplaire3,
                exemplaire4, exemplaire5, exemplaire6
        ));

        log.info("✅ Import terminé - {} genres, {} adresses, {} clients, {} jeux, {} exemplaires",
                genreRepository.count(), adresseRepository.count(), clientRepository.count(),
                jeuRepository.count(), exemplaireRepository.count());
    }

    private void dropCollectionIfExists(String collectionName) {
        try {
            if (mongoTemplate.collectionExists(collectionName)) {
                mongoTemplate.dropCollection(collectionName);
                log.info("✅ Collection {} supprimée", collectionName);
            }
        } catch (Exception e) {
            log.warn("Erreur lors de la suppression de la collection {}: {}", collectionName, e.getMessage());
        }
    }
}
