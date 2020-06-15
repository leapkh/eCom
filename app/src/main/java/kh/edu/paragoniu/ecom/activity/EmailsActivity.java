package kh.edu.paragoniu.ecom.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import kh.edu.paragoniu.ecom.model.Email;
import kh.edu.paragoniu.ecom.adapter.EmailsAdapter;
import kh.edu.paragoniu.ecom.R;

public class EmailsActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_emails);

        // Make a reference to the Views
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);

        // Create and set a layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        loadEmails();
    }

    private void loadEmails() {

        // Show loading
        showLoading(true);

        // Load email from the server using Volley library
        String url = "http://10.0.2.2/paragonu/mails.php";

        // Create a request
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Convert json string to array of Email using Gson
                Gson gson = new Gson();
                Email[] emails = gson.fromJson(response, Email[].class);
                // Create and set an adapter
                EmailsAdapter adapter = new EmailsAdapter(emails);
                recyclerView.setAdapter(adapter);

                // Hide the progress bar and show recycler view
                showLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EmailsActivity.this, "Something error while loading data from the server", Toast.LENGTH_LONG).show();
                Log.d("piuecom", "Load data error: " + error.getMessage());
                // Hide the progress bar and show recycler view
                showLoading(false);
            }
        });

        // Add the request to the Queue
        Volley.newRequestQueue(this).add(request);

    }

    private void showLoading(boolean state){
        if(state){
            recyclerView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

}






