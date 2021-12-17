package com.faryz.sciencevibeapp

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MyService : Service() {
    var bgMusic: MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        bgMusic = MediaPlayer.create(this, R.raw.background_music)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bgMusic?.start()
        bgMusic?.isLooping = true
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        bgMusic?.stop()
        bgMusic?.release()
    }
}