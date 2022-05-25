package fr.testapp.airdeve2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;

public class AccountConnectionFragment extends Fragment {

    private View view;
    private Button googleButton;
    private Button facebookButton;
    private Button mailButton;
    private Button conexionButton;
    private MainActivity mainContext;

    public AccountConnectionFragment(MainActivity context) {
        this.mainContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_connection, container, false);

        this.googleButton = view.findViewById(R.id.google);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent otherActivity = new Intent(mainContext, HomeFragment.class);
                //startActivity(otherActivity);
                mainContext.loadFragment(new AccountGoogleConnectionFragment(mainContext));
            }

        });

        this.facebookButton = view.findViewById(R.id.facebook);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent otherActivity = new Intent(mainContext, HomeFragment.class);
                //startActivity(otherActivity);
                mainContext.loadFragment(new AccountFacebookConnectionFragment(mainContext));
            }
        });

        this.mailButton = view.findViewById(R.id.mail);
        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent otherActivity = new Intent(mainContext, HomeFragment.class);
                //startActivity(otherActivity);
                mainContext.loadFragment(new AccountMailConnectionFragment(mainContext));
            }
        });

        this.conexionButton = view.findViewById(R.id.inscription);
        conexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent otherActivity = new Intent(mainContext, HomeFragment.class);
                //startActivity(otherActivity);
                mainContext.loadFragment(new AccountRegistrationFragment(mainContext));
            }
        });

        return view;
    }
}
