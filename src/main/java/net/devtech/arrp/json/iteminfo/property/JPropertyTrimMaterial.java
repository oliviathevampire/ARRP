package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "trim_material" string property.
 */
public class JPropertyTrimMaterial extends JProperty {
	public static final Codec<JPropertyTrimMaterial> CODEC = Codec.unit(JPropertyTrimMaterial::new);

	static {
		JProperty.register("minecraft:trim_material", CODEC);
	}

	protected JPropertyTrimMaterial() {
		super("minecraft:trim_material");
	}

	public static JPropertyTrimMaterial trimMaterial() {
		return new JPropertyTrimMaterial();
	}
}
