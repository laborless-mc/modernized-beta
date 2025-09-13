package net.minecraft.src;

import java.util.Random;

public class BlockSnowBlock extends Block {
	protected BlockSnowBlock(int i1, int i2) {
		super(i1, i2, Material.builtSnow);
		this.setTickOnLoad(true);
	}

	public int idDropped(int i1, Random random2) {
		if(silked) { return this.blockID; }
		return Item.snowball.shiftedIndex;
	}

	public int quantityDropped(Random random1) {
		if(silked) { return 1; }
		return 4;
	}

	public void updateTick(World world1, int i2, int i3, int i4, Random random5) {
		if(world1.getSavedLightValue(EnumSkyBlock.Block, i2, i3, i4) > 11) {
			this.dropBlockAsItem(world1, i2, i3, i4, world1.getBlockMetadata(i2, i3, i4));
			world1.setBlockWithNotify(i2, i3, i4, 0);
		}

	}

	public void onBlockDestroyedByPlayer(EntityPlayer entityPlayer, World world1, int i2, int i3, int i4, int i5) {
		if(entityPlayer.getCurrentEquippedItem() == null) {
			silked = false;
			fortuned = false;
			smelted = false;
			return;
		}

		this.silked = entityPlayer.getCurrentEquippedItem().itemID == Item.shovelGold.shiftedIndex;
	}
}
