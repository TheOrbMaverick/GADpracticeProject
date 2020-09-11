package com.example.android.gadpracticeproject;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {

    EditText editTextFirstName, editTextLastName, editTextEmail, editTextLink;
    Button submit_button;
    CardView successCardview, warningCardview;

    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        editTextFirstName = findViewById(R.id.first_name);
        editTextLastName = findViewById(R.id.last_name);
        editTextEmail = findViewById(R.id.email);
        editTextLink = findViewById(R.id.link);
        submit_button = findViewById(R.id.project_sub);
        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showdialog();

            }
        });
    }

    public void success(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.alert_dialog_success, null);

        successCardview = v.findViewById(R.id.success_cardview);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(v)
                .create();

        alertDialog.show();
    }

    public void failure(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.alert_dialog_failure, null);

        warningCardview = v.findViewById(R.id.warning_cardview);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(v)
                .create();

        alertDialog.show();
    }

    public void showdialog(){

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.alert_dialog, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(v)
                .create();

        Button yesButton = v.findViewById(R.id.yes_btn);
        ImageView closeImage = v.findViewById(R.id.close);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submit();
                alertDialog.dismiss();
            }
        });

        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
                failure();

            }
        });

        alertDialog.show();

    }

    public void submit(){

        String email = editTextEmail.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String link = editTextLink.getText().toString().trim();

        if (firstName.isEmpty()){
            editTextFirstName.setError("This field cannot be empty");
            editTextFirstName.requestFocus();
            failure();
            return;
        }

        if (email.isEmpty()){
            editTextEmail.setError("This field cannot be empty");
            editTextEmail.requestFocus();
            failure();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            failure();
            return;
        }

        if (lastName.isEmpty()){
            editTextLastName.setError("This field cannot be empty");
            editTextLastName.requestFocus();
            failure();
            return;
        }

        if (link.isEmpty()){
            editTextLink.setError("This field cannot be empty");
            editTextLink.requestFocus();
            failure();
            return;
        }


        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .viewform(email, firstName, lastName, link);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                success();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SubmissionActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                failure();

            }
        });
    }
}
