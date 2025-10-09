package net.minecraft.src;

import java.util.Random;

public class BlockCactus extends Block {
	protected BlockCactus(int i1, int i2) {
		super(i1, i2, Material.cactus);
		this.setTickOnLoad(true);
	}

	public void updateTick(World world1, int i2, int i3, int i4, Random random5) {
		if (world1.isAirBlock(i2, i3 + 1, i4)) {
			int i6;
			for (i6 = 1; world1.getBlockId(i2, i3 - i6, i4) == this.blockID; ++i6) {
			}

			if (i6 < 3) {
				int i7 = world1.getBlockMetadata(i2, i3, i4);
				if (i7 == 15) {
					if(i6 <= 1 && random5.nextInt(10) == 0) {
						world1.setBlockWithNotify(i2, i3 + 1, i4, Block.cactusFlower.blockID);
						world1.setBlockMetadataWithNotify(i2, i3, i4, 0);
					} else if(i6 == 2 && random5.nextInt(4) == 0) {
						world1.setBlockWithNotify(i2, i3 + 1, i4, Block.cactusFlower.blockID);
						world1.setBlockMetadataWithNotify(i2, i3, i4, 0);
					} else {
						world1.setBlockWithNotify(i2, i3 + 1, i4, this.blockID);
						world1.setBlockMetadataWithNotify(i2, i3, i4, 0);
					}
				} else {
					world1.setBlockMetadataWithNotify(i2, i3, i4, i7 + 1);
				}
			}
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world1, int i2, int i3, int i4) {
		float f5 = 0.0625F;
		return AxisAlignedBB.getBoundingBoxFromPool((double)((float)i2 + f5), (double)i3, (double)((float)i4 + f5), (double)((float)(i2 + 1) - f5), (double)((float)(i3 + 1) - f5), (double)((float)(i4 + 1) - f5));
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world1, int i2, int i3, int i4) {
		float f5 = 0.0625F;
		return AxisAlignedBB.getBoundingBoxFromPool((double)((float)i2 + f5), (double)i3, (double)((float)i4 + f5), (double)((float)(i2 + 1) - f5), (double)(i3 + 1), (double)((float)(i4 + 1) - f5));
	}

	public int getBlockTextureFromSide(int i1) {
		return i1 == 1 ? this.blockIndexInTexture - 1 : (i1 == 0 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture);
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public int getRenderType() {
		return 13;
	}

	public boolean canPlaceBlockAt(World world1, int i2, int i3, int i4) {
		return !super.canPlaceBlockAt(world1, i2, i3, i4) ? false : this.canBlockStay(world1, i2, i3, i4);
	}

	public void onNeighborBlockChange(World world1, int i2, int i3, int i4, int i5) {
		if(!this.canBlockStay(world1, i2, i3, i4)) {
			this.dropBlockAsItem(world1, i2, i3, i4, world1.getBlockMetadata(i2, i3, i4));
			world1.setBlockWithNotify(i2, i3, i4, 0);
		}

	}

	public boolean canBlockStay(World world1, int i2, int i3, int i4) {
		if(world1.getBlockMaterial(i2 - 1, i3, i4).isSolid()) {
			return false;
		} else if(world1.getBlockMaterial(i2 + 1, i3, i4).isSolid()) {
			return false;
		} else if(world1.getBlockMaterial(i2, i3, i4 - 1).isSolid()) {
			return false;
		} else if(world1.getBlockMaterial(i2, i3, i4 + 1).isSolid()) {
			return false;
		} else {
			int i5 = world1.getBlockId(i2, i3 - 1, i4);
			return i5 == Block.cactus.blockID || i5 == Block.sand.blockID;
		}
	}

	public void onEntityCollidedWithBlock(World world1, int i2, int i3, int i4, Entity entity5) {
		entity5.attackEntityFrom((Entity)null, 1);
	}

	public void onBlockDestroyedByPlayer(EntityPlayer entityPlayer, World world1, int i2, int i3, int i4, int i5) {
		if(entityPlayer.getCurrentEquippedItem() == null) {
			silked = false;
			fortuned = false;
			smelted = false;
			return;
		}

		this.smelted = entityPlayer.getCurrentEquippedItem().itemID == Item.axeMolten.shiftedIndex;
	}

	public int idDropped(int i1, Random random2) {
		return Block.cactus.blockID;
	}

	public int quantityDropped(Random random1) {
		if(smelted) { return 0; }
		return 1;
	}

	public void harvestBlock(World world1, EntityPlayer entityPlayer2, int i3, int i4, int i5, int i6) {
		entityPlayer2.addStat(StatList.mineBlockStatArray[this.blockID], 1);
		if(smelted) {
			this.dropBlockAsItem_do(world1, i3, i4, i5, new ItemStack(Item.dyePowder, 1, 2));
		}
		else {
			this.dropBlockAsItem(world1, i3, i4, i5, i6);
		}
	}
}
