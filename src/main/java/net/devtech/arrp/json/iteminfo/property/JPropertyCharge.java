package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "minecraft:charge" numeric property.
 */
public class JPropertyCharge extends JProperty {
    public static final String TYPE = "minecraft:charge";
    protected JPropertyCharge() {
        super(TYPE);
    }

    // Static factory method
    public static JPropertyCharge charge() {
        return new JPropertyCharge();
    }

    public static final Codec<JPropertyCharge> CODEC = Codec.unit(JPropertyCharge::new);

    static {
        JProperty.register(TYPE, CODEC);
    }
}
