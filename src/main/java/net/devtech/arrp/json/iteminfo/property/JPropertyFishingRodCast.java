package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "fishing_rod/cast" boolean property.
 */
public class JPropertyFishingRodCast extends JProperty {
    public static final String TYPE = "minecraft:fishing_rod/cast";
    protected JPropertyFishingRodCast() {
        super(TYPE);
    }

    // Static factory method
    public static JPropertyFishingRodCast fishingRodCast() {
        return new JPropertyFishingRodCast();
    }

    public static final Codec<JPropertyFishingRodCast> CODEC = Codec.unit(JPropertyFishingRodCast::new);

    static {
        JProperty.register(TYPE, CODEC);
    }
}
