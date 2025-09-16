package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "crossbow/pull" numeric property.
 */
public class JPropertyCrossbowPull extends JProperty {
	public static final String TYPE = "minecraft:crossbow/pull";
	public static final Codec<JPropertyCrossbowPull> CODEC = Codec.unit(JPropertyCrossbowPull::new);

	static {
		JProperty.register(TYPE, CODEC);
	}

	protected JPropertyCrossbowPull() {
		super("minecraft:crossbow/pull");
	}

	// Static factory method
	public static JPropertyCrossbowPull crossbowPull() {
		return new JPropertyCrossbowPull();
	}
}
