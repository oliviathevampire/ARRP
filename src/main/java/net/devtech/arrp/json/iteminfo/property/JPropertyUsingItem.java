package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "minecraft:using_item" boolean property.
 */
public class JPropertyUsingItem extends JProperty {
	public static final String TYPE = "minecraft:using_item";
	public static final Codec<JPropertyUsingItem> CODEC = Codec.unit(JPropertyUsingItem::new);

	static {
		JProperty.register(TYPE, CODEC);
	}

	public JPropertyUsingItem() {
		super(TYPE);
	}
}
