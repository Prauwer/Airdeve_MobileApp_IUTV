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

import java.util.Date;
import java.util.List;

import fr.testapp.airdeve2.model.EventModel;
import fr.testapp.airdeve2.fragments.AccountEventListFragment;
import fr.testapp.airdeve2.fragments.AccountMailConnectionFragment;
import fr.testapp.airdeve2.fragments.EventDescriptionFragment;
import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>
{
    private LayoutInflater inflater;
    private List<EventModel> data;
    private MainActivity mainContext;
    private Fragment fragment;

    public EventAdapter(List<EventModel> data, Context context, MainActivity mainContext, Fragment fragment)
    {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.mainContext = mainContext;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = this.inflater.inflate(R.layout.item_horizontal_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position)
    {
        EventModel event = this.getData().get(position);

        //utilisation de glide pour recuperer les images (temporaire)
        Glide.with(mainContext).load(Uri.parse(event.getImageUrl())).into(holder.myImageView);

        holder.nameTextView.setText(event.getName());
        holder.myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainContext.loadFragment(new EventDescriptionFragment(mainContext, event,fragment));
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
        private ImageView myImageView;
        private TextView nameTextView;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.myImageView = itemView.findViewById(R.id.recycler_event_image);
            this.nameTextView = itemView.findViewById(R.id.recycler_event_name);
        }

    }

    public List<EventModel> getData() {
        return data;
    }

    public void setData(List<EventModel> data) {
        this.data = data;
    }
}
