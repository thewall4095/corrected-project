package com.example.pratiksha.afinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;


public class RegisterActivity2 extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity2.this;

    private NestedScrollView nestedScrollView2;

    private TextInputLayout textInputLayoutName2;
    private TextInputLayout textInputLayoutEmail2;
    private TextInputLayout textInputLayoutPassword2;
    private TextInputLayout textInputLayoutConfirmPassword2;

    private TextInputEditText textInputEditTextName2;
    private TextInputEditText textInputEditTextEmail2;
    private TextInputEditText textInputEditTextPassword2;
    private TextInputEditText textInputEditTextConfirmPassword2;

    private AppCompatButton appCompatButtonRegister2;
    private AppCompatTextView appCompatTextViewLoginLink2;

    private InputValidation2 inputValidation2;
    private DatabaseHelper2 databaseHelper2;
    private Supplier supplier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView2 = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName2 = (TextInputLayout) findViewById(R.id.textInputLayoutName2);
        textInputLayoutEmail2 = (TextInputLayout) findViewById(R.id.textInputLayoutEmail2);
        textInputLayoutPassword2 = (TextInputLayout) findViewById(R.id.textInputLayoutPassword2);
        textInputLayoutConfirmPassword2 = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword2);

        textInputEditTextName2 = (TextInputEditText) findViewById(R.id.textInputEditTextName2);
        textInputEditTextEmail2 = (TextInputEditText) findViewById(R.id.textInputEditTextEmail2);
        textInputEditTextPassword2 = (TextInputEditText) findViewById(R.id.textInputEditTextPassword2);
        textInputEditTextConfirmPassword2 = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword2);

        appCompatButtonRegister2 = (AppCompatButton) findViewById(R.id.appCompatButtonRegister2);

        appCompatTextViewLoginLink2 = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink2);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister2.setOnClickListener(this);

        appCompatTextViewLoginLink2.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation2 = new InputValidation2(activity);
        databaseHelper2 = new DatabaseHelper2(activity);
        supplier = new Supplier();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister2:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink2:
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation2.isInputEditTextFilled(textInputEditTextName2, textInputLayoutName2, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation2.isInputEditTextFilled(textInputEditTextEmail2, textInputLayoutEmail2, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation2.isInputEditTextEmail(textInputEditTextEmail2, textInputLayoutEmail2, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation2.isInputEditTextFilled(textInputEditTextPassword2, textInputLayoutPassword2, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation2.isInputEditTextMatches(textInputEditTextPassword2, textInputEditTextConfirmPassword2,
                textInputLayoutConfirmPassword2, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper2.checkSupplier(textInputEditTextEmail2.getText().toString().trim())) {

            supplier.setName2(textInputEditTextName2.getText().toString().trim());
            supplier.setEmail2(textInputEditTextEmail2.getText().toString().trim());
            supplier.setPassword2(textInputEditTextPassword2.getText().toString().trim());

            databaseHelper2.addSupplier(supplier);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView2, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView2, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName2.setText(null);
        textInputEditTextEmail2.setText(null);
        textInputEditTextPassword2.setText(null);
        textInputEditTextConfirmPassword2.setText(null);
    }
}
