package gestionnote.utils;

import gestionnote.model.Classe;
import gestionnote.model.Cours;
import gestionnote.model.Eleve;
import gestionnote.model.Enseignant;
import gestionnote.model.Enseignement;
import gestionnote.model.Evaluation;
import gestionnote.model.Matiere;
import gestionnote.model.Niveau;
import gestionnote.model.Note;
import gestionnote.model.Periode;
import gestionnote.model.Personne;
import gestionnote.model.Type;
import java.util.Arrays;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author djim
 */
class AnnotatedClassCollection {
    
    public static List<Class<?>> getAnnotatedClasses() {
        Class<?>[] classes = new Class<?>[]{
            Eleve.class,
            Classe.class,
            Enseignant.class,
            Enseignement.class,
            Cours.class,
            Evaluation.class,
            Matiere.class,
            Niveau.class,
            Note.class,
            Periode.class,
            Personne.class,
            Type.class,
        };

        return Arrays.asList(classes);
    }

    private AnnotatedClassCollection() {
        throw new AssertionError("No need to instanciate this class ...");
    }
}
