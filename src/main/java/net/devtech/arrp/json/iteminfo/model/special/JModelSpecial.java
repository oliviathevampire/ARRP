package net.devtech.arrp.json.iteminfo.model.special;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.devtech.arrp.json.iteminfo.model.JItemModel;

/**
 * Abstract base class for special model types "minecraft:special".
 */
public class JModelSpecial extends JItemModel {
    public static final String TYPE = "minecraft:special";
    public static final Codec<JModelSpecial> CODEC = RecordCodecBuilder.create(function -> function.group(
            Codec.STRING.fieldOf("base").forGetter(JModelSpecial::getBase),
            JItemModel.CODEC.fieldOf("model").forGetter(JModelSpecial::getModel),
            Codec.STRING.fieldOf("special_type").forGetter(JModelSpecial::getSpecialType)
    ).apply(function, JModelSpecial::new));

    static {
        JItemModel.register(TYPE, CODEC.xmap(m -> {
            m.type = TYPE;
            return m;
        }, m -> m));
    }

    private String base;  // Optional base model
    private JItemModel model; // Nested model
    private String specialType;

    public JModelSpecial() {
        super(TYPE);
    }

    protected JModelSpecial(String specialType) {
        super(TYPE);
        this.specialType = specialType;
    }

    protected JModelSpecial(String base, JItemModel model, String specialType) {
        super(TYPE);
        this.base = base;
        this.model = model;
        this.specialType = specialType;
    }

    public static JModelSpecial specialModel(String base, JItemModel special) {
        return new JModelSpecial().base(base).model(special);
    }

    public static JModelShield shield() {
        return new JModelShield();
    }

    public String getSpecialType() {
        return specialType;
    }

    public static JModelHead head() {
        return new JModelHead();
    }

    // Fluent methods
    public JModelSpecial base(String base) {
        this.base = base;
        return this;
    }

    public JModelSpecial model(JItemModel model) {
        this.model = model;
        return this;
    }

    // Getters
    public String getBase() {
        return base;
    }

    public JItemModel getModel() {
        return model;
    }

    // Implement toJson method
    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", this.getType());
        if (base != null) {
            json.addProperty("base", base);
        }
        if (model != null) {
            json.add("model", model.toJson());
        }
        return json;
    }

    @Override
    public JModelSpecial clone() {
        JModelSpecial cloned = new JModelSpecial();
        cloned.base = this.base;
        cloned.model = (this.model != null) ? this.model.clone() : null;
        return cloned;
    }
}
