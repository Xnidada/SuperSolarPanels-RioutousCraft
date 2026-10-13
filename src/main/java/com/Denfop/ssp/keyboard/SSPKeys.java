/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.core.IC2
 *  ic2.core.util.Keyboard
 *  ic2.core.util.Keyboard$IKeyWatcher
 *  ic2.core.util.Keyboard$Key
 *  ic2.core.util.ReflectionUtil
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.common.util.EnumHelper
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.commons.lang3.ArrayUtils
 */
package com.Denfop.ssp.keyboard;

import ic2.core.IC2;
import ic2.core.util.Keyboard;
import ic2.core.util.ReflectionUtil;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Set;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

public final class SSPKeys
extends Keyboard {
    public static final Keyboard.IKeyWatcher FLY_KEY = new KeyWatcher(SSPKey.fly);
    public static final Keyboard.IKeyWatcher poison1;
    public static final Keyboard.IKeyWatcher poison;
    public static final Keyboard.IKeyWatcher poison2;

    public static void addFlyKey() {
        IC2.keyboard.addKeyWatcher(FLY_KEY);
        IC2.keyboard.addKeyWatcher(poison1);
        IC2.keyboard.addKeyWatcher(poison2);
        IC2.keyboard.addKeyWatcher(poison);
    }

    public static boolean Isremovepoison(EntityPlayer player) {
        return IC2.keyboard.isKeyDown(player, poison);
    }

    public static boolean Isremovepoison1(EntityPlayer player) {
        return IC2.keyboard.isKeyDown(player, poison1);
    }

    public static boolean Isremovepoison2(EntityPlayer player) {
        return IC2.keyboard.isKeyDown(player, poison2);
    }

    public static boolean isFlyKeyDown(EntityPlayer player) {
        return IC2.keyboard.isKeyDown(player, FLY_KEY);
    }

    static {
        poison = new KeyWatcher(SSPKey.poison);
        poison1 = new KeyWatcher(SSPKey.poison1);
        poison2 = new KeyWatcher(SSPKey.poison2);
    }

    public static class KeyWatcher
    implements Keyboard.IKeyWatcher {
        public final SSPKey key;

        public KeyWatcher(SSPKey key) {
            this.key = key;
        }

        public Keyboard.Key getRepresentation() {
            return this.key.key;
        }

        @SideOnly(value=Side.CLIENT)
        public void checkForKey(Set<Keyboard.Key> pressedKeys) {
            if (GameSettings.func_100015_a((KeyBinding)this.key.binding)) {
                pressedKeys.add(this.getRepresentation());
            }
        }
    }

    public static enum SSPKey {
        fly(33, "Fly Key"),
        poison(34, "Poison Leggings"),
        poison1(35, "Poison Boots"),
        poison2(36, "Poison Chestplate");

        public final Keyboard.Key key = this.addKey(this.name());
        @SideOnly(value=Side.CLIENT)
        public KeyBinding binding;

        public static Field getKeysField() {
            try {
                Field field = ReflectionUtil.getField(Keyboard.Key.class, (String[])new String[]{"keys"});
                ReflectionUtil.getField(Field.class, (String[])new String[]{"modifiers"}).setInt(field, field.getModifiers() & 0xFFFFFFEF);
                return field;
            }
            catch (Exception e) {
                throw new RuntimeException("Error reflecting keys field!", e);
            }
        }

        private SSPKey(int keyID, String description) {
            if (IC2.platform.isRendering()) {
                this.binding = new KeyBinding(description, keyID, "SuperSolarPanels".substring(0, 1).toUpperCase(Locale.ENGLISH) + "SuperSolarPanels".substring(1));
                ClientRegistry.registerKeyBinding((KeyBinding)this.binding);
            }
        }

        private Keyboard.Key addKey(String name) {
            Keyboard.Key key = (Keyboard.Key)EnumHelper.addEnum(Keyboard.Key.class, (String)name, (Class[])new Class[0], (Object[])new Object[0]);
            ReflectionUtil.setValue(null, (Field)SSPKey.getKeysField(), (Object)ArrayUtils.add((Object[])Keyboard.Key.keys, (Object)key));
            return key;
        }
    }
}

