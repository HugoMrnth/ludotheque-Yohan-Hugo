package fr.eni.ludotheque.bo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="adresses")
public class Adresse {
	@Id
	private String id;
	
	@Field
	private String rue;
	
	@Field
	private String codePostal;
	
	@Field
	private String ville;
}
