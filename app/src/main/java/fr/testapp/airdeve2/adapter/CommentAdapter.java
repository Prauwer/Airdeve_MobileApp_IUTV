package fr.testapp.airdeve2.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.fragments.AccountFragment;
import fr.testapp.airdeve2.model.EventCommentModel;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>
{
    private LayoutInflater inflater;
    private List<EventCommentModel> data;
    private MainActivity mainContext;
    private Fragment fragment;

    public CommentAdapter(List<EventCommentModel> data, Context context, MainActivity mainContext, Fragment fragment)
    {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.mainContext = mainContext;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = this.inflater.inflate(R.layout.item_vertical_comment, parent, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        EventCommentModel comment = this.data.get(position);

        Glide.with(mainContext).load(Uri.parse(comment.getAuthor().getProfilePictureUrl())).into(holder.profil_picture);
        holder.content.setText(comment.getText());
        holder.author_name.setText(comment.getAuthor().getUsername());
        holder.created_at.setText(mainContext.getStringDate(comment.getCreatedAt()));
        holder.like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment.setLikes(comment.getLikes()+1);
                holder.like_button.setImageDrawable(mainContext.getResources().getDrawable(R.drawable.heart_filled));;
            }
        });
        holder.profil_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainContext.loadFragment(new AccountFragment(mainContext, comment.getAuthor()));
            }
        });
        /*holder.layout.setMinimumHeight(230);
        holder.layout.setMaxHeight(holder.layout.getMinHeight() + holder.content.getHeight());
        System.out.println(holder.layout.getMinHeight());
        System.out.println(holder.layout.getHeight());
        System.out.println(holder.content.getHeight());
        System.out.println(holder.layout.getMaxHeight());*/
    }

    @Override
    public int getItemCount()
    {
        return this.getData().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageButton profil_picture;
        private ImageButton like_button;
        private TextView author_name;
        private TextView content;
        private TextView created_at;
        private ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.profil_picture = itemView.findViewById(R.id.comment_profil_picture);
            this.author_name = itemView.findViewById(R.id.comment_author);
            this.like_button = itemView.findViewById(R.id.comment_like_button);
            this.content = itemView.findViewById(R.id.comment_text);
            this.created_at = itemView.findViewById(R.id.comment_createdAt);
            this.layout = itemView.findViewById(R.id.comment_layout);
        }

    }

    public List<EventCommentModel> getData() {
        return data;
    }

    public void setData(List<EventCommentModel> data) {
        this.data = data;
    }
}
