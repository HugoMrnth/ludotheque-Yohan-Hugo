package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplaire;

public interface ExamplaireService {
    Exemplaire trouverExamplaireParCodeBarre(String codebarre);
}
