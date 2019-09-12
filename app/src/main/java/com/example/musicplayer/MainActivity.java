package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaplayer;
    AudioManager audiomanager;
    public void play(View view)
    {
        mediaplayer.start();
    }
    public void pause(View view)
    {
        mediaplayer.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audiomanager=(AudioManager) getSystemService(AUDIO_SERVICE);
        int currentVolume= audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume= audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mediaplayer= MediaPlayer.create(this,R.raw.muscle);
        final SeekBar volumecontrol=(SeekBar) findViewById(R.id.songSeekBar);
        volumecontrol.setMax(maxVolume);
        volumecontrol.setProgress(currentVolume);
        volumecontrol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }

        );

        final SeekBar songSeek= (SeekBar) findViewById(R.id.songSeekBar);
        songSeek.setMax(mediaplayer.getDuration());
        songSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaplayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer(). scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                songSeek.setProgress(mediaplayer.getCurrentPosition());
            }
        },0,1000);
    }
}
