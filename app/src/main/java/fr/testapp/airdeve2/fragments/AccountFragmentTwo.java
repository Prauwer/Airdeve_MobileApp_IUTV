package fr.testapp.airdeve2.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.Random;

import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.model.UserModel;

public class AccountFragmentTwo extends Fragment
{

    private View view;
    private Button evenementButton;
    private Button avisButton;
    private MainActivity mainContext;
    private UserModel user;
    private TextView nom;
    private ImageView profile;
    private TextView description;
    private TextView abonne;
    private TextView abonnement;
    private TextView facebook;
    private TextView twiter;
    private ImageView fond;

    public AccountFragmentTwo(MainActivity context, UserModel user)
    {
        this.mainContext = context;
        this.user=user;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_2, container, false);

        nom = view.findViewById(R.id.nomCompe);
        nom.setText(user.getUsername());
        profile = view.findViewById(R.id.profile);
        Glide.with(mainContext).load(Uri.parse(user.getProfilePictureUrl())).into(profile);
        description = view.findViewById(R.id.description);
        description.setText(user.getDescription());
        abonne = view.findViewById(R.id.abonnée);
        abonne.setText(mainContext.getStringKFromFloat(user.getSubscribers_count()));
        abonnement = view.findViewById(R.id.abonnement);
        abonnement.setText(mainContext.getStringKFromFloat(user.getSubscription_count()));
        facebook = view.findViewById(R.id.nom_facebook);
        facebook.setText(user.getFb_account());
        twiter = view.findViewById(R.id.nom_twiter);
        twiter.setText(user.getTwitter_account());
        fond = view.findViewById(R.id.fond);
        Glide.with(mainContext).load(Uri.parse(user.getFondPictureUrl())).into(fond);

        this.evenementButton = view.findViewById(R.id.evenement);
        evenementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainContext.loadFragment(new AccountEventListFragmentTwo(mainContext, user));
            }

        });

        this.avisButton = view.findViewById(R.id.avis);
        avisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainContext.loadFragment(new AccountOpinionFragmentTwo(mainContext, user));
            }
        });
        return view;
    }
}