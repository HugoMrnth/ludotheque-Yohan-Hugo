package fr.eni.ludotheque.bo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "exemplaires")
public class Exemplaire {

	@Id
	private String id;

	@Field
	@Indexed(unique = true)
	private String codebarre;

	@Field
	@Builder.Default
	private Boolean louable = true;

	@Field
	@DocumentReference
	private Jeu jeu;
}