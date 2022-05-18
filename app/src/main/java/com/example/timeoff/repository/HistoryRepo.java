package com.example.timeoff.repository;

import com.example.timeoff.models.History;

import java.util.ArrayList;

public class HistoryRepo {
    ArrayList<History> holder = new ArrayList<>();


    public HistoryRepo() {
        holder = new ArrayList<>();

        History m1 = new History("Бегемот", "Еда\nКофе\nВода", "Монополия\nМонополия1");
        holder.add(m1);
        History m2 = new History("Бегемот", "Еда\nКофе\nВода", "Монополия\nМонополия1");
        holder.add(m2);
        History m3 = new History("Бегемот", "Еда\nКофе\nВода", "Монополия\nМонополия1");
        holder.add(m3);

    }

    public ArrayList<History> getHolder() {
        return holder;
    }

    public void setHolder(ArrayList<History> holder) {
        this.holder = holder;
    }
}
