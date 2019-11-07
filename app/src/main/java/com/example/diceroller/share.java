package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import allbegray.slack.SlackClientFactory;
import allbegray.slack.webapi.SlackWebApiClient;
import allbegray.slack.webapi.method.chats.ChatPostMessageMethod;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class share extends AppCompatActivity {

    String Score_Message;
    public String token = "xoxp-817100441509-804307660402-808283958499-1acf37f2e7f6ee46b52c4762fe1a01c8";
    public SlackWebApiClient webApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        Score_Message = intent.getStringExtra(EXTRA_MESSAGE);
        webApiClient = SlackClientFactory.createWebApiClient(token);
    }

    public void on_button_click(View view) {
        ChatPostMessageMethod chatPostMessageMethod = new ChatPostMessageMethod("#general", Score_Message);
        chatPostMessageMethod.setAs_user(true);

        webApiClient.postMessage(chatPostMessageMethod);
    }

}
