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

    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    protected String user_input;
    private ListView emotionList;
    Button addButton,historyButton,countButton;
    protected ArrayList<Emotion> listEmotions = new ArrayList<>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = (Button)findViewById(R.id.addButton);
        historyButton = (Button)findViewById(R.id.historyButton);
        countButton = (Button)findViewById(R.id.countButton);
        bodyText =  (EditText) findViewById(R.id.body);
        //Button saveButton = (Button) findViewById(R.id.save);
        emotionList =  (ListView) findViewById(R.id.emotionsList);

//        addButton.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                setResult(RESULT_OK);
//                String text = bodyText.getText().toString();
//                //saveInFile(text, new Date(System.currentTimeMillis()));
//                finish();
//
//            }
//        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //String[] feelings = loadFromFile();
        String[] feelings = {"Love","Joy","Surprise","Anger","Sadness","Fear"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, feelings);
        emotionList.setAdapter(adapter);
        emotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user_input = emotionList.getItemAtPosition(position).toString();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //setResult(RESULT_OK);
                Emotion newEmotion = new Emotion(user_input,new Date(System.currentTimeMillis()));
                listEmotions.add(newEmotion);
                for (int x = 0;x<listEmotions.size();x++){
                    Emotion object = listEmotions.get(x);
                    Log.d("Ajay-----",object.getName());
                }
//                String text = bodyText.getText().toString();
                //saveInFile(text, new Date(System.currentTimeMillis()));
                //finish();

            }
        });
    }

//    private String[] loadFromFile() {
//        ArrayList<String> feelings = new ArrayList<String>();
//        try {
//            FileInputStream fis = openFileInput(FILENAME);
//            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//            String line = in.readLine();
//            while (line != null) {
//                feelings.add(line);
//                line = in.readLine();
//            }
//
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return feelings.toArray(new String[tweets.size()]);
//    }
    public void onClick(View v){
        if(v.getId() == R.id.historyButton){
            Intent intent = new Intent(MainActivity.this, ViewHistory.class);
            startActivity(intent);

        }else if(v.getId() == R.id.countButton){
            Intent intent = new Intent(MainActivity.this, Count.class);
            startActivity(intent);
        }
//        Intent intent =  new Intent(MainActivity.this,ViewHistory.class);
//        startActivity
//        //Intent.setClass(MainActivity.this.EditFieldClass.class);
    }

    public void HistoryClicked(View view) {
        Intent intent = new Intent(MainActivity.this, ViewHistory.class);
        startActivity(intent);
    }
//    private void saveInFile(String text, Date date) {
//        try {
//            FileOutputStream fos = openFileOutput(FILENAME,
//                    Context.MODE_APPEND);
//            fos.write(new String(date.toString() + " | " + text)
//                    .getBytes());
//            fos.close();
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}