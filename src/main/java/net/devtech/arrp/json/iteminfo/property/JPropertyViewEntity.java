package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "minecraft:view_entity" boolean property.
 */
public class JPropertyViewEntity extends JProperty {
	public static final Codec<JPropertyViewEntity> CODEC = Codec.unit(JPropertyViewEntity::new);

	static {
		JProperty.register("minecraft:view_entity", CODEC);
	}

	public JPropertyViewEntity() {
		super("minecraft:view_entity");
	}
}
