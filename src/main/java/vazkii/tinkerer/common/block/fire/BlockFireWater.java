package vazkii.tinkerer.common.block.fire;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import vazkii.tinkerer.common.lib.LibBlockNames;
import vazkii.tinkerer.common.registry.ThaumicTinkererRecipe;
import vazkii.tinkerer.common.research.IRegisterableResearch;

import java.util.HashMap;

public class BlockFireWater extends BlockFireBase {
	public BlockFireWater() {
		super();
	}

	@Override
	public String getBlockName() {
		return LibBlockNames.BLOCK_FIRE_WATER;
	}

	@Override
	public IRegisterableResearch getResearchItem() {
		return null;
	}

	@Override
	public ThaumicTinkererRecipe getRecipeItem() {
		return null;
	}

	@Override
	public HashMap<Block, Block> getBlockTransformation() {
		HashMap<Block, Block> result = new HashMap<Block, Block>();
		result.put(Blocks.sand, Blocks.ice);
		result.put(Blocks.netherrack, Blocks.snow);
		result.put(Blocks.soul_sand, Blocks.ice);
		result.put(Blocks.glowstone, Blocks.ice);
		result.put(Blocks.lava, Blocks.obsidian);
		result.put(Blocks.flowing_lava, Blocks.obsidian);
		return result;
	}
}