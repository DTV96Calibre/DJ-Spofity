package com.example.spg01.spotifytest;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.PlaybackState;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.SpotifyPlayer;

import java.util.Random;

//import spg01.SpotifyTest.R;

public class Playback extends AppCompatActivity implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);
        MainActivity.mPlayer.playUri(null, "spotify:track:0BIRqnH1V9FIFs6zTaXsIY", 0, 0);
        final PlaybackState[] playbackState = {MainActivity.mPlayer.getPlaybackState()};

        final ImageView albumArt = (ImageView) findViewById(R.id.albumArt);
        String artURL = MainActivity.mPlayer.getMetadata().currentTrack.albumCoverWebUrl;
        Ion.with(albumArt)
                .placeholder(R.drawable.ic_music_note)
                .error(R.drawable.ic_music_note) // TODO: Change this to indicate an error
//                        .animateLoad(spinAnimation)
//                        .animateIn(fadeInAnimation)
                .load(artURL);
        final Button playPause = (Button) findViewById(R.id.play_pause);
        if (playbackState[0].isPlaying){
            playPause.setText("Pause");
        } else {
            playPause.setText("Play");
        }
        playPause.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playbackState[0] = MainActivity.mPlayer.getPlaybackState();
                if (playbackState[0].isPlaying){
                    MainActivity.mPlayer.pause(null);
                    playPause.setText("Play");
                } else {
                    MainActivity.mPlayer.resume(null);
                    playPause.setText("Pause");
                }
            }
        });
        final Button skip = (Button) findViewById(R.id.skip);
        skip.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.mPlayer.skipToNext(new Player.OperationCallback() {
                    @Override
                    public void onSuccess() {
                        String artURL = MainActivity.mPlayer.getMetadata().currentTrack.albumCoverWebUrl;
                        Ion.with(albumArt)
                                .placeholder(R.drawable.ic_music_note)
                                .error(R.drawable.ic_music_note) // TODO: Change this to indicate an error
//                        .animateLoad(spinAnimation)
//                        .animateIn(fadeInAnimation)
                                .load(artURL);
                    }

                    @Override
                    public void onError(Error error) {
                        String artURL = MainActivity.mPlayer.getMetadata().currentTrack.albumCoverWebUrl;
                        Ion.with(albumArt)
                                .placeholder(R.drawable.ic_music_note)
                                .error(R.drawable.ic_music_note) // TODO: Change this to indicate an error
//                        .animateLoad(spinAnimation)
//                        .animateIn(fadeInAnimation)
                                .load(artURL);
                    }
                });
            }
        });
        final Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] tracks = {"spotify:track:0BIRqnH1V9FIFs6zTaXsIY","spotify:track:6DkweOE7miAbtP6DwjWreE",
                        "spotify:track:6gAX1KFBsimJOvb3fOikyU", "spotify:track:4FfBIKMCVGzENuwgQUEd3H",
                        "spotify:track:1RSy7B2vfPi84N80QJ6frX", "spotify:track:0a9N94pHEOmE2xLBsygyy7",
                        "spotify:track:6HbTF52swZiGSJ2cvAJ7PU", "spotify:track:76GlO5H5RT6g7y0gev86Nk",
                        "spotify:track:32eLNDWRd4vdORyUS2uGHD"};
                int rnd = new Random().nextInt(tracks.length);
                MainActivity.mPlayer.queue(null, tracks[rnd]);
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
