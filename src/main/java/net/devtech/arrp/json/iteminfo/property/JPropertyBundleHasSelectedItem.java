package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "bundle/has_selected_item" boolean property.
 */
public class JPropertyBundleHasSelectedItem extends JProperty {
    public static final String TYPE = "minecraft:bundle/has_selected_item";
    protected JPropertyBundleHasSelectedItem() {
        super(TYPE);
    }

    // Static factory method
    public static JPropertyBundleHasSelectedItem bundleHasSelectedItem() {
        return new JPropertyBundleHasSelectedItem();
    }

    public static final Codec<JPropertyBundleHasSelectedItem> CODEC = Codec.unit(JPropertyBundleHasSelectedItem::new);

    static {
        JProperty.register(TYPE, CODEC);
    }
}
