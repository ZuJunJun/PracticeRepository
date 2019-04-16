package com.example.jay.packageandroidproject.fourth;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.bean.VideoBean;
import com.example.jay.packageandroidproject.view.XCircleImageView;
import com.example.jay.packageandroidproject.view.XVideoView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mCtx;
    private List<VideoBean> mVideos;
    private LayoutInflater inflater;
    private VideoHolder holder;
    private MediaPlayer mediaPlayer;

    public VideoAdapter(Context mCtx, List<VideoBean> mVideos) {
        this.mCtx = mCtx;
        this.mVideos = mVideos;
        this.inflater = LayoutInflater.from(mCtx);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        holder = new VideoHolder(inflater.inflate(R.layout.item_video_layout, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int i) {
        String mUrl = mVideos.get(i).mUrl;
        if (holder instanceof VideoHolder) {
            ((VideoHolder) holder).mVideoView.setVideoPath(mUrl);
            ((VideoHolder) holder).mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer = mp;
                    ((VideoHolder) holder).mVideoView.start();
                    ObjectAnimator sunRotationAnim = ObjectAnimator.ofFloat(((VideoHolder) holder).mMsuicalPhoto, "rotation", 0f, 360f);
                    sunRotationAnim.setDuration(5000);
                    sunRotationAnim.setRepeatCount(-1);
                    sunRotationAnim.setInterpolator(new LinearInterpolator());
                    sunRotationAnim.start();
                }
            });
            ((VideoHolder) holder).mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Toast.makeText(mCtx, "无法播发此视频", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            ((VideoHolder) holder).mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                    mp.setLooping(true);
                }
            });
            ((VideoHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isPraiseVideo(((VideoHolder) holder).mVideoView);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mVideos == null ? 0 : mVideos.size();
    }

    public void setVideoPause(){
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void setVideoStart(){
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void releaseMediaPlayer(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    public class VideoHolder extends RecyclerView.ViewHolder {

        private XVideoView mVideoView;
        private XCircleImageView mVideoHeader;
        private TextView mLovely;
        private TextView mComment;
        private TextView mShare;
        private TextView mVideoName;
        private TextView mVideoFrom;
        private TextView mVideoMusical;
        private XCircleImageView mMsuicalPhoto;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            mVideoView = itemView.findViewById(R.id.item_video_view);
            mVideoHeader = itemView.findViewById(R.id.video_header);
            mLovely = itemView.findViewById(R.id.lovely);
            mComment = itemView.findViewById(R.id.comment);
            mShare = itemView.findViewById(R.id.share);
            mVideoName = itemView.findViewById(R.id.video_name);
            mVideoFrom = itemView.findViewById(R.id.video_from);
            mVideoMusical = itemView.findViewById(R.id.video_musical);
            mMsuicalPhoto = itemView.findViewById(R.id.musical_photo);
        }
    }

    public void isPraiseVideo(final XVideoView mVideoView) {
        mVideoView.setOnClickNumListener(new XVideoView.OnClickNumListener() {
            @Override
            public void onSingleClickListener() {
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                } else {
                    mVideoView.start();
                }
            }

            @Override
            public void onDoubleClickListener() {
                Toast.makeText(mCtx, "点赞了这个是视频", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
