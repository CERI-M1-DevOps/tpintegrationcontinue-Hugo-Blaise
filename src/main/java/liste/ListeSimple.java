package liste;

/**
 * Classe représentant une liste chaînée simple.
 */
public class ListeSimple {
    private long size; // nombre d'éléments dans la liste
    Noeud tete;       // premier élément de la liste

    /**
     * Retourne le nombre d'éléments dans la liste.
     * @return La taille actuelle de la liste.
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un nouvel élément au début de la liste.
     * @param element L'élément à ajouter.
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie le premier élément correspondant à la valeur donnée.
     * @param element La valeur à rechercher dans la liste.
     * @param nouvelleValeur La nouvelle valeur à affecter au premier élément trouvé.
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie tous les éléments correspondant à la valeur donnée.
     * @param element La valeur à rechercher dans la liste.
     * @param nouvelleValeur La nouvelle valeur à affecter aux éléments trouvés.
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la liste.
     * @return Une chaîne représentant les éléments de la liste.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime le premier élément correspondant à la valeur donnée.
     * @param element La valeur de l'élément à supprimer.
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime tous les éléments correspondant à la valeur donnée.
     * @param element La valeur des éléments à supprimer.
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Supprime tous les éléments correspondant à la valeur donnée de manière récursive.
     * @param element La valeur des éléments à supprimer.
     * @param tete Le noeud courant de la liste.
     * @return La tête de la liste après suppression.
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Retourne l'avant-dernier élément de la liste.
     * @return Le noeud avant le dernier, ou null si la liste contient moins de 2 éléments.
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des éléments dans la liste.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Retourne le noeud précédent du noeud donné.
     * @param r Le noeud dont on cherche le prédécesseur.
     * @return Le noeud précédent, null si c'est la tête.
     */
    public Noeud getPrecedent(Noeud r) {
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange deux noeuds de la liste.
     * @param r1 Le premier noeud à échanger.
     * @param r2 Le second noeud à échanger.
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2) {
            return;
        }

        if (r1 != tete && r2 != tete) {
            Noeud precedentR1 = getPrecedent(r1);
            Noeud precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            Noeud precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        } else { 
            Noeud precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }

        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }
}
