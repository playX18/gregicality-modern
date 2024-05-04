package com.playx.gtx.materials;

import com.gregtechceu.gtceu.api.data.chemical.Element;

public class GTXElements extends Element {

    public static Element Trinium;
    public static Element Adamantium;
    public static Element Vibranium;
    public static Element Taranium;

    public GTXElements(long protons, long neutrons, long halfLifeSeconds, String decayTo, String name, String symbol, boolean isIsotope) {
        super(protons, neutrons, halfLifeSeconds, decayTo, name, symbol, isIsotope);
    }


    public static void init() {
        Trinium = new Element(210, 300, -1, null, "TRINIUM", "Ke", false);
        Adamantium = new Element(351, 509, -1, null, "ADAMANTIUM", "Ad", false);
        Vibranium = new Element(457, 663, -1, null, "VIBRANIUM", "Vb", false);
        Taranium = new Element(321, 478, -1, null, "TARANIUM", "Tn", false);

    }
}
