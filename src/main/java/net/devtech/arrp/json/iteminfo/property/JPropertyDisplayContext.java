package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "display_context" string property.
 */
public class JPropertyDisplayContext extends JProperty {
	public static final String TYPE = "minecraft:display_context";
	public static final Codec<JPropertyDisplayContext> CODEC = Codec.unit(JPropertyDisplayContext::new);

	static {
		JProperty.register(TYPE, CODEC);
	}

	protected JPropertyDisplayContext() {
		super(TYPE);
	}

	// Static factory method
	public static JPropertyDisplayContext displayContext() {
		return new JPropertyDisplayContext();
	}
}
