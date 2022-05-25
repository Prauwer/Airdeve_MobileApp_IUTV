package fr.testapp.airdeve2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fr.testapp.airdeve2.EventRepository;
import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.adapter.EventAdapter;
import fr.testapp.airdeve2.adapter.EventVerticalAdapter;
import fr.testapp.airdeve2.model.EventModel;
import fr.testapp.airdeve2.model.UserModel;

public class AccountEventListFragmentTwo extends Fragment
{

    private View view;
    private EventRepository EventRepository;
    private Button aceuilleButton;
    private Button avisButton;
    private MainActivity mainContext;
    private UserModel user;

    private TextView nomuser;
    private ImageView photoUser;

    public AccountEventListFragmentTwo(MainActivity context, UserModel user)
    {
        this.mainContext = context;
        this.EventRepository = mainContext.getRepo();
        this.user = user;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_event_list_2, container, false);



        ArrayList<EventModel> eventList = EventRepository.getEventList();

        RecyclerView recyclerView = view.findViewById(R.id.event_list_recyclerView);
        recyclerView.setAdapter(new EventVerticalAdapter(eventList, super.getContext(), mainContext, this));

       nomuser = view.findViewById(R.id.nom);
       nomuser.setText(user.getUsername());
       photoUser = view.findViewById(R.id.photouser);
       Glide.with(mainContext).load(Uri.parse(user.getProfilePictureUrl())).into(photoUser);
//       this.aceuilleButton = view.findViewById(R.id.aceuille);
//       aceuilleButton.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent otherActivity = new Intent(mainContext, HomeFragment.class);
//               startActivity(otherActivity);
//                mainContext.loadFragment(new AccountFragmentTwo(mainContext, user));
//           }
//
//       });
//
//          this.avisButton = view.findViewById(R.id.avis);
//          avisButton.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View v) {
//        Intent otherActivity = new Intent(mainContext, HomeFragment.class);
//        startActivity(otherActivity);
//        mainContext.loadFragment(new AccountOpinionFragmentTwo(mainContext, user));
//        }
//        });
        return view;
    }
}