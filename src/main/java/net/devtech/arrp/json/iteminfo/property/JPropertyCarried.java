package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "carried" string property.
 */
public class JPropertyCarried extends JProperty {
    public static final String TYPE = "minecraft:carried";
    protected JPropertyCarried() {
        super(TYPE);
    }

    // Static factory method
    public static JPropertyCarried carried() {
        return new JPropertyCarried();
    }

    public static final Codec<JPropertyCarried> CODEC = Codec.unit(JPropertyCarried::new);

    static {
        JProperty.register(TYPE, CODEC);
    }
}
