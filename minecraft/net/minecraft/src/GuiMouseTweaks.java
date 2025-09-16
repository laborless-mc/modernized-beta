package net.minecraft.src;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiMouseTweaks {
    private static Minecraft mc;

    private static GuiScreen oldGuiScreen = null;
    private static Slot oldSelectedSlot = null;
    private static Slot firstRightClickedSlot = null;
    private static boolean oldRMBDown = false;

    public GuiMouseTweaks(Minecraft minecraft) {
        mc = minecraft;
    }

    public static void onUpdateInGame() {
        GuiScreen currentScreen = mc.currentScreen;
        if (currentScreen == null) {
            oldGuiScreen = null;
            oldSelectedSlot = null;
            firstRightClickedSlot = null;
        } else {
            onUpdateInGui(currentScreen);
        }

        oldRMBDown = Mouse.isButtonDown(1);
    }

    private static void onUpdateInGui(GuiScreen currentScreen) {
        if (oldGuiScreen != currentScreen) {
            oldGuiScreen = currentScreen;
        }

        if (!(oldGuiScreen instanceof GuiContainer)) {
            return;
        }

        GuiContainer handler = (GuiContainer) oldGuiScreen;
        Slot selectedSlot = handler.getSlotUnderMouse();

        if (Mouse.isButtonDown(1)) {
            if (!oldRMBDown) {
                firstRightClickedSlot = selectedSlot;
            }

//            if (firstRightClickedSlot != null
//                && (firstRightClickedSlot != selectedSlot || oldSelectedSlot == selectedSlot)
//                && !(firstRightClickedSlot instanceof SlotCrafting)) {
//                ItemStack targetStack = firstRightClickedSlot.getStack();
//                ItemStack stackOnMouse = mc.thePlayer.inventory.getItemStack();
//
//                if (stackOnMouse != null
//                    && areStacksCompatible(stackOnMouse, targetStack)
//                    && firstRightClickedSlot.isItemValid(stackOnMouse)) {
//                    handler.clickSlot(firstRightClickedSlot, MouseButton.RIGHT, false);
//                }
//            }
        } else {
            firstRightClickedSlot = null;
        }

        if (oldSelectedSlot != selectedSlot) {
            oldSelectedSlot = selectedSlot;

            if (selectedSlot == null) {
                return;
            }

            if (firstRightClickedSlot == selectedSlot) {
                firstRightClickedSlot = null;
            }

            ItemStack targetStack = copyStack(selectedSlot.getStack());
            ItemStack stackOnMouse = copyStack(mc.thePlayer.inventory.getItemStack());

            boolean shiftIsDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);

            if (Mouse.isButtonDown(1)) {
                if (!(firstRightClickedSlot instanceof SlotCrafting)
                    && stackOnMouse != null
                    && areStacksCompatible(stackOnMouse, targetStack)
                    && selectedSlot.isItemValid(stackOnMouse)) {
                    handler.clickSlot(selectedSlot, MouseButton.RIGHT, false);
                }
            } else if (Mouse.isButtonDown(0)) {
                if (stackOnMouse != null) {
                    if (targetStack != null
                        && areStacksCompatible(stackOnMouse, targetStack)) {
                        if (shiftIsDown) {
                            handler.clickSlot(selectedSlot, MouseButton.LEFT, true);
                        } else {
                            if ((stackOnMouse.stackSize + targetStack.stackSize) <= stackOnMouse.getMaxStackSize()) {
                                handler.clickSlot(selectedSlot, MouseButton.LEFT, false);

                                if (!(firstRightClickedSlot instanceof SlotCrafting)) {
                                    handler.clickSlot(selectedSlot, MouseButton.LEFT, false);
                                }
                            }
                        }
                    }
                } else {
                    if (targetStack != null && shiftIsDown) {
                        handler.clickSlot(selectedSlot, MouseButton.LEFT, true);
                    }
                }
            }
        }
    }

    private static boolean areStacksCompatible(ItemStack a, ItemStack b) {
        return a == null || b == null || a.isItemEqual(b);
    }

    private static ItemStack copyStack(ItemStack s) {
        return s == null ? null : s.copy();
    }
}
