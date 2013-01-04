package eurymachus.mts.client.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import eurymachus.mts.client.guis.GuiEditMTSign;
import eurymachus.mts.tileentities.TileEntityMTSign;
import eurysmods.api.IPacketHandling;
import eurysmods.network.packets.core.PacketTileEntity;
import eurysmods.network.packets.core.PacketUpdate;

public class ClientPacketHandler implements IPacketHandling {
	@Override
	public void handleTileEntityPacket(PacketTileEntity packet, EntityPlayer entityplayer, World world) {
		if (packet != null && packet.targetExists(world)) {
			TileEntity tileentity = packet.getTileEntity(world);
			if ((tileentity != null) && (tileentity instanceof TileEntityMTSign)) {
				TileEntityMTSign tileentitymtsign = (TileEntityMTSign) tileentity;
				tileentitymtsign.handleUpdatePacket(world, packet);
			}
		}
	}

	@Override
	public void handleGuiPacket(PacketUpdate packet, EntityPlayer entityplayer, World world) {
		int x = packet.xPosition;
		int y = packet.yPosition;
		int z = packet.zPosition;
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityMTSign) {
			TileEntityMTSign tileentitymtsign = (TileEntityMTSign) tileentity;
			ModLoader
					.openGUI(entityplayer, new GuiEditMTSign(tileentitymtsign));
		}
	}

	@Override
	public void handlePacket(PacketUpdate packet, EntityPlayer entityplayer, World world) {
	}
}