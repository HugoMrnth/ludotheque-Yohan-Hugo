package fr.eni.ludotheque.bo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "factures")
public class Facture {

	@Id
	private String id;

	@Field
	private LocalDateTime datePaiement;

	@Field
	@DocumentReference
	@Builder.Default
	private List<Location> locations = new ArrayList<>();

	@Field
	private Float prix;

	public void addLocation(Location location) {
		this.locations.add(location);
	}
}