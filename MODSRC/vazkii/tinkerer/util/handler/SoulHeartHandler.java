/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the ThaumicTinkerer Mod.
 * 
 * ThaumicTinkerer is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * ThaumicTinkerer is a Derivative Work on Thaumcraft 3.
 * Thaumcraft 3 � Azanor 2012
 * (http://www.minecraftforum.net/topic/1585216-)
 * 
 * File Created @ [2 Jul 2013, 16:45:54 (GMT)]
 */
package vazkii.tinkerer.util.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.tinkerer.lib.LibMisc;
import vazkii.tinkerer.network.PacketManager;
import vazkii.tinkerer.network.packet.PacketSoulHeartSync;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoulHeartHandler {

	@SideOnly(Side.CLIENT)
	public static int clientPlayerHP = 0;
	
	private static final String COMPOUND = LibMisc.MOD_ID;
	private static final String TAG_HP = "soulHearts";
	private static final int MAX_HP = 20;
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public static void renderHealthBar(RenderGameOverlayEvent event) {
		// TODO
	}
	
	@SideOnly(Side.CLIENT)
	private static void renderHeart(int x, int y, boolean full) {
		// TODO
	}
	
	@ForgeSubscribe
	public static void onPlayerDamage(LivingHurtEvent event) {
		// TODO
	}
	
	public static boolean addHP(EntityPlayer player, int hp) {
		int current = getHP(player);
		if(current >= MAX_HP)
			return false;
		
		setHP(player, Math.max(MAX_HP, current + hp));
		return true;
	}
	
	// Returns overflow damage
	public static int removeHP(EntityPlayer player, int hp) { 
		int current = getHP(player);
		int newHp = current - hp;
		setHP(player, Math.max(0, newHp));
		
		return Math.min(0, newHp);
	}
	
	public static void setHP(EntityPlayer player, int hp) {
		NBTTagCompound cmp = getCompoundToSet(player);
		cmp.setInteger(TAG_HP, hp);
	}
	
	public static int getHP(EntityPlayer player) {
		NBTTagCompound cmp = getCompoundToSet(player);
		return cmp.hasKey(TAG_HP) ? cmp.getInteger(TAG_HP) : 0;
	}
	
	private static NBTTagCompound getCompoundToSet(EntityPlayer player) {
		NBTTagCompound cmp = player.getEntityData();
		if(!cmp.hasKey(COMPOUND))
			cmp.setCompoundTag(COMPOUND, new NBTTagCompound());
		
		return cmp.getCompoundTag(COMPOUND);
	}
	
	public static void updateClient(EntityPlayer player) {
		PacketManager.sendPacketToClient((Player) player, new PacketSoulHeartSync(getHP(player)));
	}
}