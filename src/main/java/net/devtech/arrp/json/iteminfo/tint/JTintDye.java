package net.devtech.arrp.json.iteminfo.tint;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

/** Uses dye color when present; otherwise `default` RGB. */
public final class JTintDye extends JTint {
    public static final String TYPE = "minecraft:dye";

    private final int defaultRgb; // required fallback RGB (0xRRGGBB)

    public JTintDye(int defaultRgb) {
        super(TYPE);
        this.defaultRgb = defaultRgb;
    }

    public int defaultRgb() { return defaultRgb; }

    public static JTintDye of(int value) {
        return new JTintDye(value);
    }

    public static final Codec<JTintDye> CODEC = RecordCodecBuilder.create(i -> i.group(
        Codecs.RGB.fieldOf("default").forGetter(JTintDye::defaultRgb)
    ).apply(i, JTintDye::new));

    static { JTint.register(TYPE, CODEC); }

    @Override public JTintDye clone() { return new JTintDye(defaultRgb); }
}
