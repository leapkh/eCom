package kh.edu.paragoniu.ecom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kh.edu.paragoniu.ecom.R;

public class LoginMethodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_methods);
    }

    public void onLoginWithEmailClick(View view){
        passDataToActivity1("email");
    }

    public void onLoginWithPhoneClick(View view){
        passDataToActivity1("phone");
    }

    public void onLoginWithFacebookClick(View view){
        passDataToActivity1("facebook");
    }

    private void passDataToActivity1(String data){
        Intent intent = new Intent();
        intent.putExtra("LOGIN-METHOD", data);
        setResult(RESULT_OK, intent);
        finish();
    }

}
