package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "minecraft:fullness" numeric property.
 */
public class JPropertyFullness extends JProperty {
	public static final String TYPE = "minecraft:bundle/fullness";
	public static final Codec<JPropertyFullness> CODEC = Codec.unit(JPropertyFullness::new);

	static {
		JProperty.register(TYPE, CODEC);
	}

	protected JPropertyFullness() {
		super(TYPE);
	}

	// Static factory method
	public static JPropertyFullness fullness() {
		return new JPropertyFullness();
	}
}
