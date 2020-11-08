package test.uts.hotel;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;


import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import test.uts.hotel.Model.User;
import test.uts.hotel.database.DatabaseHelper;
import test.uts.hotel.helper.CostumeEditText;
import test.uts.hotel.helper.InputValidation;
import test.uts.hotel.helper.PasswordView;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = RegisterActivity.this;
    private CostumeEditText etUserName;
    private CostumeEditText etEmail;
    private CostumeEditText etPhoneNumber;
    private MaterialButton btnSubmit;
    private NestedScrollView nestedScrollView;
    private ProgressBar llProgressBar;


    private PasswordView etPassword;
    private PasswordView etConfirmPassword;

    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;

    private Toolbar toolbar;
    private User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initListeners();
        initObjects();
    }
    /**
     * This method is to initialize views
     */
    private void initViews() {
        etUserName = (CostumeEditText) findViewById(R.id.etUserName);
        etEmail = (CostumeEditText) findViewById(R.id.etEmail);
        etPassword = (PasswordView) findViewById(R.id.etPassword);
        etConfirmPassword =(PasswordView) findViewById(R.id.etConfirmPassword) ;
        etPhoneNumber = (CostumeEditText) findViewById(R.id.etPhoneNumber);
        btnSubmit =  (MaterialButton) findViewById(R.id.btnSubmit);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        llProgressBar = findViewById(R.id.llProgressBar);

    }
    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        btnSubmit.setOnClickListener(this);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }
    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                if(validation()){
                    setDelay();
                }
                break;
        }
    }
    /**
     * This method is to validate the input text fields and post data to SQLite
     */

    private void setDelay(){
        llProgressBar.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                llProgressBar.setVisibility(View.GONE);
                postData();
            }
        }, 5000);
    }

    private void postData(){

        if (!databaseHelper.checkUser(etEmail.getText().toString().trim())) {
            user.setUserName(etUserName.getText().toString().trim());
            user.setEmail(etEmail.getText().toString().trim());
            user.setPassword(etPassword.getText().toString().trim());
            user.setPhoneNumber(etPhoneNumber.getText().toString().trim());
            databaseHelper.addUser(user);
            // Snack Bar to show success message that record saved successfully
            showMessage(getString(R.string.registered_success));
            emptyInputEditText();
        } else {
            // Snack Bar to show error message that record already exists
            showMessage(getString(R.string.error_email_exists));
        }
    }
    private boolean validation() {
        Boolean status = true;

        String password = etPassword.getText().toString();
        String username = etUserName.getText().toString();
        String email = etEmail.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();

        if (username.isEmpty()) {
            showMessage(getString(R.string.error_message_name));
            status = false;

        } else if (!isValidEmail(email)) {
            showMessage(getString(R.string.error_message_email));
            status = false;
        }
        else if (phoneNumber.length()<6) {
            showMessage(getString(R.string.error_message_phpne));
            status = false;
        }
        else if (password.length()<6){
            status = false;
            showMessage(getString(R.string.error_message_password));
        }

        else if (!confirmPassword.equals(password)){
            status = false;
            showMessage(getString(R.string.error_password_match));
        }

        return status;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        etUserName.setText(null);
        etEmail.setText(null);
        etPassword.setText(null);
        etPhoneNumber.setText(null);
        etConfirmPassword.setText(null);
    }

    private void  showMessage(String message){
        Snackbar.make(nestedScrollView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}