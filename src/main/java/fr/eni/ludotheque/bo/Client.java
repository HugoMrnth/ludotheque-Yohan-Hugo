package fr.eni.ludotheque.bo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "clients")
public class Client {
	@Id
	private String id;
	
	@Field
	private String nom;
	
	@Field
	private String prenom;

	@Field
	private String email;

	@Field
	private String noTelephone;

	@Field
	private Adresse adresse;
}
