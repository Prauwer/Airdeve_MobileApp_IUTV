package fr.testapp.airdeve2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.testapp.airdeve2.EventRepository;
import fr.testapp.airdeve2.model.EventModel;
import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.adapter.EventAdapter;
import fr.testapp.airdeve2.model.UserModel;

public class HomeFragment extends Fragment
{
    private MainActivity mainContext;
    private EventRepository repo;
    private SearchView SearchView;

    public HomeFragment(MainActivity mainContext) {
        this.mainContext = mainContext;
        this.repo = mainContext.getRepo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList<EventModel> eventList = repo.getEventList();

        //ajout des recyclers views
        RecyclerView recyclerView = view.findViewById(R.id.home_horizontal_trends_recyclerView);
        recyclerView.setAdapter(new EventAdapter(eventList, super.getContext(), mainContext, this));

        RecyclerView recyclerView2 = view.findViewById(R.id.home_horizontal_local_recyclerView);
        recyclerView2.setAdapter(new EventAdapter(eventList, super.getContext(), mainContext, this));

        SearchView search = view.findViewById(R.id.search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<EventModel> listEvent = new ArrayList<>();
                for(EventModel event: mainContext.getRepo().getEventList())
                {
                    if(event.getName().contains(query))
                    {
                        listEvent.add(event);
                    }
                }
                mainContext.loadFragment(new ResearchFragment(mainContext, listEvent, HomeFragment.this));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //
                return false;
            }
        });


        return view;
    }


}
