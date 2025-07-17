package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExamplaireServiceImpl implements ExamplaireService {
    @NonNull
    private final ExemplaireRepository exemplaireRepository;

    @Override
    public  Exemplaire trouverExamplaireParCodeBarre(String codebarre) {
        return exemplaireRepository.findByCodebarre(codebarre);
    };

}
