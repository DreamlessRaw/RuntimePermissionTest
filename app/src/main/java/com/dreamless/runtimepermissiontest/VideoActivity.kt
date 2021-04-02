package com.dreamless.runtimepermissiontest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.activity_player.btn_pause
import kotlinx.android.synthetic.main.activity_player.btn_start
import kotlinx.android.synthetic.main.activity_player.btn_stop
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        video_view.setVideoURI(uri)
        btn_start.setOnClickListener {
            if (!video_view.isPlaying) {
                video_view.start()
            }
        }

        btn_pause.setOnClickListener {
            if (video_view.isPlaying) {
                video_view.pause()
                btn_pause.text = "继续"
            } else {
                video_view.start()
                btn_pause.text = "暂停"
            }
        }

        btn_stop.setOnClickListener {
            if (video_view.isPlaying) {
                video_view.suspend()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        video_view.suspend()
    }
}