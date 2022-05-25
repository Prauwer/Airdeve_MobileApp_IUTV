package fr.testapp.airdeve2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.testapp.airdeve2.EventRepository;
import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.adapter.EventVerticalAdapter;
import fr.testapp.airdeve2.model.EventModel;
import fr.testapp.airdeve2.model.UserModel;


public class ResearchFragment extends Fragment
{
    private ArrayList<EventModel> eventList;
    private View view;
    private EventRepository EventRepository;
    private MainActivity mainContext;
    private UserModel user;
    private Fragment previousFragment;
    private ImageButton eventBackArrow;


    public ResearchFragment(MainActivity context, ArrayList<EventModel> eventModel, Fragment previousFragment)
    {
        this.mainContext = context;
        this.EventRepository = mainContext.getRepo();
        this.previousFragment = previousFragment;
        eventList = eventModel;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_research, container, false);
        eventBackArrow = view.findViewById(R.id.event_back_arrow);
        RecyclerView recyclerView = view.findViewById(R.id.event_list_recyclerView);
        recyclerView.setAdapter(new EventVerticalAdapter(eventList, super.getContext(), mainContext, this));
        eventBackArrow.getBackground().setAlpha(70);
        eventBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainContext.loadFragment(previousFragment);
            }
        });
        return view;
    }
}
