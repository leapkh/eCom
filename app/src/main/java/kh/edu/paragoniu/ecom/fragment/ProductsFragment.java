package kh.edu.paragoniu.ecom.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import kh.edu.paragoniu.ecom.R;
import kh.edu.paragoniu.ecom.activity.EmailsActivity;
import kh.edu.paragoniu.ecom.adapter.EmailsAdapter;
import kh.edu.paragoniu.ecom.model.Email;

public class ProductsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_products, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Reference subviews
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
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
                Toast.makeText(getActivity(), "Something error while loading data from the server", Toast.LENGTH_LONG).show();
                Log.d("piuecom", "Load data error: " + error.getMessage());
                // Hide the progress bar and show recycler view
                showLoading(false);
            }
        });

        // Add the request to the Queue
        Volley.newRequestQueue(getActivity()).add(request);

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
