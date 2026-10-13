/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  ic2.core.IC2
 *  ic2.core.init.BlocksItems
 *  ic2.core.init.Localization
 *  ic2.core.item.tool.HarvestLevel
 *  ic2.core.item.tool.ItemDrill
 *  ic2.core.ref.ItemName
 *  ic2.core.util.StackUtil
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.SPacketBlockChange
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.GameType
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.Denfop.ssp.items.tools;

import com.Denfop.ssp.Configs;
import com.google.common.base.CaseFormat;
import ic2.core.IC2;
import ic2.core.init.BlocksItems;
import ic2.core.init.Localization;
import ic2.core.item.tool.HarvestLevel;
import ic2.core.item.tool.ItemDrill;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemUltDrill
extends ItemDrill {
    protected static final Material[] MATERIALS = new Material[]{Material.field_151576_e, Material.field_151577_b, Material.field_151578_c, Material.field_151595_p, Material.field_151571_B};
    protected static final String NAME = "ItemUltDrill";

    public ItemUltDrill() {
        super(null, Configs.operationEnergyCost, HarvestLevel.Iridium, Configs.maxChargedrill, Configs.transferLimitdrill, Configs.tierdrill, DrillMode.NORMAL.drillSpeed);
        ((ItemUltDrill)BlocksItems.registerItem((Item)this, (ResourceLocation)new ResourceLocation("super_solar_panels", NAME))).func_77655_b(NAME);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)0, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels:" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, NAME), null));
    }

    public static DrillMode readDrillMode(ItemStack stack) {
        return DrillMode.getFromID(StackUtil.getOrCreateNbtData((ItemStack)stack).func_74762_e("toolMode"));
    }

    public static DrillMode readNextDrillMode(ItemStack stack) {
        return DrillMode.getFromID(StackUtil.getOrCreateNbtData((ItemStack)stack).func_74762_e("toolMode") + 1);
    }

    public static void saveDrillMode(ItemStack stack, DrillMode mode) {
        StackUtil.getOrCreateNbtData((ItemStack)stack).func_74768_a("toolMode", mode.ordinal());
    }

    public String func_77658_a() {
        return "super_solar_panels." + super.func_77658_a().substring(4);
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
        if (IC2.keyboard.isModeSwitchKeyDown(player)) {
            ItemStack stack = StackUtil.get((EntityPlayer)player, (EnumHand)hand);
            if (!world.field_72995_K) {
                DrillMode mode = ItemUltDrill.readNextDrillMode(stack);
                ItemUltDrill.saveDrillMode(stack, mode);
                IC2.platform.messagePlayer(player, "super_solar_panels.ItemUltDrill.mode", new Object[]{mode.colour, new Object[]{mode.translationName}});
                this.field_77864_a = mode.drillSpeed;
                this.operationEnergyCost = mode.energyCost;
            }
            return new ActionResult(EnumActionResult.SUCCESS, (Object)stack);
        }
        return super.func_77659_a(world, player, hand);
    }

    public static Collection<BlockPos> getBrokenBlocks(EntityPlayer player, RayTraceResult ray) {
        return ItemUltDrill.getBrokenBlocks(player, ray.func_178782_a(), ray.field_178784_b);
    }

    protected static Collection<BlockPos> getBrokenBlocks(EntityPlayer player, BlockPos pos, EnumFacing side) {
        int xMove;
        int yMove;
        assert (side != null);
        int zMove = yMove = (xMove = 2);
        switch (side.func_176740_k()) {
            case X: {
                xMove = 0;
                break;
            }
            case Y: {
                yMove = 0;
                break;
            }
            case Z: {
                zMove = 0;
            }
        }
        World world = player.field_70170_p;
        ArrayList<BlockPos> list = new ArrayList<BlockPos>(25);
        for (int x = pos.func_177958_n() - xMove; x <= pos.func_177958_n() + xMove; ++x) {
            for (int y = pos.func_177956_o() - yMove; y <= pos.func_177956_o() + yMove; ++y) {
                for (int z = pos.func_177952_p() - zMove; z <= pos.func_177952_p() + zMove; ++z) {
                    BlockPos potential = new BlockPos(x, y, z);
                    if (!ItemUltDrill.canBlockBeMined(world, potential, player, false)) continue;
                    list.add(potential);
                }
            }
        }
        return list;
    }

    protected static boolean canBlockBeMined(World world, BlockPos pos, EntityPlayer player, boolean skipEffectivity) {
        IBlockState state = world.func_180495_p(pos);
        return state.func_177230_c().canHarvestBlock((IBlockAccess)world, pos, player) && (skipEffectivity || ItemUltDrill.isEffective(state.func_185904_a())) && state.func_185903_a(player, world, pos) != 0.0f;
    }

    protected static boolean isEffective(Material material) {
        for (Material option : MATERIALS) {
            if (material != option) continue;
            return true;
        }
        return false;
    }

    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        World world;
        if (ItemUltDrill.readDrillMode(stack) == DrillMode.BIG_HOLES) {
            world = player.field_70170_p;
            if (!world.field_72995_K) {
                Collection<BlockPos> blocks = ItemUltDrill.getBrokenBlocks(player, this.func_77621_a(world, player, true));
                if (!blocks.contains(pos) && ItemUltDrill.canBlockBeMined(world, pos, player, true)) {
                    blocks.add(pos);
                }
                boolean powerRanOut = false;
                for (BlockPos blockPos : blocks) {
                    int experience;
                    IBlockState state;
                    Block block;
                    if (!world.func_175667_e(blockPos) || (block = (state = world.func_180495_p(blockPos)).func_177230_c()).isAir(state, (IBlockAccess)world, blockPos)) continue;
                    if (player instanceof EntityPlayerMP) {
                        experience = ForgeHooks.onBlockBreakEvent((World)world, (GameType)((EntityPlayerMP)player).field_71134_c.func_73081_b(), (EntityPlayerMP)((EntityPlayerMP)player), (BlockPos)blockPos);
                        if (experience < 0) {
                            return false;
                        }
                    } else {
                        experience = 0;
                    }
                    block.func_176208_a(world, blockPos, state, player);
                    if (player.func_184812_l_()) {
                        if (block.removedByPlayer(state, world, blockPos, player, false)) {
                            block.func_176206_d(world, blockPos, state);
                        }
                    } else {
                        if (block.removedByPlayer(state, world, blockPos, player, true)) {
                            block.func_176206_d(world, blockPos, state);
                            block.func_180657_a(world, player, blockPos, state, world.func_175625_s(blockPos), stack);
                            if (experience > 0) {
                                block.func_180637_b(world, blockPos, experience);
                            }
                        }
                        stack.func_179548_a(world, state, blockPos, player);
                    }
                    world.func_175718_b(2001, blockPos, Block.func_176210_f((IBlockState)state));
                    ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)new SPacketBlockChange(world, blockPos));
                }
                if (powerRanOut) {
                    IC2.platform.messagePlayer(player, "super_solar_panels.advancedDrill.ranOut", new Object[0]);
                }
                return true;
            }
        }
        if (ItemUltDrill.readDrillMode(stack) == DrillMode.BIG_HOLES1) {
            world = player.field_70170_p;
            if (!world.field_72995_K) {
                Collection<BlockPos> blocks = ItemUltDrill.getBrokenBlocks1(player, this.func_77621_a(world, player, true));
                if (!blocks.contains(pos) && ItemUltDrill.canBlockBeMined(world, pos, player, true)) {
                    blocks.add(pos);
                }
                boolean powerRanOut = false;
                for (BlockPos blockPos : blocks) {
                    int experience;
                    IBlockState state;
                    Block block;
                    if (!world.func_175667_e(blockPos) || (block = (state = world.func_180495_p(blockPos)).func_177230_c()).isAir(state, (IBlockAccess)world, blockPos)) continue;
                    if (player instanceof EntityPlayerMP) {
                        experience = ForgeHooks.onBlockBreakEvent((World)world, (GameType)((EntityPlayerMP)player).field_71134_c.func_73081_b(), (EntityPlayerMP)((EntityPlayerMP)player), (BlockPos)blockPos);
                        if (experience < 0) {
                            return false;
                        }
                    } else {
                        experience = 0;
                    }
                    block.func_176208_a(world, blockPos, state, player);
                    if (player.func_184812_l_()) {
                        if (block.removedByPlayer(state, world, blockPos, player, false)) {
                            block.func_176206_d(world, blockPos, state);
                        }
                    } else {
                        if (block.removedByPlayer(state, world, blockPos, player, true)) {
                            block.func_176206_d(world, blockPos, state);
                            block.func_180657_a(world, player, blockPos, state, world.func_175625_s(blockPos), stack);
                            if (experience > 0) {
                                block.func_180637_b(world, blockPos, experience);
                            }
                        }
                        stack.func_179548_a(world, state, blockPos, player);
                    }
                    world.func_175718_b(2001, blockPos, Block.func_176210_f((IBlockState)state));
                    ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)new SPacketBlockChange(world, blockPos));
                }
                if (powerRanOut) {
                    IC2.platform.messagePlayer(player, "super_solar_panels.ItemUltDrill.ranOut", new Object[0]);
                }
                return true;
            }
        }
        return super.onBlockStartBreak(stack, pos, player);
    }

    public static Collection<BlockPos> getBrokenBlocks1(EntityPlayer player, RayTraceResult ray) {
        return ItemUltDrill.getBrokenBlocks1(player, ray.func_178782_a(), ray.field_178784_b);
    }

    protected static Collection<BlockPos> getBrokenBlocks1(EntityPlayer player, BlockPos pos, EnumFacing side) {
        int xMove;
        int yMove;
        assert (side != null);
        int zMove = yMove = (xMove = 1);
        switch (side.func_176740_k()) {
            case X: {
                xMove = 0;
                break;
            }
            case Y: {
                yMove = 0;
                break;
            }
            case Z: {
                zMove = 0;
            }
        }
        World world = player.field_70170_p;
        ArrayList<BlockPos> list = new ArrayList<BlockPos>(9);
        for (int x = pos.func_177958_n() - xMove; x <= pos.func_177958_n() + xMove; ++x) {
            for (int y = pos.func_177956_o() - yMove; y <= pos.func_177956_o() + yMove; ++y) {
                for (int z = pos.func_177952_p() - zMove; z <= pos.func_177952_p() + zMove; ++z) {
                    BlockPos potential = new BlockPos(x, y, z);
                    if (!ItemUltDrill.canBlockBeMined(world, potential, player, false)) continue;
                    list.add(potential);
                }
            }
        }
        return list;
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add(TextFormatting.GOLD + Localization.translate((String)"super_solar_panels.ItemUltDrill.mode", (Object[])new Object[]{TextFormatting.WHITE + Localization.translate((String)ItemUltDrill.readDrillMode((ItemStack)stack).translationName)}));
    }

    public static enum DrillMode {
        NORMAL(TextFormatting.DARK_GREEN, Configs.drillSpeed, Configs.energyCost),
        BIG_HOLES(TextFormatting.AQUA, Configs.drillSpeed1, Configs.energyCost1),
        BIG_HOLES1(TextFormatting.LIGHT_PURPLE, Configs.drillSpeed2, Configs.energyCost2);

        private static final DrillMode[] VALUES;
        public final float drillSpeed;
        public final double energyCost;
        public final TextFormatting colour;
        public final String translationName = "super_solar_panels.ItemUltDrill." + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, this.name());

        private DrillMode(TextFormatting colour, float speed, double energyCost) {
            this.energyCost = energyCost;
            this.drillSpeed = speed;
            this.colour = colour;
        }

        public static DrillMode getFromID(int ID) {
            return VALUES[ID % VALUES.length];
        }

        static {
            VALUES = DrillMode.values();
        }
    }
}

