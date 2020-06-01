package kh.edu.paragoniu.ecom;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EmailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_emails);

        // Make a reference to the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // Create and set a layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Create and set an adapter
        Email[] emails = loadEmails();
        EmailsAdapter adapter = new EmailsAdapter(emails);
        recyclerView.setAdapter(adapter);
    }

    private Email[] loadEmails(){
        // Temporary data
        Email email1 = new Email();
        email1.setSender("Sok Sao");
        email1.setSubject("Hello");
        email1.setBody("How are you?");

        Email email2 = new Email();
        email2.setSender("Abc");
        email2.setSubject("Hi");
        email2.setBody("Bye bye");

        Email email3 = new Email();
        email3.setSender("Xyz");
        email3.setSubject("How do you do?");
        email3.setBody(":)");

        Email email4 = new Email();
        email4.setSender("Xyz2");
        email4.setSubject("How do you do?");
        email4.setBody(":)");

        return new Email[]{email1, email2, email3, email4};
    }

}






