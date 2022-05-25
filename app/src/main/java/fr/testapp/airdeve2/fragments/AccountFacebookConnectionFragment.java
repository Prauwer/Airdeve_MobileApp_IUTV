package fr.testapp.airdeve2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.util.Random;

import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.model.UserModel;

public class AccountFacebookConnectionFragment extends Fragment
{

    private View view;
    private Button conexionButton;
    private MainActivity mainContext;

    public AccountFacebookConnectionFragment(MainActivity context)
    {
        this.mainContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_facebook_conection, container, false);

        this.conexionButton = view.findViewById(R.id.jeMeConecte);
        conexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel user = mainContext.getRepo().getUserList().get(new Random().nextInt(4));
                mainContext.loadFragment(new AccountFragmentTwo(mainContext, user));
                mainContext.setMainUser(user);
            }

        });
        return view;
    }
}