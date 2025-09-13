package net.minecraft.src;

import java.util.Random;

public class BlockBookshelf extends Block {
	public BlockBookshelf(int i1, int i2) {
		super(i1, i2, Material.wood);
	}

	public int getBlockTextureFromSide(int i1) {
		return i1 <= 1 ? 4 : this.blockIndexInTexture;
	}

	public int quantityDropped(Random random1) {
		if(silked) { return 1; }
		return 3;
	}

	public int idDropped(int i1, Random random2) {
		if(silked) { return this.blockID; }
		return Item.book.shiftedIndex;
	}

	public void onBlockDestroyedByPlayer(EntityPlayer entityPlayer, World world1, int i2, int i3, int i4, int i5) {
		if(entityPlayer.getCurrentEquippedItem() == null) {
			silked = false;
			fortuned = false;
			smelted = false;
			return;
		}

		this.silked = entityPlayer.getCurrentEquippedItem().itemID == Item.axeGold.shiftedIndex;
	}
}
