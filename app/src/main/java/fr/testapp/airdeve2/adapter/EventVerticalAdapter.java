package fr.testapp.airdeve2.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.fragments.EventDescriptionFragment;
import fr.testapp.airdeve2.model.EventModel;
import fr.testapp.airdeve2.model.UserModel;

public class EventVerticalAdapter extends RecyclerView.Adapter<EventVerticalAdapter.ViewHolder>
{
    private LayoutInflater inflater;
    private List<EventModel> data;
    private MainActivity mainContext;
    private Fragment fragment;
    private UserModel user;

    public EventVerticalAdapter(List<EventModel> data, Context context, MainActivity mainContext, Fragment fragment)
    {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.mainContext = mainContext;
        this.fragment = fragment;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = this.inflater.inflate(R.layout.item_vertical_event, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        EventModel event = this.getData().get(position);

        //utilisation de glide pour recuperer les images (temporaire)
        Glide.with(mainContext).load(Uri.parse(event.getImageUrl())).into(holder.monImageView);

        holder.eventName.setText(event.getName());
        holder.PlaceTextView.setText(event.getPlace());
        holder.CreatorTextView.setText("Organisateur");
        holder.DateTextView.setText("22.01.2002");
        holder.monImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainContext.loadFragment(new EventDescriptionFragment(mainContext, event, fragment));
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return this.getData().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView DateTextView;
        private TextView PlaceTextView;
        private TextView CreatorTextView;
        private ImageView monImageView;
        private TextView eventName;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.CreatorTextView = itemView.findViewById(R.id.recycler_event_creator);
            this.DateTextView = itemView.findViewById(R.id.recycler_event_date);
            this.PlaceTextView = itemView.findViewById(R.id.recycler_event_place);
            this.monImageView = itemView.findViewById(R.id.recycler_event_image);
            this.eventName = itemView.findViewById(R.id.recycler_event_name);
        }

    }

    public List<EventModel> getData() {
        return data;
    }

    public void setData(List<EventModel> data) {
        this.data = data;
    }
}
