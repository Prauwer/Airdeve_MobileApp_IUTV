package fr.testapp.airdeve2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import fr.testapp.airdeve2.EventRepository;
import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.adapter.EventVerticalAdapter;
import fr.testapp.airdeve2.model.EventModel;
import fr.testapp.airdeve2.model.UserModel;

public class AccountEventListFragment extends Fragment
{

    private EventRepository EventRepository;
    private View view;
    private Button aceuilleButton;
    private Button avisButton;
    private MainActivity mainContext;
    private UserModel user;
    private TextView nomuser;

    public AccountEventListFragment(MainActivity context, UserModel user)
    {
        this.mainContext = context;
        this.EventRepository = mainContext.getRepo();
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_event_list, container, false);

        ArrayList<EventModel> eventList = EventRepository.getEventList();

        RecyclerView recyclerView = view.findViewById(R.id.event_list_recyclerView);
        recyclerView.setAdapter(new EventVerticalAdapter(eventList, super.getContext(), mainContext, this));
        nomuser = view.findViewById(R.id.nom);
        nomuser.setText(user.getUsername());

        this.aceuilleButton = view.findViewById(R.id.aceuille);
        aceuilleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainContext.loadFragment(new AccountFragment(mainContext, user));
            }

        });

        this.avisButton = view.findViewById(R.id.avis);
        avisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainContext.loadFragment(new AccountOpinionFragment(mainContext, user));
            }
        });
        return view;
    }
}