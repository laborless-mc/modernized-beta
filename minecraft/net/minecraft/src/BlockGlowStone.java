package net.minecraft.src;

import java.util.Random;

public class BlockGlowStone extends Block {
	public BlockGlowStone(int i1, int i2, Material material3) {
		super(i1, i2, material3);
	}

	public int quantityDropped(Random random1) {
		if(silked) { return 1; }
		if(fortuned) { return (2 + random1.nextInt(3)) * 2; }
		return 2 + random1.nextInt(3);
	}

	public int idDropped(int i1, Random random2) {
		if(silked) { return this.blockID; }
		return Item.lightStoneDust.shiftedIndex;
	}

	public void onBlockDestroyedByPlayer(EntityPlayer entityPlayer, World world1, int i2, int i3, int i4, int i5) {
		if(entityPlayer.getCurrentEquippedItem() == null) {
			silked = false;
			fortuned = false;
			smelted = false;
			return;
		}

		this.silked = entityPlayer.getCurrentEquippedItem().itemID == Item.pickaxeGold.shiftedIndex;
		this.fortuned = entityPlayer.getCurrentEquippedItem().itemID == Item.pickaxeStone.shiftedIndex;
	}
}
