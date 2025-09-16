package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "minecraft:broken" boolean property.
 */
public class JPropertyBroken extends JProperty {
    public static final String TYPE = "minecraft:broken";
    protected JPropertyBroken() {
        super(TYPE);
    }

    // Static factory method
    public static JPropertyBroken broken() {
        return new JPropertyBroken();
    }

    public static final Codec<JPropertyBroken> CODEC = Codec.unit(JPropertyBroken::new);

    static {
        JProperty.register(TYPE, CODEC);
    }
}
