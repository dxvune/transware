package fr.undev.linuxhacks.listeners;

import fr.undev.linuxhacks.module.Module;
import fr.undev.linuxhacks.module.Modules;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientTickListener {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        for (String id : Modules.getEnabled()) {
            try {
                Module module = Modules.getById(id);
                module.onTick(event);
            } catch (Exception e) {}
        }
        if (!Modules.getToggleQueue().isEmpty()) {
            for (String id : Modules.getToggleQueue()) {
                Modules.toggle(id);
            }
            Modules.getToggleQueue().clear();
        }
    }
}
