package fr.testapp.airdeve2.fragments;

import android.accounts.Account;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.model.UserModel;

public class AccountGiveOpinionFragment extends Fragment {

    private View view;
    private Button acceuil;
    private Button evenement;
    private MainActivity mainContext;
    private UserModel user;


    public AccountGiveOpinionFragment(MainActivity context, UserModel user) {
        this.mainContext = context;
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_give_opinion, container, false);
        this.acceuil = view.findViewById(R.id.aceuille);
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent otherActivity = new Intent(mainContext, HomeFragment.class);
                //startActivity(otherActivity);
                mainContext.loadFragment(new AccountFragment(mainContext,user));
            }

        });

        this.evenement = view.findViewById(R.id.evenement);
        evenement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent otherActivity = new Intent(mainContext, HomeFragment.class);
                //startActivity(otherActivity);
                mainContext.loadFragment(new AccountEventListFragment(mainContext, user));
            }

        });

        return view;
    }
}