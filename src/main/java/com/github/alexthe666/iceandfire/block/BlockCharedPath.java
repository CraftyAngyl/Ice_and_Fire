package com.github.alexthe666.iceandfire.block;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.core.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrassPath;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCharedPath extends BlockGrassPath{
    boolean isFire;

    public BlockCharedPath(boolean isFire) {
        super();
        this.isFire = isFire;
        this.setUnlocalizedName(isFire ? "iceandfire.charedGrassPath" : "iceandfire.frozenGrassPath");
        this.setHarvestLevel("shovel", 0);
        this.setHardness(0.6F);
        this.setSoundType(isFire ? SoundType.GROUND : SoundType.GLASS);
        this.setCreativeTab(IceAndFire.TAB);
        GameRegistry.registerBlock(this, isFire ? "chared_grass_path" : "frozen_grass_path");
        if(!isFire){
            this.slipperiness = 0.98F;
        }
    }

    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return isFire ? ModBlocks.charedDirt.getItemDropped(Blocks.DIRT.getDefaultState(), rand, fortune) : ModBlocks.frozenDirt.getItemDropped(Blocks.DIRT.getDefaultState(), rand, fortune);
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
        super.neighborChanged(state, worldIn, pos, blockIn);

        if (worldIn.getBlockState(pos.up()).getMaterial().isSolid()) {
            worldIn.setBlockState(pos, isFire ? ModBlocks.charedDirt.getDefaultState() : ModBlocks.frozenDirt.getDefaultState());
        }
    }

}
