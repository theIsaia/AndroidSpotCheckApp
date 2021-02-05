package com.nedreboe.spotcheckapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nedreboe.spotcheckapp.model.SpotCheck;
import com.nedreboe.spotcheckapp.model.viewModel.spotCheckViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SpotCheckListAdapter adapter;
    private ArrayList<SpotCheck>spotCheckList = new ArrayList();

    private com.nedreboe.spotcheckapp.model.viewModel.spotCheckViewModel spotCheckViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialises the recyclerView now
        recyclerView = findViewById(R.id.RecyclerSpotCheck2);
        adapter = new SpotCheckListAdapter(this, spotCheckList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        spotCheckViewModel = ViewModelProviders.of(this).get(spotCheckViewModel.class);
        //Gets all existing spot checks and adds them to the arraylist that will be sent to the recyclerview
        spotCheckViewModel.getAllSpotChecks().observe(this, new Observer<List<SpotCheck>>() {
            @Override
            public void onChanged(List<SpotCheck> spotChecks) {
                spotCheckList.clear();
                for (SpotCheck spotCheck: spotChecks){
                    spotCheckList.add(spotCheck);
                    Log.d("sizeOnChanged", spotCheckList.size() + "");
                }
                //tells the recyclerview to check for new data and update the view.
                recyclerView.getAdapter().notifyDataSetChanged();
        }
        });
        //The reason this is here is to make it so the app does not crash when pressing the Export recent button.
        //If this isnt here the app crashes upon pressing "export recent" button.
        spotCheckViewModel.getRecentSpotChecks().observe(this, new Observer<List<SpotCheck>>() {
            @Override
            public void onChanged(List<SpotCheck> spotChecks) {

            }
        });
    }
    //Sends them to the add spot check screen.
    public void addSpotCheckPressed(View view) {
        Intent addSpotCheckScreen= new Intent(this, addSpotCheck.class);
        startActivityForResult(addSpotCheckScreen, 0);
    }

    public void navigateSearchSpotChecks(View view) {
        Intent searchSpotCheckScreen= new Intent(this, spotCheckSearch.class);
        startActivityForResult(searchSpotCheckScreen, 1);
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Checks if the request code matches the addEntry screens assigned result code, and checks if the data is not empty before it adds it to the database
        // this prevents a crash incase the user decides to back out of the add entry screen
        if (requestCode == 0 && data != null){
            String date = data.getStringExtra("dateFromAddNew");
            String location = data.getStringExtra("locationFromAddNew");
            String reg = data.getStringExtra("regFromAddNew");
            String model = data.getStringExtra("modelFromAddNew");
            String result = data.getStringExtra("resultFromAddNew");
            String notes= data.getStringExtra("notesFromAddNew");
            SpotCheck newSpotCheck = new SpotCheck(date, location, model, reg, result, notes, false);
            spotCheckViewModel.insert(newSpotCheck);
        }
    }

    public void exportEmail(View view) {
        String emailString = "";
        if (view.getId() == R.id.emailExportButton) {
            List<SpotCheck> newSpotChecks = spotCheckViewModel.getAllSpotChecks().getValue();
            emailString = exportToEmail(newSpotChecks);
        }else if (view.getId() == R.id.emailRecentExportButton){
            List<SpotCheck> newSpotChecks = spotCheckViewModel.getRecentSpotChecks().getValue();
            emailString = exportToEmail(newSpotChecks);
            }
        String subject = "Phone catalogue export";
        Uri mailUri = Uri.parse("mailto:Fred@gmail.com");
        String body = emailString;
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO,mailUri);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
        else{
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    public String exportToEmail(List<SpotCheck> newSpotChecks){
        String emailBody = "";
        for (SpotCheck spotCheck : newSpotChecks) {
            emailBody +=
                    spotCheck.getDate() + "\n" +
                    spotCheck.getCarRegNr() + "\n" +
                    spotCheck.getCarModel() + "\n" +
                    spotCheck.getLocation() + "\n" +
                    spotCheck.getSpotCheckResult() + "\n" +
                    spotCheck.getNotes() + "\n\n";
            spotCheck.setHasExported(true);
            spotCheckViewModel.update(spotCheck);
        }
        return emailBody;
    }
}
