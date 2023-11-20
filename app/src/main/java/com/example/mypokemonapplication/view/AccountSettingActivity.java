package com.example.mypokemonapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.model.LoginUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class AccountSettingActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etBirthDate;

    private RadioButton rbMale;
    private RadioButton rbFemale;
    private RadioGroup rgGender;

    private Button btnCancel;
    private Button btnSave;
    private DatabaseReference db;
    private String currentUserUid;

    private final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etBirthDate = findViewById(R.id.et_birthdate);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        rgGender = findViewById(R.id.rg_gender);

        currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance("https://my-pokemon-application-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
        retrievingData();
        etDatePicker();
        btnSaveAndCancel();
    }

    private void btnSaveAndCancel(){
        btnCancel = findViewById(R.id.btn_cancel);
        btnSave = findViewById(R.id.btn_save);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedGender = rgGender.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedGender);
                Map<String, Object> childUpdates = new HashMap<>();
                if (selectedRadioButton != null){
                    childUpdates.put("gender", selectedRadioButton.getText().toString());
                }
                if (etBirthDate.getText() != null){
                    childUpdates.put("dateOfBirth", etBirthDate.getText());
                } else {
                    etBirthDate.requestFocus();
                    Toast.makeText(AccountSettingActivity.this, "Date of birth can's be empty.", Toast.LENGTH_SHORT).show();
                }
                db.child("users").child(currentUserUid).updateChildren(childUpdates);
            }
        });
    }

    private void retrievingData(){
        db.child("users").child(currentUserUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                LoginUser user = snapshot.getValue(LoginUser.class);
                etName.setText(user.getName());
                etEmail.setText(user.getUsername());
                if (user.getGender() != null) {
                    switch (user.getGender()) {
                        case "male":
                            rbMale.setChecked(true);
                            rbFemale.setChecked(false);
                        case "female":
                            rbMale.setChecked(false);
                            rbFemale.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AccountSettingActivity.this, "fail.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void etDatePicker(){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar .set(Calendar.YEAR, year);
                myCalendar .set(Calendar.MONTH, month);
                myCalendar .set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        etBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AccountSettingActivity.this, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.ENGLISH);
        etBirthDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void savingData(){
    }
}