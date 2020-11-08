package test.uts.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import test.uts.hotel.Model.User;
import test.uts.hotel.database.DatabaseHelper;
import test.uts.hotel.helper.CostumeEditText;
import test.uts.hotel.helper.InputValidation;
import test.uts.hotel.helper.PasswordView;
import test.uts.hotel.helper.SharedPreference;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvRegister;
    MaterialButton btnLogin;
    private CostumeEditText etEmail;
    private PasswordView etPassword;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private RelativeLayout root;

    private SharedPreference sharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();
        checkSession();

    }

    private void checkSession(){
        if(sharedPreference.getLogin(this)){
            moveToHome();
        }
    }


    private void initViews(){
        tvRegister = findViewById(R.id.tvRegister);
        btnLogin = findViewById(R.id.btnSubmit);
        etEmail = (CostumeEditText) findViewById(R.id.etEmail);
        etPassword = (PasswordView) findViewById(R.id.etPassword);
        root = findViewById(R.id.root);
    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
        databaseHelper = new DatabaseHelper(this);
        sharedPreference = new SharedPreference();
    }

    private void initListeners(){
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                verifyFromSQLite();
                break;
            case R.id.tvRegister:
                moveToRegister();
                break;
        }
    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextEmail(etEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (etPassword.getText().toString().length()<6){
            showMessage(getString(R.string.error_message_password));
            return;
        }

        if (databaseHelper.checkUser(etEmail.getText().toString().trim(),etPassword.getText().toString().trim())) {
            saveUser();
            emptyInputEditText();
            moveToHome();

        } else {

            showMessage(getString(R.string.error_valid_email_password));
        }
    }

    private void emptyInputEditText() {
        etEmail.setText(null);
        etPassword.setText(null);
    }

    private void saveUser(){
        User user = new User();
        user.setEmail(etEmail.getText().toString());
        sharedPreference.saveUser(this,user);
        sharedPreference.saveLogin(this,true);
    }

    private void  showMessage(String message){
        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show();
    }
    private void moveToHome(){
        showMessage(sharedPreference.getUser(this).getUserName());
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    private void moveToRegister(){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

}