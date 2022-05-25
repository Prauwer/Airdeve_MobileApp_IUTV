package fr.testapp.airdeve2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.testapp.airdeve2.MainActivity;
import fr.testapp.airdeve2.R;

public class AccountRegistrationFragment extends Fragment
{

    private View view;
    private Button googleButton;
    private MainActivity mainContext;

    public AccountRegistrationFragment(MainActivity context)
    {
        this.mainContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }
}