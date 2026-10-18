/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.recipe.IMachineRecipeManager
 *  ic2.api.recipe.IRecipeInput
 *  net.minecraft.item.ItemStack
 */
package com.rioutouscraft.ssp;

import com.rioutouscraft.ssp.MolecularTransformerRecipeManager;
import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;

public interface IMolecularTransformerRecipeManager
extends IMachineRecipeManager<IMolecularTransformerRecipeManager.Input, ItemStack, ItemStack> {
    public static final IMolecularTransformerRecipeManager RECIPES = new MolecularTransformerRecipeManager();

    public boolean addRecipe(IRecipeInput var1, long var2, ItemStack var3, boolean var4);

    public long getTotalEUNeeded(ItemStack var1);

    public static final class Input {
        public final IRecipeInput input;
        public final long totalEU;

        public Input(IRecipeInput input, long totalEU) {
            this.input = input;
            this.totalEU = totalEU;
        }

        public String toString() {
            return "MTInput<" + this.input + ", " + this.totalEU + '>';
        }
    }
}

