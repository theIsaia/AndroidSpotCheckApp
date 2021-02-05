package com.nedreboe.spotcheckapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class addSpotCheck extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spot_check);
        //populates the spinner with the data stored in the string resource
        Spinner spotCheckSpinner = findViewById(R.id.spotCheckActionSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spot_Check_Action, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spotCheckSpinner.setAdapter(adapter);

        EditText dateField = findViewById(R.id.spotDate);
        //pre fills the current date and time
        dateField.setText(java.time.LocalDate.now().toString() + " " +java.time.LocalTime.now());
    }

    public void insertNewSpotCheck(View view){
        //gets the input fields
        EditText dateField = findViewById(R.id.spotDate);
        EditText locationField = findViewById(R.id.spotLocation);
        EditText regField = findViewById(R.id.spotRegNr);
        EditText modelField = findViewById(R.id.spotModel);
        Spinner resultField = findViewById(R.id.spotCheckActionSpinner);
        EditText notesField = findViewById(R.id.spotNotes);

        //sets the data that gets passed back to mainactivity
        Intent replyIntent = new Intent();
        replyIntent.putExtra("failCheck", "true");
        replyIntent.putExtra("dateFromAddNew", dateField.getText().toString());
        replyIntent.putExtra("locationFromAddNew", locationField.getText().toString());
        replyIntent.putExtra("regFromAddNew", regField.getText().toString());
        replyIntent.putExtra("modelFromAddNew", modelField.getText().toString());
        replyIntent.putExtra("resultFromAddNew", resultField.getSelectedItem().toString());
        replyIntent.putExtra("notesFromAddNew", notesField.getText().toString());
        setResult(RESULT_OK,replyIntent);
        finish();
    }
}
