package com.example.ajaypart_feelsbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ajaypart_feelsbook.Count;
import com.example.ajaypart_feelsbook.R;
import com.example.ajaypart_feelsbook.ViewHistory;

public class MainActivity extends Activity {
    //Initialize the Buttons,comment text field and emotionList that stores emotions
    private EditText commentText;
    protected String user_input;
    private ListView emotionList;
    Button addButton,historyButton,countButton;
    protected ArrayList<Emotion> listEmotions = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set values of the buttons,commentText,emotionList to the appropriate views
        addButton = (Button)findViewById(R.id.addButton);
        historyButton = (Button)findViewById(R.id.historyButton);
        countButton = (Button)findViewById(R.id.countButton);
        commentText =  (EditText) findViewById(R.id.comment);
        emotionList =  (ListView) findViewById(R.id.emotionsList);


    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //List of emotions to choose from
        String[] feelings = {"Love","Joy","Surprise","Anger","Sadness","Fear"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, feelings);
        emotionList.setAdapter(adapter);
        emotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user_input = emotionList.getItemAtPosition(position).toString();
            }
        });
        //I adapted this block of code from LonelyTwitter
        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Emotion newEmotion = new Emotion(user_input,new Date(System.currentTimeMillis()));
                String comment = commentText.getText().toString();
                newEmotion.setComment(comment);
                listEmotions.add(newEmotion);


            }
        });
    }

    public void CountClicked(View view) {
        Intent intent = new Intent(MainActivity.this, ViewHistory.class);
        startActivity(intent);
    }
    public void HistoryClicked(View view) {
        Intent intent = new Intent(MainActivity.this, Count.class);
        startActivity(intent);
    }

}