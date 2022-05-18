package com.example.timeoff.views;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.timeoff.MainActivity;
import com.example.timeoff.R;
import com.example.timeoff.databinding.PersonBinding;
import com.example.timeoff.databinding.RoomChooserBinding;
import com.example.timeoff.viewModels.PersonViewModel;
import com.example.timeoff.viewModels.RoomViewModel;

public class RoomFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RoomFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RoomFragment newInstance(String param1, String param2) {
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Dialog dialog;
    public final RoomViewModel mViewModel = new RoomViewModel();
    RoomChooserBinding binding;
    public void dialogView(RoomViewModel mViewModel, String[] name) {
        //Создание и вызова диалогового окна
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.booker_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Прозрачный фон
        dialog.setCancelable(false); // нельзя закрыть кнопкой назад
        dialog.show();
        (dialog.findViewById(R.id.butn_back_from_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getContext(), "Команата забронирована", Toast.LENGTH_SHORT).show();
            }
        });

        (dialog.findViewById(R.id.enter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText people = dialog.findViewById(R.id.people);
                EditText date = dialog.findViewById(R.id.date);
                EditText start_time = dialog.findViewById(R.id.time_start_edit);
                EditText end_time = dialog.findViewById(R.id.time_end_edit);
                if (mViewModel.isValid(people.getText().toString(), date.getText().toString(), start_time.getText().toString(), end_time.getText().toString())) {
                    mViewModel.addBook(people.getText().toString(), date.getText().toString(), start_time.getText().toString(), end_time.getText().toString(), name);
                    dialog.dismiss();
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = RoomChooserBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        RoomViewModel mViewModel = new ViewModelProvider(this).get(RoomViewModel.class);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.room_chooser, container, false);
        View viewDialog = inflater.inflate(R.layout.booker_dialog, container, false);
        // Создание спинера для списка комнат
        Spinner roomChooser = view.findViewById(R.id.room_choose_spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.rooms));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomChooser.setAdapter(myAdapter);
        // Создание спинера для списка комнат
        Button btnBook = view.findViewById(R.id.book_a_room_roomchooser);
        Button btnCls = viewDialog.findViewById(R.id.butn_back_from_dialog);

        final String[] roomName = {""};
        roomChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        //Бегемотня
                        roomName[0] = "Бегемотня";
                        break;
                    case 1:
                        //Отдых
                        roomName[0] = "Отдых";
                        break;
                    case 2:
                        roomName[0] = "Ночной файт";
                        break;
                        //Ночной файт
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.book_a_room_roomchooser) {
                    dialogView(mViewModel, roomName);
                }
            }
        };
        btnBook.setOnClickListener(onClickListener);


        return view;
    }
}