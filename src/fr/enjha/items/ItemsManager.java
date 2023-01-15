package fr.enjha.items;

import fr.enjha.utils.Cooldown;

import java.util.ArrayList;
import java.util.List;

public class ItemsManager {

    private List<ItemCustom> itemsCustoms;
    private final GrapplingHook grapplingHook;
    private final FreezeBall freezeBall;



    public ItemsManager() {
        this.grapplingHook = new GrapplingHook(new Cooldown(7));
        this.freezeBall = new FreezeBall(new Cooldown(15));
    }

    public void init() {
        // Initialisation de la liste des items customs
        itemsCustoms = new ArrayList<>();

        // Initialisation des items customs
        this.grapplingHook.init();
        this.freezeBall.init();

        // Ajout à la liste des items customs
        itemsCustoms.add(this.grapplingHook);
        itemsCustoms.add(this.freezeBall);
    }

    public ItemCustom getItemCustom(Class<?> item) {
        for (ItemCustom i : itemsCustoms) {
            if (item.getName().equals(i.getClass().getName())) {
                return i;
            }
        }
        return null;
    }
}
