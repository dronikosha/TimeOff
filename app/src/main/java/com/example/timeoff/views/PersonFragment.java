package com.example.timeoff.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.timeoff.MainActivity;
import com.example.timeoff.R;
import com.example.timeoff.adapters.CartAdapter;
import com.example.timeoff.adapters.HistoryAdapter;
import com.example.timeoff.databinding.AuthLayoutBinding;
import com.example.timeoff.databinding.PersonBinding;
import com.example.timeoff.models.FoodItem;
import com.example.timeoff.models.History;
import com.example.timeoff.repository.CartRepo;
import com.example.timeoff.repository.DAOUser;
import com.example.timeoff.viewModels.AuthViewModel;
import com.example.timeoff.viewModels.PersonViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.BarcodeMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonFragment newInstance(String param1, String param2) {
        PersonFragment fragment = new PersonFragment();
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
    public final PersonViewModel mViewModel = new PersonViewModel();
    PersonBinding binding;
    public DAOUser daoUser = new DAOUser();
    public HistoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PersonBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        PersonViewModel mViewModel = new ViewModelProvider(this).get(PersonViewModel.class);

        String name = binding.FIO.getText().toString().trim();
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(name, BarcodeFormat.QR_CODE, 1000, 1000);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            binding.QR.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        daoUser.getDatabaseReference().get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        if (!TextUtils.isEmpty(String.valueOf(dataSnapshot.child("name").getValue())) && !TextUtils.isEmpty(String.valueOf(dataSnapshot.child("surname").getValue()))) {
                            String name = String.valueOf(dataSnapshot.child("name").getValue());
                            String surname = String.valueOf(dataSnapshot.child("surname").getValue());
                            String FIO = name + " " + surname;
                            binding.FIO.setText(FIO);
                        }
                    }
                }
            }
        });
        ArrayList<History> list = new ArrayList<History>();
        DatabaseReference db = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("history");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    History item = ds.getValue(History.class);
                    String id = ds.getKey();
                    item.setRoomName(id);
                    list.add(item);
                }
                RecyclerView hisRec = binding.historyPersonView;
                Collections.reverse(list);
                hisRec.setAdapter(new HistoryAdapter(list));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                hisRec.setLayoutManager(gridLayoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.person, container, false);

        return v;
    }

    public void dialogViewChange() {
        //Создание и вызова диалогового окна
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.settings_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Прозрачный фон
        dialog.setCancelable(false); // нельзя закрыть кнопкой назад
        dialog.show();
        EditText name = (EditText) dialog.findViewById(R.id.settings_name);
        EditText surname = (EditText) dialog.findViewById(R.id.settings_surname);
        (dialog.findViewById(R.id.butn_back_from_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.addName(name.getText().toString(), surname.getText().toString());
                dialog.dismiss();
            }
        });
        //Создание и вызова диалогового окна
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner settings = view.findViewById(R.id.edit_person);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.settings));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        settings.setAdapter(myAdapter);
        settings.setSelection(1, false);
        settings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        dialogViewChange();
                        break;
                    case 1:
                        Toast.makeText(getContext(), "EXIT", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}