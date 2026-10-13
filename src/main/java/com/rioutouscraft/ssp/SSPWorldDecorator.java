/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.block.state.pattern.BlockMatcher
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.IChunkGenerator
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraftforge.fml.common.IWorldGenerator
 */
package com.rioutouscraft.ssp;

import com.rioutouscraft.ssp.BlocksRegister;
import com.rioutouscraft.ssp.ConfigHandler;
import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class SSPWorldDecorator
implements IWorldGenerator {
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 0: {
                this.runGenerator(BlocksRegister.iridiumOre.getDefaultState(), random.nextInt(ConfigHandler.iridiumOnChunk) + 1, 3, 12, 26, (Predicate<IBlockState>)BlockMatcher.forBlock((Block)Blocks.STONE), world, random, chunkX, chunkZ);
                this.runGenerator(BlocksRegister.platiumOre.getDefaultState(), random.nextInt(ConfigHandler.iridiumOnChunk) + 1, 3, 12, 26, (Predicate<IBlockState>)BlockMatcher.forBlock((Block)Blocks.STONE), world, random, chunkX, chunkZ);
            }
        }
    }

    private void runGenerator(IBlockState blockToGen, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight, Predicate<IBlockState> blockToReplace, World world, Random rand, int chunk_X, int chunk_Z) {
        if (minHeight < 0 || maxHeight > 60 || minHeight > maxHeight) {
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
        }
        WorldGenMinable generator = new WorldGenMinable(blockToGen, blockAmount, blockToReplace);
        int heightdiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; ++i) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightdiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}

