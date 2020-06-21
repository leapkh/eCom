package kh.edu.paragoniu.ecom.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kh.edu.paragoniu.ecom.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null){
            String loginMethod = data.getStringExtra("LOGIN-METHOD");
            Toast.makeText(this, loginMethod, Toast.LENGTH_LONG).show();
        }
    }

    public void onLoginClick(View view){

        EditText etxtUsername = findViewById(R.id.etxt_username);
        String username = etxtUsername.getText().toString();

        Intent intent = new Intent(this, BottomNavActivity.class);
        intent.putExtra(BottomNavActivity.EXTRA_USERNAME, username);
        startActivity(intent);

    }

    public void onWebsiteClick(View view){

        Uri data = Uri.parse("https://www.paragoniu.edu.kh");
        Intent intent = new Intent(Intent.ACTION_VIEW, data);
        startActivity(intent);

    }

    public void testIntentFilter(View view){
        Uri data = Uri.parse("https://www.paragoniu.edu.kh/sites/default/files/banner/Reg%202nd%20Intake_PU%20Webbanner.jpg");
        Intent intent = new Intent(Intent.ACTION_VIEW, data);
        startActivity(intent);
    }

    public void onLoginMethodsClick(View view){
        Intent intent = new Intent(this, LoginMethodsActivity.class);
        startActivityForResult(intent, 1);
    }

}
