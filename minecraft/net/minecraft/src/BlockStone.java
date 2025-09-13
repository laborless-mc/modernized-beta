package net.minecraft.src;

import java.util.Random;

public class BlockStone extends Block {
	public BlockStone(int i1, int i2) {
		super(i1, i2, Material.rock);
	}

	public int idDropped(int i1, Random random2) {
		if(silked) { return this.blockID; }
		return Block.cobblestone.blockID;
	}

	public void onBlockDestroyedByPlayer(EntityPlayer entityPlayer, World world1, int i2, int i3, int i4, int i5) {
		if(entityPlayer.getCurrentEquippedItem() == null) {
			silked = false;
			fortuned = false;
			smelted = false;
			return;
		}

		this.silked = entityPlayer.getCurrentEquippedItem().itemID == Item.pickaxeGold.shiftedIndex;
	}
}
