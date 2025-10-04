package net.minecraft.src;

import java.util.Random;

public class BlockOre extends Block {
	public BlockOre(int i1, int i2) {
		super(i1, i2, Material.rock);
	}

	public int idDropped(int i1, Random random2) {
		if(silked) { return this.blockID; }
		if(fortuned) {
			if (this.blockID == Block.oreIron.blockID) {
				return Item.rawIron.shiftedIndex;
			}
		}
		if(smelted) {
			if(this.blockID == Block.oreIron.blockID) { return Item.ingotIron.shiftedIndex; }
			if(this.blockID == Block.oreGold.blockID) { return Item.ingotGold.shiftedIndex; }
		}
		return this.blockID == Block.oreCoal.blockID ? Item.coal.shiftedIndex : (this.blockID == Block.oreDiamond.blockID ? Item.diamond.shiftedIndex : (this.blockID == Block.oreLapis.blockID ? Item.dyePowder.shiftedIndex : this.blockID));
	}

	public int quantityDropped(Random random1) {
		int quantity;
		quantity = (this.blockID == Block.oreLapis.blockID || this.blockID == Block.oreRedstone.blockID)
				? 4 + random1.nextInt(5)
				: 1;

		if(fortuned) {
			int chance = random1.nextInt(100);

			if (chance < 20) {
				quantity *= 2;
			} else if (chance < 40) {
				quantity *= 3;
			} else if (chance < 60) {
				quantity *= 4;
			}
		}

		return quantity;
	}

	protected int damageDropped(int i1) {
		return this.blockID == Block.oreLapis.blockID ? 4 : 0;
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
		this.smelted = entityPlayer.getCurrentEquippedItem().itemID == Item.pickaxeMolten.shiftedIndex;
	}
}
