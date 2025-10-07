package net.minecraft.src;

public class RecipesCrafting {
	public void addRecipes(CraftingManager craftingManager1) {
		craftingManager1.addRecipe(new ItemStack(Block.chest), new Object[]{"###", "# #", "###", '#', Block.planks});
		craftingManager1.addRecipe(new ItemStack(Block.stoneOvenIdle), new Object[]{"###", "#X#", "###", '#', Block.cobblestone, 'X', Item.coal});
		craftingManager1.addRecipe(new ItemStack(Block.workbench), new Object[]{"##", "##", '#', Block.planks});
		craftingManager1.addRecipe(new ItemStack(Block.sandStone), new Object[]{"##", "##", '#', Block.sand});
	}
}
