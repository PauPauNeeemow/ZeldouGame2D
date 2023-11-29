package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() { // initialise les urls des fichiers sonores 

        soundURL[0] = getClass().getResource("/res/sound/MusicTheme.wav");
        soundURL[1] = getClass().getResource("/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/res/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/res/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/res/sound/MusicTheme2.wav");
        soundURL[6] = getClass().getResource("/res/sound/hitmonster.wav");
        soundURL[7] = getClass().getResource("/res/sound/attack.wav");
        soundURL[8] = getClass().getResource("/res/sound/death.wav");
        soundURL[9] = getClass().getResource("/res/sound/ArrowSE.wav");
        soundURL[10] = getClass().getResource("/res/sound/HeroHit.wav");
        // soundURL[11] = getClass().getResource("/res/sound/HeroHit.wav"); Game over sound

    }

    public void setFile(int i) { // charge le fichier sonore correspondant à l'indice i 

        try{

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play() { //lance la lecture du son 

        clip.start();
    }

    public void loop() { // démarre la lecture en boucle 

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() { // stop le son 

        if (clip != null) { 
            clip.stop();
        } else {
            System.out.println("Le clip est null, impossible de l'arrêter.");
        }
    }
}
