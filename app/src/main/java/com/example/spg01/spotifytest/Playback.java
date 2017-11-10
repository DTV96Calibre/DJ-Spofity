package com.example.spg01.spotifytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.SpotifyPlayer;

//import spg01.SpotifyTest.R;

public class Playback extends AppCompatActivity implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback {
    public boolean isPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);
        MainActivity.mPlayer.playUri(null, "spotify:track:0BIRqnH1V9FIFs6zTaXsIY", 0, 0);

        Button playPause = (Button) findViewById(R.id.play_pause);
        playPause.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (isPlaying){
                    MainActivity.mPlayer.pause(null);
                } else {
                    MainActivity.mPlayer.resume(null);
                }
                isPlaying = !isPlaying;
            }
        });
    }

    @Override
    public void onLoggedIn() {

    }

    @Override
    public void onLoggedOut() {

    }

    @Override
    public void onLoginFailed(Error error) {

    }

    @Override
    public void onTemporaryError() {

    }

    @Override
    public void onConnectionMessage(String s) {

    }

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {

    }

    @Override
    public void onPlaybackError(Error error) {

    }
}
