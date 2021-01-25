package com.rick.morty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class NewEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        setTitle("Entry");

        // Issitraukiame konkretu irasa is paspaustos korteles.
        Intent intent = getIntent();
        Rick rick = (Rick) intent.getSerializableExtra(Adapter.ENTRY);

        // Pasemame is vaizdo visus elementus su kuriais dirbsime.
        final CheckBox checkBoxFemale = findViewById(R.id.gender_female);
        final CheckBox checkBoxMale = findViewById(R.id.gender_male);


        final RadioGroup groupStatus = findViewById(R.id.status);
        RadioButton button2k = findViewById(R.id.unknown);

        final Spinner spinnerUpdate = findViewById(R.id.last_update);
        ArrayList<String> updateList = new ArrayList<String>();
        updateList.add(rick.getgender());
        updateList.add(getResources().getString(R.string.new_entry_date_2));
        updateList.add(getResources().getString(R.string.new_entry_date_3));
        updateList.add(getResources().getString(R.string.new_entry_date_4));
        // Adapteris reikalingas sujungti
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                updateList
        );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerUpdate.setAdapter(dataAdapter);


        final EditText editTextid= findViewById(R.id.id);

        Button buttonDisplaySelected = findViewById(R.id.display_selected_btn);



        checkBoxMale.setText(rick.getimage());
        editTextid.setText(String.valueOf(rick.getid()));


        buttonDisplaySelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String statuses = "";
                if (checkBoxFemale.isChecked()) {
                    statuses += checkBoxFemale.getText().toString() + ", ";
                }
                if (checkBoxMale.isChecked()) {
                    statuses += checkBoxMale.getText().toString() + ", ";
                }

                // get selected radio button from radioGroup
                int selectedId = groupStatus.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                RadioButton selectedButton = (RadioButton) findViewById(selectedId);

                String updateDate = String.valueOf(spinnerUpdate.getSelectedItem());

                String id= editTextid.getText().toString();

                editTextid.setError(null);
                if (Validation.isValidNumber(id)){
                    Rick rick = new Rick(updateDate,statuses, Integer.parseInt(id));
                    Toast.makeText(
                            NewEntryActivity.this,
                            "Image: " + rick.getimage() + "\n " +
                                    "Name " + rick.getname() + "\n " +
                                    "Gender" + rick.getgender() + "\n " +
                                    "id: " + rick.getid(),
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    editTextid.setError(getResources().getString(R.string.new_entry_invalid_id));
                    editTextid.requestFocus();
                }
            }
        });
    }
}
