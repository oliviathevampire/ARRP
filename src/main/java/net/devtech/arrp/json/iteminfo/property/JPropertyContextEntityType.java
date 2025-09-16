package net.devtech.arrp.json.iteminfo.property;

import com.mojang.serialization.Codec;

/**
 * Represents the "minecraft:context_entity_type" property.
 */
public class JPropertyContextEntityType extends JProperty {
    public static final String TYPE = "minecraft:context_entity_type";
    public JPropertyContextEntityType() {
        super("minecraft:context_entity_type");
    }

    public static final Codec<JPropertyContextEntityType> CODEC = Codec.unit(JPropertyContextEntityType::new);

    static {
        JProperty.register(TYPE, CODEC);
    }
}
