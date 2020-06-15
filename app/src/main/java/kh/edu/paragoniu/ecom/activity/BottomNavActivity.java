package kh.edu.paragoniu.ecom.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kh.edu.paragoniu.ecom.R;
import kh.edu.paragoniu.ecom.fragment.CategoriesFragment;
import kh.edu.paragoniu.ecom.fragment.ProductsFragment;
import kh.edu.paragoniu.ecom.fragment.ProfileFragment;
import kh.edu.paragoniu.ecom.fragment.SettingsFragment;

public class BottomNavActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bottom_nav);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Bottom Nav Bar
        BottomNavigationView bottomNav = findViewById(R.id.bootom_nav);
        bottomNav.setOnNavigationItemSelectedListener(this);

        // Fragment
        showFragment(new ProductsFragment());
        getSupportActionBar().setTitle(R.string.products);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.mnu_products) {
            showFragment(new ProductsFragment());
            getSupportActionBar().setTitle(R.string.products);
        } else if (item.getItemId() == R.id.mnu_categories) {
            showFragment(new CategoriesFragment());
            getSupportActionBar().setTitle(R.string.categories);
        } else if (item.getItemId() == R.id.mnu_profile) {
            showFragment(new ProfileFragment());
            getSupportActionBar().setTitle(R.string.profile);
        } else {
            showFragment(new SettingsFragment());
            getSupportActionBar().setTitle(R.string.settings);
        }

        return true;
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lyt_fragment_container, fragment);
        fragmentTransaction.commit();
    }

}
