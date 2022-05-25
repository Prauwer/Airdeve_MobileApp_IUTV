package fr.testapp.airdeve2.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import fr.testapp.airdeve2.EventRepository;
import fr.testapp.airdeve2.adapter.CommentAdapter;
import fr.testapp.airdeve2.model.EventModel;
import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.model.UserModel;

public class EventDescriptionFragment extends Fragment
{
    private EventModel event;
    private MainActivity mainContext;
    private EventRepository repo;
    private Fragment previousFragment;
    private Boolean liked;

    private ImageView eventImage;
    private ImageButton eventBackArrow;
    private ImageButton eventOptions;
    private TextView eventNameTxt;
    private TextView eventShareTxt;
    private TextView eventCategoryTxt;
    private TextView eventCreatorTxt;
    private TextView eventDescriptionTxt;
    private TextView eventDateTxt;
    private TextView eventPlaceTxt;
    private ProgressBar eventProgressBar;
    private TextView eventMoneyCountTxt;
    private TextView eventMaxMoneyCountTxt;
    private TextView eventTicketCountTxt;
    private TextView eventDaysRemainingTxt;
    private ImageButton profilButton;
    private ImageButton likeButton;
    private ImageButton shareButton;
    private ImageButton commentButton;
    private ConstraintLayout sendMessage;
    private Button buyButton;
    private RecyclerView commentRecylcerView;

    public EventDescriptionFragment(MainActivity mainContext, EventModel event, Fragment previousFragment)
    {
        this.event = event;
        this.mainContext = mainContext;
        this.previousFragment = previousFragment;
        this.repo = mainContext.getRepo();
        if(mainContext.getMainUser() != null)
        {
            this.liked = mainContext.getMainUser().getEventLiked().contains(event);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_event_description, container, false);

        eventImage = view.findViewById(R.id.event_image);
        eventBackArrow = view.findViewById(R.id.event_back_arrow);
        eventOptions = view.findViewById(R.id.event_options);
        eventNameTxt = view.findViewById(R.id.event_name);
        eventShareTxt = view.findViewById(R.id.event_share_count);
        eventCreatorTxt = view.findViewById(R.id.event_creator);
        eventCategoryTxt = view.findViewById(R.id.event_text_category);
        eventDescriptionTxt = view.findViewById(R.id.event_description);
        eventDateTxt = view.findViewById(R.id.event_date);
        eventPlaceTxt = view.findViewById(R.id.event_place);
        eventProgressBar = view.findViewById(R.id.event_progress_bar);
        eventMoneyCountTxt = view.findViewById(R.id.event_money_count);
        eventMaxMoneyCountTxt = view.findViewById(R.id.event_max_money);
        eventTicketCountTxt = view.findViewById(R.id.event_ticket_count);
        eventDaysRemainingTxt = view.findViewById(R.id.event_day_remaining);
        profilButton = view.findViewById(R.id.event_profil_picture);
        likeButton = view.findViewById(R.id.event_like_button);
        shareButton = view.findViewById(R.id.event_share_button);
        commentButton = view.findViewById(R.id.event_comment_button);
        sendMessage = view.findViewById(R.id.event_send_comment);
        buyButton = view.findViewById(R.id.event_buy_button);
        commentRecylcerView = view.findViewById(R.id.event_vertical_comment_recyclerView);

        eventBackArrow.getBackground().setAlpha(70);
        eventBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainContext.loadFragment(previousFragment);
            }
        });

        eventOptions.getBackground().setAlpha(70);

        if(mainContext.getMainUser() != null) {
            if (mainContext.getMainUser().getId() == event.getOrganizers().get(0).getId()) {
                eventOptions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu menu = new PopupMenu(mainContext, eventOptions);
                        menu.getMenuInflater().inflate(R.menu.event_option_menu, menu.getMenu());
                        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                switch (menuItem.getItemId()) {
                                    case R.id.modify_event:
                                        mainContext.loadFragment(new EventModifyFragment(mainContext, event, mainContext.getMainUser()));
                                    case R.id.supress_event:
                                        break; //procédure suppression
                                    default:
                                        break;
                                }
                                return false;
                            }
                        });
                        menu.show();
                    }
                });
            }
            /*else
            {
                eventOptions.setVisibility(View.GONE);
            }*/
        }
        /*else
        {
            eventOptions.setVisibility(View.GONE);
        }*/

        eventNameTxt.setText(this.getEvent().getName());

        this.refreshLikeCount(view);

        eventShareTxt.setText("15");

        eventCreatorTxt.setText(this.getEvent().getOrganizers().get(0).getUsername());

        eventCategoryTxt.setText(this.getEvent().getCategory());

        eventDescriptionTxt.setText(this.getEvent().getDescription());

        eventDateTxt.setText(mainContext.getFormatDate(this.getEvent().getSalesStart(),this.getEvent().getSalesEnd()));

        eventPlaceTxt.setText(this.getEvent().getPlace());

        eventProgressBar.setProgress(this.getProgressFromEvent());

        eventMoneyCountTxt.setText(mainContext.getStringKFromFloat(this.getEvent().getTicketSold()*this.getEvent().getTicketPrice()) + "€");

        eventMaxMoneyCountTxt.setText("sur " + mainContext.getStringKFromFloat(this.getEvent().getMoneyNeeded()) + "€");

        eventTicketCountTxt.setText(mainContext.getStringKFromFloat(this.getEvent().getTicketSold()));

        eventDaysRemainingTxt.setText("12");

        Glide.with(mainContext).load(Uri.parse(event.getOrganizers().get(0).getProfilePictureUrl())).into(profilButton);

        profilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainContext().loadFragment(new AccountFragment(getMainContext(),getCreator()));
            }
        });

        if(liked != null && liked) {
            likeButton.setImageDrawable(mainContext.getResources().getDrawable(R.drawable.heart_filled));
        }


        if(mainContext.getMainUser() != null) {
            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (liked) {
                        likeButton.setImageDrawable(mainContext.getResources().getDrawable(R.drawable.heart));
                        getEvent().setLike(getEvent().getLike() - 1);
                        mainContext.getMainUser().getEventLiked().remove(event);
                    } else {
                        likeButton.setImageDrawable(mainContext.getResources().getDrawable(R.drawable.heart_filled));
                        getEvent().setLike(getEvent().getLike() + 1);
                        mainContext.getMainUser().getEventLiked().add(event);
                    }
                }
            });
        }

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        commentRecylcerView.setAdapter(new CommentAdapter(event.getCommentList(), super.getContext(), mainContext, this));
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commentRecylcerView.getVisibility() == view.VISIBLE)
                {
                    viewGoneAnimator(commentRecylcerView);
                    viewGoneAnimator(sendMessage);
                }
                else
                {
                    viewVisibleAnimator(commentRecylcerView);
                    viewVisibleAnimator(sendMessage);
                }
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainContext().loadFragment(new HomeFragment(mainContext));
            }
        });

        return view;
    }

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }

    public MainActivity getMainContext() {
        return mainContext;
    }

    public void setMainContext(MainActivity mainContext) {
        this.mainContext = mainContext;
    }

    public void refreshLikeCount(View view)
    {
        TextView eventLikeTxt = view.findViewById(R.id.event_like_count);
        eventLikeTxt.setText(this.mainContext.getStringKFromFloat(this.getEvent().getLike()));
    }

    public int getProgressFromEvent()
    {
        float dividende = this.getEvent().getTicketSold()*this.getEvent().getTicketPrice();
        float diviseur  = this.getEvent().getMoneyNeeded();
        float quotient  = dividende/diviseur;
        return (int) (100 * quotient);
    }

    private void viewGoneAnimator(View view)
    {
        view.animate()
                .alpha(0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                });
    }

    private void viewVisibleAnimator(View view)
    {
        view.animate()
                .alpha(1f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(view.VISIBLE);
                    }
                });
    }

    public UserModel getCreator()
    {
        for(UserModel user: this.mainContext.getRepo().getUserList())
        {

            if ( user.getUsername() == this.eventCreatorTxt.getText() )
            {
                return user;
            }
        }
        return null;
    }
}
