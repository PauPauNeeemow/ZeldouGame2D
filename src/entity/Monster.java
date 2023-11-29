package entity;

import main.GameLoop;

public class Monster extends Entity { //monster est une classe fille de entity

    public Monster(GameLoop gp) {
        super(gp);//prend la boucle du jeu en constructeur 

        direction = "down"; //sa direction
        speed = 1;//vitesse du monstre
        
    }
    
}
