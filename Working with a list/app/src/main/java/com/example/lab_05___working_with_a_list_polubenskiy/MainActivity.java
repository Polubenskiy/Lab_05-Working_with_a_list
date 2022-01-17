package com.example.lab_05___working_with_a_list_polubenskiy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    // Polubenskiy Lab05 - Working with a list
    ArrayAdapter<Note> adapter; // note array
    ListView listView;
    Intent intent;
    Note note;
    int sel, pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1); // Create note array

        listView = findViewById(R.id.list_Note); // get list and attach note array to it
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id)
            {
                sel = position;
                Toast.makeText(getApplicationContext(), position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (data != null) //user pressed "save" button
        {
            pos = data.getIntExtra("my-note-index", -1);
            String title = data.getStringExtra("my-note-title");
            String content = data.getStringExtra("my-note-content");

            note = adapter.getItem(pos);
            note.title = title;
            note.content = content;

            adapter.notifyDataSetChanged(); // update list box
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void OnNew(View v)   // when new note button clicked
    {
        note = new Note("New note", "Some content"); // create new note

        adapter.add(note);
        pos = adapter.getPosition(note); // get it's position (array element index)


        intent = new Intent(this, MainActivity2.class);
        intent.putExtra("my-note-index", pos); // share note data with new activity
        intent.putExtra("my-note-title", note.title);
        intent.putExtra("my-note-content", note.content);
        startActivityForResult(intent, 1); // show note editing activity
    }

    public void OnEdit(View v)
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id)
            {
                Note note1 = adapter.getItem(pos);
                intent.putExtra("my-note-index", adapter.getPosition(note)); // share note data with new activity
                intent.putExtra("my-note-title", note.title);
                intent.putExtra("my-note-content", note.content);
                startActivityForResult(intent, 1); // show note editing activity

                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void OnDelete(View v)
    {
        adapter.remove(note);
    }
}