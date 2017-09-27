package com.example.pratiksha.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

/**
 * Created by pratiksha on 21/9/17.
 */

public class LoginActivity2 extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity2.this;

    private NestedScrollView nestedScrollView2;

    private TextInputLayout textInputLayoutEmail2;
    private TextInputLayout textInputLayoutPassword2;

    private TextInputEditText textInputEditTextEmail2;
    private TextInputEditText textInputEditTextPassword2;

    private AppCompatButton appCompatButtonLogin2;

    private AppCompatTextView textViewLinkRegister2;

    private InputValidation2 inputValidation2;
    private DatabaseHelper2 databaseHelper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView2 = (NestedScrollView) findViewById(R.id.nestedScrollView2);

        textInputLayoutEmail2 = (TextInputLayout) findViewById(R.id.textInputLayoutEmail2);
        textInputLayoutPassword2 = (TextInputLayout) findViewById(R.id.textInputLayoutPassword2);

        textInputEditTextEmail2 = (TextInputEditText) findViewById(R.id.textInputEditTextEmail2);
        textInputEditTextPassword2 = (TextInputEditText) findViewById(R.id.textInputEditTextPassword2);

        appCompatButtonLogin2 = (AppCompatButton) findViewById(R.id.appCompatButtonLogin2);

        textViewLinkRegister2 = (AppCompatTextView) findViewById(R.id.textViewLinkRegister2);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin2.setOnClickListener(this);
        textViewLinkRegister2.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
       databaseHelper2=new DatabaseHelper2(activity);
        inputValidation2=new InputValidation2(activity);



    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin2:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister2:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity2.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation2.isInputEditTextFilled(textInputEditTextEmail2, textInputLayoutEmail2, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation2.isInputEditTextEmail(textInputEditTextEmail2, textInputLayoutEmail2, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation2.isInputEditTextFilled(textInputEditTextPassword2, textInputLayoutPassword2, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper2.checkSupplier(textInputEditTextEmail2.getText().toString().trim()
                , textInputEditTextPassword2.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, UsersListActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail2.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView2, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail2.setText(null);
        textInputEditTextPassword2.setText(null);
    }
}

