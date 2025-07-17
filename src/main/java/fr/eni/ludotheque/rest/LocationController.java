package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.LocationService;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dto.LocationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public Location createLocation(@RequestBody LocationDTO locationDto) {
        return locationService.ajouterLocation(locationDto);
    }
}