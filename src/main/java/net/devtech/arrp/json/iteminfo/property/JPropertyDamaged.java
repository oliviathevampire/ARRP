package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "minecraft:damaged" boolean property.
 */
public class JPropertyDamaged extends JProperty {
    public static final String TYPE = "minecraft:damaged";
    protected JPropertyDamaged() {
        super(TYPE);
    }

    // Static factory method
    public static JPropertyDamaged damaged() {
        return new JPropertyDamaged();
    }

    public static final Codec<JPropertyDamaged> CODEC = Codec.unit(JPropertyDamaged::new);

    static {
        JProperty.register(TYPE, CODEC);
    }
}
