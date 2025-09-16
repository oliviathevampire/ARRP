package net.devtech.arrp.json.iteminfo.model.special;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.devtech.arrp.json.iteminfo.model.JItemModel;

import java.util.Optional;

/**
 * Represents the "minecraft:shield" special model type.
 */
public class JModelShield extends JModelSpecial {
    public static final String TYPE = "minecraft:shield";

    // { "pattern": "<ns:path>", "base_texture": "<ns:path>" } — both optional
    public static final Codec<JModelShield> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.optionalFieldOf("pattern").forGetter(s -> Optional.ofNullable(s.pattern)),
            Codec.STRING.optionalFieldOf("base_texture").forGetter(s -> Optional.ofNullable(s.baseTexture))
    ).apply(i, (opPattern, opBase) -> {
        JModelShield s = new JModelShield();
        opPattern.ifPresent(s::pattern);
        opBase.ifPresent(s::baseTexture);
        return s;
    }));

    static {
        JItemModel.register(TYPE, CODEC.xmap(m -> { m.type = TYPE; return m; }, m -> m));
    }

    private String pattern;  // Pattern texture
    private String baseTexture; // Base texture

    protected JModelShield() {
        super("minecraft:shield");
    }

    // Fluent methods
    public JModelShield pattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public JModelShield baseTexture(String baseTexture) {
        this.baseTexture = baseTexture;
        return this;
    }

    // Getters
    public String getPattern() {
        return pattern;
    }

    public String getBaseTexture() {
        return baseTexture;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", this.getSpecialType());
        return json;
    }

    @Override
    public JModelShield clone() {
        JModelShield cloned = new JModelShield();
        cloned.base(this.getBase());
        cloned.model((this.getModel() != null) ? this.getModel().clone() : null);
        cloned.pattern = this.pattern;
        cloned.baseTexture = this.baseTexture;
        return cloned;
    }
}
