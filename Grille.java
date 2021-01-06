/**
 * Classe représentant la grille du puissance 4
 *
 * @author JOLO Elodie, FEQQOUSSI Sarah
 * @version (un numéro de version ou une date)
 */
/**
 * Classe représentant la grille du puissance 4
 *
 * @author JOLO Elodie, FEQQOUSSI Sarah
 * @version (un numéro de version ou une date)
 */
public class Grille
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private final int colonne, ligne;
    private Case[][] grille; //ou ArrayList<Colonne> grille;
    private int nb_pions;

    /**
     * Constructeur d'objets de classe Grille
     */

    public Grille(int colonne, int ligne)
    {
        this.colonne = colonne;
        this.ligne = ligne;
        grille=new Case[colonne][ligne];
        
        //initialiser la grille vide
        for(int i=0;i<colonne;i++){
            for(int j=0;j<ligne;j++){
                grille[i][j]=Case.VIDE;
            }
        }
    }
    
    /**
     * Cette methode nous permet de savoir si une colonne est rempli ou non
     *
     * @param  [][]grille   le paramètre, le tableau de case
     * @param  numcol      le numéro de colonne dont on souhaite savoir si elle est rempli ou pas 
     * @return              true si la colonne est rempli, false sinon
     */
    public boolean verifier_colonne (Case[][] grille, int numcol){
        if (numcol-1 > this.colonne) return true;
         
        for (int i = 0; i<this.ligne; i++){
            if (grille[numcol-1][i] == Case.VIDE)return false; 
        }
        return true;
    }
   
   
   
     /**
     * Cette methode nous permet de savoir si la grille est remplie ou pas 
     *
     * @param  [][]grille   la grille dont on veut savoir si elle est pleine ou non
     * @return              true si la grille est rempli, false sinon
     */
    
    public boolean grille_pleine (Case [][] grille){
       for (int i = 0; i<this.colonne; i++){  // Colonne 
           for (int j = 0; j<this.ligne; j++){// Lignes
               if (grille[i][j] == Case.VIDE) return false;
           }
        }
        return true;
    }
   
    
     /**
     * Cette methode permet au joueur d'ajouter un pion 
     *
     * @param  j            Joueur qui doit jouer 
     * @param  [][]grille   la grille dans laquelle on souhaite jouer
     * @param  colonne      le numéro de colonne dans laquelle le joueur souhaite placer son pion
     * @return              true si le pion a été ajouté ,sinon false
     */
    
    public boolean ajout_pion (Joueur j, Case [][] grille, int colonne){
        if (verifier_colonne(grille , colonne) == true) { // Dans le cas ou la colonne est remplie
            System.out.println("Cette colonne est déja rempli");
            return false;
        } //Dans le cas ou il reste une place de libre dans la colonne
        
        for (int i = 0; i<this.ligne; i++){
            if (grille[colonne-1][i] == Case.VIDE){ 
              grille[colonne-1][i] = j.getJeton();//On place le jeton à la première case libre 
              return true;
            }
        }
          return false;
        
    }
    
    /**
     * Cette methode permet d'avoir la ligne du dernier pion joué
     *
     * @param  j            Joueur qui a joué 
     * @param  [][]grille   la grille dans laquelle on souhaite jouer
     * @param  colonne      le numéro de colonne dans laquelle le joueur souhaite placer son pion
     * @return              -1 si la colonne est vide ,ligne du dernier pion joué dans la colonne
     */
    public int dernier_pion_joue (Joueur j, Case [][] grille, int colonne){
        if (verifier_colonne(grille , colonne) == true) { // Dans le cas ou la colonne est remplie
            System.out.println("Cette colonne est déja rempli");
            return -1;
        } //Dans le cas ou il reste une place de libre dans la colonne
        
        for (int i = 0; i<this.ligne; i++){
            if (grille[colonne-1][i] == Case.VIDE){ 
               return i-1;
            }
        }
          return -1;
        
    }
    
     /**
     * Cette methode nous permet de détecter s'il y'a eu une victoire
     *
     * @param  [][]grille   la grille dans laquelle les joueurs jouent
     * @param  colonne      la colonne choisi par le joueur 
     * @return              un String annoncant le vainqueur/ un match nul 
     */
    public boolean detection_victoire (Case[][]grille, int colonne, Joueur j ){
        // Detection de victoire à l'horizontale
        if (dernier_pion_joue (j,grille,colonne) >-1){
            int ligne = dernier_pion_joue (j,grille,colonne); // Recuperation du numéro de ligne ou se trouve le pion ajouté
            
            if (detection_victoire_horizontale ( grille, colonne, ligne) == true){ 
                System.out.println ("Le joueur "+j.getNom()+" est le vainqueur");
                return true;
            }   
            
            if (detection_victoire_verticale( grille, colonne, ligne) == true){
                System.out.println("Le joueur "+j.getNom()+" est le vainqueur");
                return true;
            }
            
            //Ici il manque la detection de victoire diagonale
            
            if (grille_pleine (grille) == true) {
                System.out.println("La partie se termine en match nul");
                return true;
            }
        }
        System.out.println("Vous pouvez continuer la partie");
        return false;  
    }
    
    
     /**
     * Cette methode nous permet de détecter s'il y'a eu une victoire à l'horizontale
     *
     * @param  [][]grille   la grille dans laquelle les joueurs jouent
     * @param  colonne      la colonne dans laquelle le jeton a été ajouté  
     * @param  ligne        la ligne dans laquelle le jeton a été ajouté 
     * @return              true si il y'a victoire, false sinon
     */
    
    public boolean detection_victoire_horizontale (Case[][]grille, int colonne, int ligne){
        int repetition = 1;
        for (int i = colonne-1;  0<=i && i < this.colonne-1; i++){ //Vers la droite
            if (grille[i][ligne]!=Case.VIDE && grille [i][ligne].equals(grille [i+1][ligne]) ){
                repetition ++;
            }
        }
          
        for (int j = colonne-1;  1<=j && j < this.colonne; j--){ //Vers la gauche
            if (grille [j][ligne].equals(grille [j-1][ligne]) && grille[j][ligne]!=Case.VIDE){
                repetition ++;
            }
        }
          
        if (repetition >= 4) return true;
        return false;
    }
    
     /**
     * Cette methode nous permet de détecter s'il y'a eu une victoire à la verticale
     *
     * @param  [][]grille   la grille dans laquelle les joueurs jouent
     * @param  colonne      la colonne dans laquelle le jeton a été ajouté  
     * @param  ligne        la ligne dans laquelle le jeton a été ajouté 
     * @return              true si il y'a victoire, false sinon
     */
    
    public boolean detection_victoire_verticale (Case[][]grille, int colonne, int ligne){
        int repetition = 1;
        for (int i = ligne;  0<=i && i < this.ligne-1; i++){ //Vers la droite
            if (grille [colonne-1][i].equals(grille [colonne-1][i+1] ) && grille[colonne-1][i]!=Case.VIDE){
                repetition ++;
            }
          }
          
        for (int j = ligne;  1<=j && j < this.ligne; j--){ //Vers la gauche
            if (grille [colonne-1][j].equals(grille [colonne-1][j-1]) && grille[colonne-1][j]!=Case.VIDE){
                repetition ++;
            }
          }
          
        if (repetition >= 4) return true;
        else return false;
    }
    
     /**
     * Cette methode nous permet de détecter s'il y'a eu une victoire à la diagonale
     *
     * @param  [][]grille   la grille dans laquelle les joueurs jouent
     * @param  colonne      la colonne dans laquelle le jeton a été ajouté  
     * @param  ligne        la ligne dans laquelle le jeton a été ajouté 
     * @return              true si il y'a victoire, false sinon
     */
    
    public boolean detection_victoire_diago (Case[][]grille, int colonne, int ligne){
        int repetition = 1;
        for (int i = ligne;  0<i && i < grille[colonne-1].length; i--){ //Vers la droite // compteur ligne
            for (int j = colonne-1; 0<j && j <grille.length; j++){ // compteur colonne
                if (grille [j][i].equals(grille [j+1][i-1]) && grille[j][i]!=Case.VIDE){
                    repetition ++;
                }
            }
        }
          
         for (int i = ligne;  0<i && i < grille[colonne-1].length; i++){ //Vers la gauche // compteur ligne
            for (int j = colonne-1; 0<j &&j <grille.length; j--){ // compteur colonne
            if (grille [j][i].equals(grille [j-1][i+1]) && grille[j][i]!=Case.VIDE){
                repetition ++;
            }
          }
        }
          
        if (repetition >= 4) return true;
        return false;
    }


    /**
     * afficher la grille
     * 
     */
    public void afficher_grille()
    {
        //pour avoir les numéros de colonnes
        for(int nc=1;nc<=this.colonne;nc++){
            System.out.print(nc+" ");
        }
        System.out.println("");
        //pour afficher le contenu de la grille
        for(int i=(this.ligne-1);i>=0;i--){
            System.out.print("|");
            for(int j=0;j<this.colonne;j++){
                System.out.print(grille[j][i]+"|");
            }
            System.out.println("");
        }
    }
    
    /**
     * recupérer la colonne
     * 
     * @return colonne
     */
    public int getColonne(){
        return this.colonne;
    }
    
    /**
     * recupérer la ligne
     * 
     * @return ligne
     */
    public int getLigne(){
        return this.ligne;
    }
    
    /**
     * recupérer la grille
     * 
     * @return grille
     */
    public Case[][] getGrille(){
        return this.grille;
    }
}
