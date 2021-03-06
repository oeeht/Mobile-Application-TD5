package com.esilv.theo.matd5.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.esilv.theo.matd5.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView playerView;
    private TextView videoDesc;
    private TextView videoTit;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_player);

        playerView = (YouTubePlayerView)findViewById(R.id.player_view);
        playerView.initialize(YTConnector.KEY, this);
        videoDesc = (TextView)findViewById(R.id.video_description);
        videoDesc.setText(getIntent().getStringExtra("VIDEO_DESC"));
        videoTit = (TextView)findViewById(R.id.video_title);
        videoTit.setText(getIntent().getStringExtra("VIDEO_TIT"));

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult result) {
        Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean restored) {
        if(!restored){
            player.cueVideo(getIntent().getStringExtra("VIDEO_ID"));
        }
    }
}