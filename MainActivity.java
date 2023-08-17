package com.example.citationapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextInputEditText editTextAuthor;
    private TextInputEditText editTextTitle;
    private TextInputEditText editDate;
    private TextInputEditText NewspaperName;
    private TextInputEditText issueNum;
    private Spinner spinnerStyle;
    private TextView textViewCitation;
    private Button buttonGenerate;
    private String selectedStyle;
    private String author;
    private String Newspaper;
    private String title;
    private String issuedate;
    private String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.citations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        editTextAuthor = findViewById(R.id.AuthorName);
        editTextTitle = findViewById(R.id.Title);
        editDate = findViewById(R.id.date);
        NewspaperName = findViewById(R.id.newspapers);
        issueNum = findViewById(R.id.IssueNumber);
        textViewCitation = findViewById(R.id.CitationView);
        textViewCitation.setVisibility(View.INVISIBLE);
        spinnerStyle = findViewById(R.id.spinner1);


        Button cite = (Button)findViewById(R.id.cited);
        cite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                author = editTextAuthor.getText().toString();
                title = editTextTitle.getText().toString();
                year = editDate.getText().toString();
                Newspaper = NewspaperName.getText().toString();
                issuedate = issueNum.getText().toString();
                // Access the selected citation style
                String citationStyle = spinnerStyle.getSelectedItem().toString();
                if (citationStyle.equals("MLA")) {
                    editTextAuthor.setVisibility(View.INVISIBLE);
                    editTextTitle.setVisibility(View.INVISIBLE);
                    editDate.setVisibility(View.INVISIBLE);
                    NewspaperName.setVisibility(View.INVISIBLE);
                    issueNum.setVisibility(View.INVISIBLE);
                    textViewCitation.setVisibility(View.VISIBLE);

                    //  (Last name, First initial)
                    String[] authorNames = author.split(" ");
                    StringBuilder formattedAuthor = new StringBuilder();

                    for (int i = 0; i < authorNames.length; i++) {
                        String name = authorNames[i];
                        if (i == authorNames.length - 1) {
                            // Last name
                            formattedAuthor.append(name).append(", ");
                        } else {
                            // First initial(s)
                            formattedAuthor.append(name.charAt(0)).append(". ");
                        }
                    }

                    String cited = formattedAuthor.toString() + "(" + year + "). " + title + ".";
                    System.out.print(author);
                    TextView citationView = findViewById(R.id.CitationView);
                    System.out.println("cited: " + cited);
                    citationView.setText(cited);
                    citationView.setTextSize(24);
                }
                if (citationStyle.equals("APA")) {
                    editTextAuthor.setVisibility(View.INVISIBLE);
                    editTextTitle.setVisibility(View.INVISIBLE);
                    editDate.setVisibility(View.INVISIBLE);
                    NewspaperName.setVisibility(View.INVISIBLE);
                    issueNum.setVisibility(View.INVISIBLE);
                    textViewCitation.setVisibility(View.VISIBLE);

                    // Split the author's name into first name and last name
                    String[] names = author.split(" ");
                    String lastName = names[names.length - 1];
                    StringBuilder firstNameInitials = new StringBuilder();
                    for (int i = 0; i < names.length - 1; i++) {
                        String name = names[i];
                        firstNameInitials.append(name.charAt(0));
                    }

                    String cited = lastName + ", " + firstNameInitials.toString() + ". (" + year + "). " + title + ". " +
                            Newspaper + ", " + issuedate+ ".";
                    TextView citationView = findViewById(R.id.CitationView);
                    citationView.setText(cited);
                    citationView.setTextSize(24);
                }
                if (citationStyle.equals("Chicago")) {
                    editTextAuthor.setVisibility(View.INVISIBLE);
                    editTextTitle.setVisibility(View.INVISIBLE);
                    editDate.setVisibility(View.INVISIBLE);
                    NewspaperName.setVisibility(View.INVISIBLE);
                    issueNum.setVisibility(View.INVISIBLE);
                    textViewCitation.setVisibility(View.VISIBLE);

                    String cited = author + ". " + title + ".";
                    TextView citationView = findViewById(R.id.CitationView);
                    citationView.setText(cited);
                    citationView.setTextSize(24);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}