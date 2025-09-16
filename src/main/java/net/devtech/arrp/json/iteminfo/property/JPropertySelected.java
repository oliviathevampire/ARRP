package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "selected" boolean property.
 */
public class JPropertySelected extends JProperty {
	// empty object -> only "property" is emitted
	public static final Codec<JPropertySelected> CODEC = Codec.unit(JPropertySelected::new);

	static {
		JProperty.register("minecraft:selected", CODEC);
	}

	protected JPropertySelected() {
		super("minecraft:selected");
	}

	// Static factory method
	public static JPropertySelected selected() {
		return new JPropertySelected();
	}
}
