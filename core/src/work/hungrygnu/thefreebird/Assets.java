package work.hungrygnu.thefreebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by hungry on 21.02.16.
 */
public class Assets {
    public static Music music = Gdx.audio.newMusic(Gdx.files.internal("data/music/music.mp3"));
    public static Sound soundBomb = Gdx.audio.newSound(Gdx.files.internal("data/sound/bomb.ogg"));
    public static Sound soundDeath = Gdx.audio.newSound(Gdx.files.internal("data/sound/death.ogg"));
    public static Sound soundHit = Gdx.audio.newSound(Gdx.files.internal("data/sound/hit.ogg"));
    public static Sound soundKarr = Gdx.audio.newSound(Gdx.files.internal("data/sound/karr.ogg"));
    public static Sound soundMeow = Gdx.audio.newSound(Gdx.files.internal("data/sound/meow.ogg"));



}
