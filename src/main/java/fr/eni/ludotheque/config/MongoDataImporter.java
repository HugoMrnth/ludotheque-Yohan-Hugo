package fr.eni.ludotheque.config;

import fr.eni.ludotheque.bo.*;
import fr.eni.ludotheque.dal.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MongoDataImporter implements CommandLineRunner {

    private final GenreRepository genreRepository;
    private final AdresseRepository adresseRepository;
    private final ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {

        if (genreRepository.count() > 0) {
            log.info("✅ Données déjà importées");
            return;
        }

        log.info("🚀 Import des données MongoDB...");

        // 1. Import des genres
        Genre genre1 = Genre.builder().id("1").libelle("Jeu de plateau").build();
        Genre genre2 = Genre.builder().id("2").libelle("Jeu de cartes").build();
        Genre genre3 = Genre.builder().id("3").libelle("Jeu de stratégie").build();
        Genre genre4 = Genre.builder().id("4").libelle("Coopératif").build();
        Genre genre5 = Genre.builder().id("5").libelle("Jeu de dé").build();
        Genre genre6 = Genre.builder().id("6").libelle("Jeu d'enquete").build();

        genreRepository.saveAll(List.of(genre1, genre2, genre3, genre4, genre5, genre6));

        // 2. Import des adresses
        Adresse adresse1 = Adresse.builder()
                .id("1")
                .rue("rue des Cormorans")
                .codePostal("79000")
                .ville("Niort")
                .build();

        Adresse adresse2 = Adresse.builder()
                .id("2")
                .rue("rue des marguerites")
                .codePostal("79500")
                .ville("Melle")
                .build();

        adresseRepository.saveAll(List.of(adresse1, adresse2));

        // 3. Import des clients
        Client client1 = Client.builder()
                .id("1")
                .nom("Curie")
                .prenom("Marie")
                .email("marie.curie@example.com")
                .noTelephone("123456789")
                .adresse(adresse1)
                .build();

        Client client2 = Client.builder()
                .id("2")
                .nom("Einstein")
                .prenom("Albert")
                .email("albert.einstein@example.com")
                .noTelephone("0123456789")
                .adresse(adresse2)
                .build();

        clientRepository.saveAll(List.of(client1, client2));

        log.info("✅ Import terminé - {} genres, {} adresses, {} clients",
                genreRepository.count(), adresseRepository.count(), clientRepository.count());
    }
}