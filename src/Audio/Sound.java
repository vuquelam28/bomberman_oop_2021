package Audio;

import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    // Đọc các file âm thanh.
    public Sound() {
        soundURL[0] = getClass().getResource("/Sounds/Music.wav");
        soundURL[1] = getClass().getResource("/Sounds/Bomb.wav");
        soundURL[2] = getClass().getResource("/Sounds/BombDrop.wav");
        soundURL[3] = getClass().getResource("/Sounds/destroy.wav");
        soundURL[4] = getClass().getResource("/Sounds/GameOver.wav");
        soundURL[5] = getClass().getResource("/Sounds/item.wav");
        soundURL[6] = getClass().getResource("/Sounds/kill.wav");
        soundURL[7] = getClass().getResource("/Sounds/newlv.wav");
        soundURL[8] = getClass().getResource("/Sounds/Victory.wav");
        soundURL[9] = getClass().getResource("/Sounds/walk.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
