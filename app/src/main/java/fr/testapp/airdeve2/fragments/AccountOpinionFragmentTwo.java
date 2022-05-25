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

import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.model.UserModel;

public class AccountOpinionFragmentTwo extends Fragment {

    private View view;
    private Button giveOpinion;
    private Button evenement;
    private MainActivity mainContext;
    private UserModel user;
    private Button compteButon;
    private Button eventButon;
    private TextView nom;
    private ImageView profile;
    private TextView avis;
    private TextView notation;
    private TextView note;

    public AccountOpinionFragmentTwo(MainActivity context, UserModel user)
    {
        this.mainContext = context;
        this.user=user;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_opinion_2, container, false);

        nom = view.findViewById(R.id.nomCompe);
        nom.setText(user.getUsername());
        profile = view.findViewById(R.id.profile);
        Glide.with(mainContext).load(Uri.parse(user.getProfilePictureUrl())).into(profile);
        avis = view.findViewById(R.id.avis);
        avis.setText(mainContext.getStringKFromFloat(user.getNombreAvis()));
        notation = view.findViewById(R.id.notationMot);
        notation.setText(user.getEvaluation());
        note = view.findViewById(R.id.note);
        note.setText(mainContext.getStringKFromFloat(user.getNombreEtoiles()));

        this.eventButon = view.findViewById(R.id.evenement);
        eventButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainContext.loadFragment(new AccountEventListFragmentTwo(mainContext, user));
            }

        });

        this.compteButon = view.findViewById(R.id.compte);
        compteButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainContext.loadFragment(new AccountFragmentTwo(mainContext, user));
            }
        });



        return view;
    }
}