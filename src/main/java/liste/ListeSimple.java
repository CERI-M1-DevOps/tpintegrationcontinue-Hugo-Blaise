package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne la taille actuelle de la liste.
     * @return le nombre d'éléments dans la liste
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un élément au début de la liste.
     * @param element l'élément à ajouter
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie le premier élément égal à celui fourni.
     * @param element l'élément à chercher
     * @param nouvelleValeur la nouvelle valeur à attribuer
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie tous les éléments égaux à celui fourni.
     * @param element l'élément à chercher
     * @param nouvelleValeur la nouvelle valeur à attribuer
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
     * Retourne la représentation textuelle de la liste.
     * @return chaîne représentant la liste
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
     * Supprime le premier élément égal à celui fourni.
     * @param element l'élément à supprimer
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
     * Supprime tous les éléments égaux à l'élément fourni.
     * @param element l'élément à supprimer
     */
    public void supprimeTous(int element) {
        tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Fonction récursive pour supprimer tous les éléments égaux à l'élément fourni.
     * @param element élément à supprimer
     * @param tete tête du sous-ensemble de la liste
     * @return nouvelle tête après suppression
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
     * Retourne l'avant-dernier noeud de la liste.
     * @return le noeud avant le dernier, ou null si non disponible
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
     * Inverse la liste.
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
     * Retourne le noeud précédent celui fourni.
     * @param r noeud dont on cherche le précédent
     * @return le noeud précédent
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
     * Échange deux noeuds dans la liste.
     * @param r1 premier noeud
     * @param r2 second noeud
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1, precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else if (r2 == tete) {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}
