package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TryCon1 tryCon = new TryCon1();

        ArrayList<Cities> cities2 = new ArrayList<Cities>();
        Human h2 = new Human(15, "Ilya");
        tryCon.insertHuman(h2);
        Human h3 = new Human(14, "Sasha");
        tryCon.insertHuman(h3);
        Countries ru = new Countries(1, "Russia");
        tryCon.insertCountry(ru);
        Countries uz = new Countries(2, "Uzbekstan");
        tryCon.insertCountry(uz);
        cities2.add(new Cities(1, "Astana", ru, h3));
        cities2.add(new Cities(1, "Osh", uz, h2));

    }
}
