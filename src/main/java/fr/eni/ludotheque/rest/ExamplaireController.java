package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ExamplaireService;
import fr.eni.ludotheque.bo.Exemplaire;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/exemplaires")
@RequiredArgsConstructor
public class ExamplaireController {

    private final ExamplaireService examplaireService;


    @GetMapping("/{codeBarre}")
    public Exemplaire findExamplaireByCodeBarre(@PathVariable String codeBarre) {
        return examplaireService.trouverExamplaireParCodeBarre(codeBarre);
    }
}
