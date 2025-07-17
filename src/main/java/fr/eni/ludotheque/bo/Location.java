package fr.eni.ludotheque.bo;

import com.mongodb.lang.Nullable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "locations")
public class Location {

	@Id
	private String id;

	@Field
	private LocalDateTime dateDebut;

	@Field
	@Nullable
	private LocalDateTime dateRetour;

	@Field
	@Nullable
	private Float tarifJour;

	@Field
	private Client client;

	@Field
	private Exemplaire exemplaire;
}