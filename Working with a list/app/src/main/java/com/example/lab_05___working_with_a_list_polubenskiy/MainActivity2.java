package com.example.lab_05___working_with_a_list_polubenskiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    // Polubenskiy Lab05 - Working with a list
    EditText editTextTitle; // text boxes
    EditText editTextContent;

    int pos; // note array index

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextTitle = findViewById(R.id.editTextTitle); // aquire text boxes
        editTextContent = findViewById(R.id.editTextContent);

        Intent intent = getIntent(); // get note data
        pos = intent.getIntExtra("my-note-index", -1);
        editTextTitle.setText(intent.getStringExtra("my-note-title"));
        editTextContent.setText(intent.getStringExtra("my-note-content"));
    }

    public void OnCancel_click(View v)
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void  OnSave_click(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("my-note-index", pos); // prepare updated note data back to first activity
        intent.putExtra("my-note-title", editTextTitle.getText().toString());
        intent.putExtra("my-note-content", editTextContent.getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }
}