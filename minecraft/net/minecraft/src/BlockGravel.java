package net.minecraft.src;

import java.util.Random;

public class BlockGravel extends BlockSand {
	public BlockGravel(int i1, int i2) {
		super(i1, i2);
	}

	public int idDropped(int i1, Random random2) {
		if(silked) { return this.blockID; }
		if(fortuned) { return Item.flint.shiftedIndex; }
		return random2.nextInt(10) == 0 ? Item.flint.shiftedIndex : this.blockID;
	}

	public int quantityDropped(Random random1) {
		if(fortuned) { return 2; }
		return 1;
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
}
