package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "minecraft:extended_view" boolean property.
 */
public class JPropertyExtendedView extends JProperty {
    public static final String TYPE = "minecraft:extended_view";
    public JPropertyExtendedView() {
        super(TYPE);
    }

    // Static factory method
    public static JPropertyExtendedView extendedView() {
        return new JPropertyExtendedView();
    }

    public static final Codec<JPropertyExtendedView> CODEC = Codec.unit(JPropertyExtendedView::new);

    static {
        JProperty.register(TYPE, CODEC);
    }
}
