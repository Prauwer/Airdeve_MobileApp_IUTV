package fr.testapp.airdeve2.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Date;

import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;
import fr.testapp.airdeve2.model.EventModel;
import fr.testapp.airdeve2.model.TicketTypeModel;
import fr.testapp.airdeve2.model.UserModel;

public class EventModifyFragment extends EventCreationFragment{

    private TextView title;
    private Button nextButton, less, more;
    private EditText nameText, capacityText, descriptionText,imgUrlText, moneyNeededText, placeText;
    private EditText TicketNameText1, TicketNameText2, TicketNameText3, TicketNameText4, TicketNameText5;
    private EditText TicketPriceText1, TicketPriceText2, TicketPriceText3, TicketPriceText4, TicketPriceText5;
    private EditText TicketAmountText1, TicketAmountText2, TicketAmountText3, TicketAmountText4, TicketAmountText5;
    private EditText TicketDescText1, TicketDescText2, TicketDescText3, TicketDescText4, TicketDescText5;
    private DatePicker salesEndDate;
    private MainActivity mainContext;
    private UserModel mainUser;
    private LinearLayout ticket1, ticket2, ticket3, ticket4, ticket5;
    private RadioButton selectedRadioButton;
    private RadioGroup categoryButton;

    private EventModel event;
    private TicketTypeModel Ticket1, Ticket2, Ticket3, Ticket4, Ticket5;


    public EventModifyFragment(MainActivity context, EventModel event, UserModel mainUser) { super(context,mainUser); this.mainContext = context; this.event=event; this.mainUser=mainUser; }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_creation, container, false);

        title = (TextView) view.findViewById(R.id.fragment_event_textview_welcome);
        nextButton = (Button) view.findViewById(R.id.fragment_event_button_next);
        nameText = (EditText) view.findViewById(R.id.fragment_event_edittext_name);
        capacityText = (EditText) view.findViewById(R.id.fragment_event_edittext_capacity);
        moneyNeededText = (EditText) view.findViewById(R.id.fragment_event_edittext_moneyNeeded);
        descriptionText = (EditText) view.findViewById(R.id.fragment_event_edittext_desc);
        imgUrlText = (EditText) view.findViewById(R.id.fragment_event_edittext_img);
        salesEndDate = (DatePicker) view.findViewById(R.id.fragment_event_datepicker_salesEnd);
        placeText = (EditText) view.findViewById(R.id.fragment_event_edittext_place);
        categoryButton = (RadioGroup) view.findViewById(R.id.fragment_event_radio_category);

        title.setText("Modifions votre évènement ensemble !");
        nameText.setText(event.getName());
        capacityText.setText(Integer.toString(event.getCapacity()));
        descriptionText.setText(event.getDescription());
        moneyNeededText.setText(Integer.toString(event.getMoneyNeeded()));
        placeText.setText(event.getPlace());
        imgUrlText.setText(event.getImageUrl());

        if (event.getCategory().toString().contentEquals("Musique")){
            categoryButton.check(R.id.fragment_event_radio_music);
        }
        if (event.getCategory().toString().contentEquals("Loisir")){
            categoryButton.check(R.id.fragment_event_radio_hobbies);
        }
        if (event.getCategory().toString().contentEquals("Exposition et vernissage")){
            categoryButton.check(R.id.fragment_event_radio_art);
        }
        if (event.getCategory().toString().contentEquals("Evènement sportif")){
            categoryButton.check(R.id.fragment_event_radio_sport);
        }
        if (event.getCategory().toString().contentEquals("Evènement associatif")){
            categoryButton.check(R.id.fragment_event_radio_charity);
        }

        if (event.getCategory().toString().contentEquals("Théâtre et spectacle")) {
            categoryButton.check(R.id.fragment_event_radio_shows);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;
                String message = "";

                // Récupération du nom
                if (TextUtils.isEmpty(nameText.getText().toString().trim())){
                    message+="Le nom est vide !\n";
                    error = true;
                }else{
                    message+="Nom : "+ nameText.getText().toString()+"\n";
                }

                //Récupération du nombre de participants
                if (TextUtils.isEmpty(capacityText.getText().toString().trim())){
                    message+="Le nombre de participants est vide !\n";
                    error=true;
                }else{
                    message+="Participants : "+ capacityText.getText().toString()+"\n";
                }

                //Récupération du plafond
                if (TextUtils.isEmpty(moneyNeededText.getText().toString().trim())){
                    message+="Le plafond attendu est vide !\n";
                    error=true;
                }else{
                    message+="Plafond attendu : "+ moneyNeededText.getText().toString()+"\n";
                }

                // Récupération du texte
                if (TextUtils.isEmpty(placeText.getText().toString().trim())){
                    message+="Le lieu est vide !\n";
                    error = true;
                }else{
                    message+="Nom : "+ placeText.getText().toString()+"\n";
                }

                //Récupération de la description
                if (TextUtils.isEmpty(descriptionText.getText().toString().trim())){
                    message+="La description est vide !\n";
                    error=true;
                }else{
                    message+="Description : "+ descriptionText.getText().toString()+"\n";
                }

                //Récupération de l'image
                if (TextUtils.isEmpty(imgUrlText.getText().toString().trim())){
                    message+="L'URL d'image est vide !\n";
                    error=true;
                }else{
                    message+="Image : "+ imgUrlText.getText().toString()+"\n";
                }

                // Récupération du bouton checké
                if(categoryButton.getCheckedRadioButtonId()==-1)
                {
                    message+="Aucun type choisi !\n";
                    error=true;
                }
                else
                {
                    // get selected radio button from radioGroup
                    int selectedId = categoryButton.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    selectedRadioButton = (RadioButton)view.findViewById(selectedId);
                    message+="Type : "+ selectedRadioButton.getText().toString()+"\n";
                }

                //Récupération de la date
                Date currentTime = Calendar.getInstance().getTime();
                Date maxTime = Calendar.getInstance().getTime();
                currentTime.setHours(00);
                currentTime.setMinutes(00);
                currentTime.setSeconds(00);
                maxTime.setHours(00);
                maxTime.setMinutes(00);
                maxTime.setSeconds(00);
                maxTime.setMonth(currentTime.getMonth()+6);

                Date dateE = new Date(salesEndDate.getYear() - 1900, salesEndDate.getMonth(), salesEndDate.getDayOfMonth());

                if (dateE.compareTo(maxTime)>=0) {
                    message += "La date de fin excède 6 mois à partir d'aujourd'hui !\n";
                    message+="Date max : "+ maxTime+"\n";
                    error=true;
                }else if (dateE.compareTo(currentTime)<0) {
                    message += "La date de fin indiquée est avant la date du jour !\n";
                    message+="Date du jour : "+ currentTime+"\n";
                    error=true;
                }else{
                    message+="Date de fin : "+dateE+"\n";
                }

                //Récupération des tickets

                TicketNameText1 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket1Name);
                TicketPriceText1 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket1Price);
                TicketAmountText1 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket1Amount);
                TicketDescText1 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket1Desc);
                boolean ticket1val=false;

                TicketNameText2 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket2Name);
                TicketPriceText2 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket2Price);
                TicketAmountText2 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket2Amount);
                TicketDescText2 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket2Desc);
                boolean ticket2val=false;

                TicketNameText3 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket3Name);
                TicketPriceText3 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket3Price);
                TicketAmountText3 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket3Amount);
                TicketDescText3 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket3Desc);
                boolean ticket3val=false;

                TicketNameText4 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket4Name);
                TicketPriceText4 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket4Price);
                TicketAmountText4 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket4Amount);
                TicketDescText4 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket4Desc);
                boolean ticket4val=false;

                TicketNameText5 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket5Name);
                TicketPriceText5 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket5Price);
                TicketAmountText5 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket5Amount);
                TicketDescText5 = (EditText) view.findViewById(R.id.fragment_event_edittext_ticket5Desc);
                boolean ticket5val=false;

                if (!TextUtils.isEmpty(TicketNameText1.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketPriceText1.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketAmountText1.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketDescText1.getText().toString().trim())){
                    ticket1val=true;
                    message+="Ticket 1 correct. Nom : "+TicketNameText1.getText().toString().trim()+". Prix : "+TicketPriceText1.getText().toString().trim()+". Nombre : "+TicketAmountText1.getText().toString().trim()+". Description : "+TicketDescText1.getText().toString().trim()+"\n";
                }

                if (!TextUtils.isEmpty(TicketNameText2.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketPriceText2.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketAmountText2.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketDescText2.getText().toString().trim())){
                    ticket2val=true;
                    message+="Ticket 2 correct. Nom : "+TicketNameText2.getText().toString().trim()+". Prix : "+TicketPriceText2.getText().toString().trim()+". Nombre : "+TicketAmountText2.getText().toString().trim()+". Description : "+TicketDescText2.getText().toString().trim()+"\n";
                }

                if (!TextUtils.isEmpty(TicketNameText3.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketPriceText3.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketAmountText3.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketDescText3.getText().toString().trim())){
                    ticket3val=true;
                    message+="Ticket 3 correct. Nom : "+TicketNameText3.getText().toString().trim()+". Prix : "+TicketPriceText3.getText().toString().trim()+". Nombre : "+TicketAmountText3.getText().toString().trim()+". Description : "+TicketDescText3.getText().toString().trim()+"\n";
                }

                if (!TextUtils.isEmpty(TicketNameText4.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketPriceText4.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketAmountText4.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketDescText4.getText().toString().trim())){
                    ticket4val=true;
                    message+="Ticket 4 correct. Nom : "+TicketNameText4.getText().toString().trim()+". Prix : "+TicketPriceText4.getText().toString().trim()+". Nombre : "+TicketAmountText4.getText().toString().trim()+". Description : "+TicketDescText4.getText().toString().trim()+"\n";
                }

                if (!TextUtils.isEmpty(TicketNameText5.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketPriceText5.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketAmountText5.getText().toString().trim()) &&
                        !TextUtils.isEmpty(TicketDescText5.getText().toString().trim())){
                    ticket5val=true;
                    message+="Ticket 5 correct. Nom : "+TicketNameText5.getText().toString().trim()+". Prix : "+TicketPriceText5.getText().toString().trim()+". Nombre : "+TicketAmountText5.getText().toString().trim()+". Description : "+TicketDescText5.getText().toString().trim()+"\n";
                }

                if (!ticket1val && !ticket2val && !ticket3val && !ticket4val && !ticket5val){
                    error=true;
                    message+= "Aucun ticket correct!\n";
                }else{
                    if (ticket1val){
                        Ticket1 = new TicketTypeModel(Integer.valueOf(TicketPriceText1.getText().toString()),Integer.valueOf(TicketAmountText1.getText().toString()),TicketNameText1.getText().toString().trim().trim(), TicketDescText1.getText().toString());
                    }
                    if (ticket2val){
                        Ticket2 = new TicketTypeModel(Integer.valueOf(TicketPriceText2.getText().toString()),Integer.valueOf(TicketAmountText2.getText().toString()),TicketNameText2.getText().toString().trim().trim(), TicketDescText2.getText().toString());
                    }
                    if (ticket3val){
                        Ticket3 = new TicketTypeModel(Integer.valueOf(TicketPriceText3.getText().toString()),Integer.valueOf(TicketAmountText3.getText().toString()),TicketNameText3.getText().toString().trim().trim(), TicketDescText3.getText().toString());
                    }
                    if (ticket4val){
                        Ticket4 = new TicketTypeModel(Integer.valueOf(TicketPriceText4.getText().toString()),Integer.valueOf(TicketAmountText4.getText().toString()),TicketNameText4.getText().toString().trim().trim(), TicketDescText4.getText().toString());
                    }
                    if (ticket5val) {
                        Ticket5 = new TicketTypeModel(Integer.valueOf(TicketPriceText5.getText().toString()), Integer.valueOf(TicketAmountText5.getText().toString()), TicketNameText5.getText().toString().trim().trim(), TicketDescText5.getText().toString());
                    }
                }


                //Construction du message de confirmation
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());


                if (error){
                    builder.setTitle("Des erreurs ont été trouvées !")
                            .setMessage(message)
                            .setPositiveButton("JE VAIS LES CORRIGER", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .create()
                            .show();
                }else{
                    boolean finalTicket1val = ticket1val;
                    boolean finalTicket2val = ticket2val;
                    boolean finalTicket3val = ticket3val;
                    boolean finalTicket4val = ticket4val;
                    boolean finalTicket5val = ticket5val;
                    builder.setTitle("Ces données sont-elles bien correctes ?")
                            .setMessage(message)
                            .setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    event.setName(nameText.getText().toString().trim());
                                    event.setDescription(descriptionText.getText().toString().trim());
                                    event.setImageUrl(imgUrlText.getText().toString().trim());
                                    event.setCapacity(Integer.valueOf(capacityText.getText().toString().trim()));
                                    event.setSalesEnd(dateE);
                                    event.setMoneyNeeded(Integer.valueOf(moneyNeededText.getText().toString().trim()));
                                    event.setPlace(placeText.getText().toString().trim());
                                    event.setCategory(selectedRadioButton.getText().toString());

                                    event.delTickets();

                                    if (finalTicket1val){
                                        event.addTicket(Ticket1);
                                    }
                                    if (finalTicket2val){
                                        event.addTicket(Ticket2);
                                    }
                                    if (finalTicket3val){
                                        event.addTicket(Ticket3);
                                    }
                                    if (finalTicket4val){
                                        event.addTicket(Ticket4);
                                    }
                                    if (finalTicket5val){
                                        event.addTicket(Ticket5);
                                    }




                                    mainContext.loadFragment(new EventDescriptionFragment(mainContext, event, new HomeFragment(mainContext)));
                                }
                            })
                            .setNegativeButton("NON", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .create()
                            .show();
                }

            }

        });

        //Affichage de plus ou moins de tickets (1-5)

        ticket1 = (LinearLayout) view.findViewById(R.id.fragment_event_ll_ticket1);
        ticket2 = (LinearLayout) view.findViewById(R.id.fragment_event_ll_ticket2);
        ticket3 = (LinearLayout) view.findViewById(R.id.fragment_event_ll_ticket3);
        ticket4 = (LinearLayout) view.findViewById(R.id.fragment_event_ll_ticket4);
        ticket5 = (LinearLayout) view.findViewById(R.id.fragment_event_ll_ticket5);

        ticket2.setVisibility(View.GONE);
        ticket3.setVisibility(View.GONE);
        ticket4.setVisibility(View.GONE);
        ticket5.setVisibility(View.GONE);

        more = (Button) view.findViewById(R.id.fragment_event_button_more);
        less = (Button) view.findViewById(R.id.fragment_event_button_less);

        less.setAlpha(.5f);
        less.setClickable(false);

        final int[] i = {1};
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(i[0]){

                    case 1:
                        ticket2.setVisibility(View.VISIBLE);
                        i[0] +=1;
                        less.setClickable(true);
                        less.setAlpha(1);
                        break;

                    case 2:
                        ticket3.setVisibility(View.VISIBLE);
                        i[0] +=1;
                        break;

                    case 3:
                        ticket4.setVisibility(View.VISIBLE);
                        i[0] +=1;
                        break;

                    case 4:
                        ticket5.setVisibility(View.VISIBLE);
                        i[0] +=1;
                        more.setClickable(false);
                        more.setAlpha(.5f);
                        break;

                    case 5:
                        break;
                }
            }
        });

        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(i[0]){

                    case 1:
                        break;

                    case 2:
                        ticket2.setVisibility(View.GONE);
                        i[0] -=1;
                        less.setClickable(false);
                        less.setAlpha(.5f);
                        break;

                    case 3:
                        ticket3.setVisibility(View.GONE);
                        i[0] -=1;
                        break;

                    case 4:
                        ticket4.setVisibility(View.GONE);
                        i[0] -=1;
                        break;
                    case 5:
                        ticket5.setVisibility(View.GONE);
                        i[0] -=1;
                        more.setClickable(true);
                        more.setAlpha(1);
                        break;
                }
            }

        });


        return view;
    }

}