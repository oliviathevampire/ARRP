package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "minecraft:context_dimension" property.
 */
public class JPropertyContextDimension extends JProperty {
    public static final String TYPE = "minecraft:context_dimension";
    public JPropertyContextDimension() {
        super(TYPE);
    }

    public static final Codec<JPropertyContextDimension> CODEC = Codec.unit(JPropertyContextDimension::new);

    static {
        JProperty.register(TYPE, CODEC);
    }
}
