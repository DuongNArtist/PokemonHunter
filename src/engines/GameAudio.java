package engines;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;
import java.util.Random;

public class GameAudio {

    public static final int MUSIC_DURATION = 60000;

    public static final int NUMBER_OF_MUSIC = 5;
    public static final String KEY_MUSIC = "music_";
    public static final String KEY_SOUND_COIN = "sound_coin";
    public static final String KEY_SOUND_REWIND = "sound_rewind";
    public static final String KEY_SOUND_SHOOT = "sound_shoot";

    private static GameAudio instance;
    private static boolean mute;
    private static int index;

    private HashMap<String, AudioClip> hashMap;

    private GameAudio() {
        hashMap = new HashMap();
        index = 0;
        mute = false;
        for (int index = 0; index < NUMBER_OF_MUSIC; index++) {
            putAudio(KEY_MUSIC + index);
        }
        putAudio(KEY_SOUND_COIN);
        putAudio(KEY_SOUND_REWIND);
        putAudio(KEY_SOUND_SHOOT);
    }

    public static GameAudio getInstance() {
        if (instance == null) {
            instance = new GameAudio();
        }
        return instance;
    }

    public AudioClip getAudio(String name) {
        return hashMap.get(name);
    }

    public void putAudio(String name) {
        AudioClip audioClip = Applet.newAudioClip(GameAudio.class.getResource("/audio/" + name + ".wav"));
        hashMap.put(name, audioClip);
    }

    public void randomMusic() {
        if (!mute) {
            GameAudio.getInstance().getAudio(GameAudio.KEY_MUSIC + index).stop();
            index = new Random().nextInt(NUMBER_OF_MUSIC);
            GameAudio.getInstance().getAudio(GameAudio.KEY_MUSIC + index).play();
        }
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute() {
        if (!mute) {
            GameAudio.getInstance().getAudio(GameAudio.KEY_MUSIC + index).stop();
        } else {
            GameAudio.getInstance().getAudio(GameAudio.KEY_MUSIC + index).play();
        }
        mute = !mute;
    }
}
