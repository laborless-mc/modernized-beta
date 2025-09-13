package net.minecraft.src;

import java.util.Random;

public class BlockClay extends Block {
	public BlockClay(int i1, int i2) {
		super(i1, i2, Material.clay);
	}

	public int idDropped(int i1, Random random2) {
		if(silked) { return this.blockID; }
		return Item.clay.shiftedIndex;
	}

	public void onBlockDestroyedByPlayer(EntityPlayer entityPlayer, World world1, int i2, int i3, int i4, int i5) {
		if(entityPlayer.getCurrentEquippedItem() == null) {
			silked = false;
			fortuned = false;
			smelted = false;
			return;
		}

		this.silked = entityPlayer.getCurrentEquippedItem().itemID == Item.shovelGold.shiftedIndex;
        this.fortuned = entityPlayer.getCurrentEquippedItem().itemID == Item.shovelStone.shiftedIndex;
	}

	public int quantityDropped(Random random1) {
		if(silked) { return 1; }
		if(fortuned) { return 8; }
		return 4;
	}
}
