package fr.eni.ludotheque.bo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "locations")
public class Location {

	@Id
	private String id;

	@Field
	private LocalDateTime dateDebut;

	@Field
	private LocalDateTime dateRetour;

	@Field
	private Float tarifJour;

	@Field
	@DocumentReference
	private Client client;

	@Field
	@DocumentReference
	private Exemplaire exemplaire;
}