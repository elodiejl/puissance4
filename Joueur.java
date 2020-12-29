import java.util.Scanner;
/**
 * Classe représentant un joueur (humain ou IA)
 *
 * @author FEQQOUSSI Sarah JOLO Elodie
 * @version 29/12/2020
 */
public class Joueur
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private boolean type;
    private String nom;
    //private int nb_pion; //pour détecter une fin de partie
    private Case jeton;

    /**
     * Constructeur d'objets de classe Joueur
     * 
     * @param type si c'est un humain ou une IA
     * @param nom du joueur
     * @param jeton du joueur
     */
    public Joueur(boolean type,String nom,Case jeton)
    {
        // initialisation des variables d'instance
        this.type=type;
        this.nom=nom;
        this.jeton=jeton;
    }
    
    public Joueur()
    {
        this(true,null,Case.VIDE);
    }
    
    public boolean getType()
    {
        return this.type;
    }

    public String getNom()
    {
        return this.nom;
    }
    
    public Case getJeton()
    {
        return this.jeton;
    }
    
    
}
