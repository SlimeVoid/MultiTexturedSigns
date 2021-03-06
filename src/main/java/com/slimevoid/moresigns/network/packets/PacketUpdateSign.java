package com.slimevoid.moresigns.network.packets;

import com.slimevoid.library.network.PacketTileEntity;
import com.slimevoid.moresigns.core.lib.CommandLib;
import com.slimevoid.moresigns.core.lib.CoreLib;
import com.slimevoid.moresigns.tileentities.TileEntitySign;

public class PacketUpdateSign extends PacketTileEntity {

	public PacketUpdateSign() {
		super();
		this.setChannel(CoreLib.MOD_CHANNEL);
	}

	public PacketUpdateSign(TileEntitySign tileentitymtsign) {
		this();
		this.setPosition(	tileentitymtsign.xCoord,
							tileentitymtsign.yCoord,
							tileentitymtsign.zCoord,
							tileentitymtsign.getTexture());
		this.payload = tileentitymtsign.getPacketPayload();
		this.setCommand(CommandLib.SIGN_UPDATE);
	}

	public void setMtSignText(String[] signText) {
		for (int i = 0; i < signText.length; i++) {
			this.payload.setStringPayload(	i,
											signText[i]);
		}
	}

	public String[] getMtSignText() {
		String[] signText = new String[4];
		for (int i = 0; i < 4; i++) {
			signText[i] = this.payload.getStringPayload(i);
		}
		return signText;
	}

	public int getTexture() {
		return this.side;
	}

	public void setTexture(int texture) {
		this.side = texture;
	}
}
