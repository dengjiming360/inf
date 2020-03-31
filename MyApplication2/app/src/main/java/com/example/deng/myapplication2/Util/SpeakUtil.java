package com.example.deng.myapplication2.Util;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.speech.EventManager;
import com.baidu.speech.asr.SpeechConstant;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpeakUtil {
    public static void start(Context context, EventManager asr){
        Toast.makeText(context, "请开始说话", Toast.LENGTH_SHORT).show();
        Map<String,Object> params = new LinkedHashMap<>();
        String event = null;
        event = SpeechConstant.ASR_START;
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME,false);
        String json = null;
        json = new JSONObject(params).toString();
        asr.send(event, json, null, 0, 0);
    }
    public static void stop(EventManager asr){
        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
    }
}
