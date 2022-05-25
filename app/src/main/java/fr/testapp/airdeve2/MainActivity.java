package fr.testapp.airdeve2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.testapp.airdeve2.fragments.AccountConnectionFragment;
import fr.testapp.airdeve2.fragments.AccountFragment;
import fr.testapp.airdeve2.fragments.AccountFragmentTwo;
import fr.testapp.airdeve2.fragments.EventCreationFragment;
import fr.testapp.airdeve2.fragments.HomeFragment;
import fr.testapp.airdeve2.model.UserModel;

public class MainActivity extends AppCompatActivity
{
    private EventRepository repo = new EventRepository();
    private UserModel mainUser = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment(this));

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation_view);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int id = item.getItemId();

                switch (id)
                {
                    case R.id.home_page:
                    {
                        loadFragment(new HomeFragment(MainActivity.this));
                        return true;
                    }
                    case R.id.event_page:
                    {
                        if(mainUser != null)
                        {
                            loadFragment(new EventCreationFragment(MainActivity.this,mainUser));
                        }
                        else {
                            loadFragment(new AccountConnectionFragment(MainActivity.this));
                        }
                        return true;
                    }
                    case R.id.account_page:
                    {
                        if(mainUser != null)
                        {
                            loadFragment(new AccountFragmentTwo(MainActivity.this, mainUser));
                        }
                        else {
                            loadFragment(new AccountConnectionFragment(MainActivity.this));
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //23 456 (float) -> 23.4K (String)
    public String getStringKFromFloat(float n)
    {
        String s;
        if(n >= 1000)
        {
            s = String.valueOf(n/1000);
            String temp = "";
            for(int i = 0; i < s.indexOf("."); i++)
            {
                temp += s.charAt(i);
            }
            if(s.charAt(s.indexOf(".")+1) != 0)
            {
                s = temp + "." + s.charAt(s.indexOf(".")+1) + "K";
            }
            else {
                s = temp + "K";
            }
        }
        else
        {
            s = String.valueOf((int) n);
        }
        return s;
    }

    public String getFormatDate(Date date, Date date2)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Du " + dateFormat.format(date) + " au " + dateFormat.format(date2);
    }

    public String getStringDate(Date date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }

    public EventRepository getRepo() {
        return repo;
    }

    public void setRepo(EventRepository repo) {
        this.repo = repo;
    }

    public UserModel getMainUser() {
        return mainUser;
    }

    public void setMainUser(UserModel mainUser) {
        this.mainUser = mainUser;
    }
}