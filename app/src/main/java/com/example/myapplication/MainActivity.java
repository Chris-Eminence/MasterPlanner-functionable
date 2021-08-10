package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    LinearLayout timeTableBTN, todoBTN, notePadBTN, calenderBTN;
    // View from activity
    TextView keyword, welcomeVoice;
    Button mVoiceBtn;
    Button mSpeakBtn;
    Button copyButton;
    EditText mEditTextV;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTableBTN = findViewById(R.id.time_table_layout);
        todoBTN = findViewById(R.id.todo_layout);
        notePadBTN = findViewById(R.id.notes_card_layout);
        calenderBTN = findViewById(R.id.calender_layout);
        mVoiceBtn = findViewById(R.id.speakBtn);
        welcomeVoice = findViewById(R.id.welcomeNote);
//        mSpeakBtn = findViewById(R.id.speakBtn);
        keyword = findViewById(R.id.keyWord);
//        mVoiceBtn = findViewById(R.id.voiceBtn);
//        copyButton = findViewById(R.id.copyBtn);

        timeTableBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, juliushenke.smarttt.TimeTableMainActivity.class);
                startActivity(intent);
            }
        });

        todoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TodoMainActivity.class);
                startActivity(intent);
            }
        });

        notePadBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotesMainActivity.class);
                startActivity(intent);
            }
        });

        calenderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
//                Calendar beginTime = Calendar.getInstance();
//                Calendar endTime = Calendar.getInstance();
//                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
//                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                startActivity(calendarIntent);
            }
        });


                t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status == TextToSpeech.SUCCESS) {
                            t1.setLanguage(Locale.UK);

                        }
                    }
                });

//                mSpeakBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String toSpeak = mEditTextV.getText().toString();
//                        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
//                        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
//                    }
//                });

//                copyButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                        ClipData clip = ClipData.newPlainText("Voice text", keyword.getText().toString());
//                        clipboard.setPrimaryClip(clip);
//
//                        Toast.makeText(MainActivity.this, "Text Copied", Toast.LENGTH_SHORT).show();
//                    }
//                });

                // Button click on show speech to text dialog
                mVoiceBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        speak();
                    }
                });
            }

            private void speak() {
                // Intent to show speech to text dialogs
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL ,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Listening...");

                // Start Intent
                try{
                    // If there was no error
                    // showing dialogs
                    startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e){
                    // If there was some error

                    // get Message of error and show
                    Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            // Receive voice input and handle it

            @Override
            protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
                    if (requestCode != RESULT_OK && null != data) {
                        // get the text array from voice intent
                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                        // set the voice view
                        keyword.setText(result.get(0));
                        String keywordTest = keyword.getText().toString().trim();

                        switch (keywordTest) {
                            case "open timetable":
                            case "open time table":
                            case "open Time table":
                            case "open Time Table":
                            case "open timeTable":
                            case "open TimeTable":
                            case "open Timetable":

                            case "timetable":
                            case "time table":
                            case "Time table":
                            case "Time Table":
                            case "timeTable":
                            case "TimeTable":
                            case "Timetable":
                                {
                                Intent intent = new Intent(MainActivity.this, juliushenke.smarttt.TimeTableMainActivity.class);
                                startActivity(intent);
                                break;
                            }
                            case "open Todo":
                            case "open todo":
                            case "open to do":
                            case "open to Do":
                            case "open To Do":
                            case "open To do":

                            case "Todo":
                            case "todo":
                            case "to do":
                            case "to Do":
                            case "To Do":
                            case "To do":
                                {
                                Intent intent = new Intent(MainActivity.this, TodoMainActivity.class);
                                startActivity(intent);
                                break;
                            }

                            case "open Notepad":
                            case "open Note pad":
                            case "open note pad":
                            case "open Note Pad":
                            case "open notepad":
                            case "open note Pad":

                            case "Notepad":
                            case "Note pad":
                            case "note pad":
                            case "Note Pad":
                            case "notepad":
                            case "note Pad":
                                {
                                Intent intent = new Intent(MainActivity.this, NotesMainActivity.class);
                                startActivity(intent);
                                break;
                            }
                            case "open calendar": {
                                // Event is on January 23, 2021 -- from 7:30 AM to 10:30 AM.
                                Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
//                                Calendar beginTime = Calendar.getInstance();
//                                Calendar endTime = Calendar.getInstance();
//                                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
//                                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                                startActivity(calendarIntent);

                            }
                        }
                    }
                }

            }
            public void onPause () {
                        if (t1 != null) {
                            t1.stop();
                            t1.shutdown();
                        }
                        super.onPause();
                    }
                }
