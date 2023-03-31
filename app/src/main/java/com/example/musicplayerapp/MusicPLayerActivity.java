package com.example.musicplayerapp;

import static com.example.musicplayerapp.R.id.play_pause;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPLayerActivity extends AppCompatActivity {


    TextView titleTv;



    ImageView pausePlay,nextBtn,previousBtn,musicIcon;

    ArrayList<MusicModel> songsList;

    MusicModel currentSong;

    MediaPlayer mediaPlayer = MyMedia.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        titleTv=findViewById(R.id.song_title);
        pausePlay=findViewById(R.id.play_pause);
        nextBtn=findViewById(R.id.next);
        previousBtn=findViewById(R.id.previous);
        musicIcon=findViewById(R.id.music_icon_big);

        titleTv.setSelected(true);

        songsList = (ArrayList<MusicModel>) getIntent().getSerializableExtra("List") ;

        setResourcesWithMusic();

        }
       void setResourcesWithMusic(){
        currentSong =songsList.get(MyMedia.currentIndex);

        titleTv.setText(currentSong.getTitle());
        pausePlay.setOnClickListener(view -> pausePlay());
        nextBtn.setOnClickListener(view -> playNextSong());
        previousBtn.setOnClickListener(view -> playPreviousSong());
        playMusic();


    }
    private void playMusic()  {
        mediaPlayer.reset();
        try {


            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void playNextSong(){

         if(MyMedia.currentIndex==songsList.size()-1)
             return;
        MyMedia.currentIndex +=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
        MusicPLayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){


                    if(mediaPlayer.isPlaying()){
                        pausePlay.setImageResource(R.drawable.baseline_pause_circle_outline_24);
                    }else {
                        pausePlay.setImageResource(R.drawable.baseline_play_circle_outline_24);
                    }
                }
                new Handler().postDelayed(this,100);
            }
        });


    }
    private void playPreviousSong(){
        if(MyMedia.currentIndex==0)
            return;
        MyMedia.currentIndex -=1;
        mediaPlayer.reset();
        setResourcesWithMusic();

    }
    private void pausePlay(){

        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();

    }

}