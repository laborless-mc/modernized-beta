package net.minecraft.src;

import java.util.Random;

public class BlockGlass extends BlockBreakable {
	public BlockGlass(int i1, int i2, Material material3, boolean z4) {
		super(i1, i2, material3, z4);
	}

	public int getRenderBlockPass() {
		return 0;
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

	public int quantityDropped(Random random1) {
		if(silked) { return 1; }
		return 0;
	}
}
