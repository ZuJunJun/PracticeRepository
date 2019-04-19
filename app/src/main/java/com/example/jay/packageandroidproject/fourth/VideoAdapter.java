package com.example.jay.packageandroidproject.fourth;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.bean.VideoBean;
import com.example.jay.packageandroidproject.util.UICommonUtil;
import com.example.jay.packageandroidproject.view.AnimCircleImageView;
import com.example.jay.packageandroidproject.view.CircleAddView;
import com.example.jay.packageandroidproject.view.CommentPopupWindow;
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
        final String mUrl = mVideos.get(i).mUrl;
        if (holder instanceof VideoHolder) {
            ((VideoHolder) holder).mVideoView.setVideoPath(mUrl);
            ((VideoHolder) holder).mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer = mp;
                    ((VideoHolder) holder).mVideoView.start();
                    ((VideoHolder) holder).mMusicalPhoto.setAnimStart();
                }
            });
            ((VideoHolder) holder).mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Toast.makeText(mCtx, "无法播发此视频", Toast.LENGTH_SHORT).show();
                    return true;
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
                    isPraiseVideo((VideoHolder) holder);
                }
            });
            ((VideoHolder) holder).mLovely.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable drawable = ContextCompat.getDrawable(mCtx, R.mipmap.lovely_l);
                    ((VideoHolder) holder).mLovely.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                }
            });
            ((VideoHolder) holder).mComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommentPopupWindow commentPopupWindow = new CommentPopupWindow(mCtx);
                    commentPopupWindow.showAtLocation(((VideoHolder) holder).mVideoView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, UICommonUtil.dp2px(mCtx, 50));
                }
            });
            ((VideoHolder) holder).mShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareText("分享到", "", mUrl);
                }
            });
            ((VideoHolder) holder).mFocusCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((VideoHolder) holder).mFocusCv.setVisibility(View.GONE);
                    ((VideoHolder) holder).mFocusedTv.setVisibility(View.VISIBLE);
                }
            });
            ((VideoHolder) holder).mFocusedTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((VideoHolder) holder).mFocusCv.setVisibility(View.VISIBLE);
                    ((VideoHolder) holder).mFocusedTv.setVisibility(View.GONE);
                }
            });
            ((VideoHolder) holder).mVideoView.setOnDeatachListener(new XVideoView.OnDeatachListener() {
                @Override
                public void onDetachListener() {
                    resetItemView(((VideoHolder) holder));
                }
            });
        }
    }

    private void resetItemView(VideoHolder mHolder) {
        mHolder.mMusicalPhoto.setAnimPause();
        if (mHolder.pauseIv.getVisibility() == View.VISIBLE) {
            mHolder.pauseIv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mVideos == null ? 0 : mVideos.size();
    }

    public void setVideoPause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void setVideoStart() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    private void shareText(String dlgTitle, String subject, String content) {
        if (content == null || "".equals(content)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (subject != null && !"".equals(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);

        // 设置弹出框标题
        if (dlgTitle != null && !"".equals(dlgTitle)) {
            // 自定义标题
            mCtx.startActivity(Intent.createChooser(intent, dlgTitle));
        } else {
            // 系统默认标题
            mCtx.startActivity(intent);
        }
    }

    public class VideoHolder extends RecyclerView.ViewHolder {

        private ImageView pauseIv;
        private CircleAddView mFocusCv;
        private TextView mFocusedTv;
        private XVideoView mVideoView;
        private XCircleImageView mVideoHeader;
        private TextView mLovely;
        private TextView mComment;
        private TextView mShare;
        private TextView mVideoName;
        private TextView mVideoFrom;
        private TextView mVideoMusical;
        private AnimCircleImageView mMusicalPhoto;

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
            mMusicalPhoto = itemView.findViewById(R.id.musical_photo);
            mFocusCv = itemView.findViewById(R.id.focus);
            mFocusedTv = itemView.findViewById(R.id.focused);
            pauseIv = itemView.findViewById(R.id.pause);
        }
    }

    public void isPraiseVideo(final VideoHolder mHolder) {

        mHolder.mVideoView.setOnClickNumListener(mCtx,new XVideoView.OnClickNumListener() {
            @Override
            public void onSingleClickListener() {
                if (mHolder.mVideoView.isPlaying()) {
                    mHolder.mVideoView.pause();
                    mHolder.pauseIv.setVisibility(View.VISIBLE);
                    mHolder.mMusicalPhoto.setAnimPause();
                } else {
                    mHolder.mVideoView.start();
                    mHolder.pauseIv.setVisibility(View.GONE);
                    mHolder.mMusicalPhoto.setAnimResume();
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDoubleClickListener() {
                Drawable drawable = ContextCompat.getDrawable(mCtx, R.mipmap.lovely_l);
                mHolder.mLovely.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            }
        });
    }
}
