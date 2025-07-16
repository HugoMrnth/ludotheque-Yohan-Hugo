package fr.eni.ludotheque.bo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "jeux")
public class Jeu {

	@Id
	private String id;

	@Field
	private String titre;

	@Field
	@Indexed(unique = true)
	private String reference;

	@Field
	private Integer ageMin;

	@Field
	private String description;

	@Field
	private Integer duree;

	@Field
	private Float tarifJour;

	@Field
	private Integer nbExemplairesDisponibles;

	@Field
	@DocumentReference
	@Builder.Default
	private List<Genre> genres = new ArrayList<>();

	public void addGenre(Genre g) {
		genres.add(g);
	}
}