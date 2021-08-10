package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class NodeEditorActivity extends AppCompatActivity {
    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_editor);

        //create edit text widget
        EditText editText = (EditText)findViewById(R.id.editText);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);         //default value is -1 (in case of intent error)
        if(noteID != -1)
        {
            editText.setText(NotesMainActivity.notes.get(noteID));
        }else{
            NotesMainActivity.notes.add("");                // as initially, the note is empty
            noteID = NotesMainActivity.notes.size() - 1;
            NotesMainActivity.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                NotesMainActivity.notes.set(noteID, String.valueOf(s));
                NotesMainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.tanay.thunderbird.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(NotesMainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }
            @Override
            public void afterTextChanged(Editable s){}
        });
    }

}