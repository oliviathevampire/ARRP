package net.devtech.arrp.json.iteminfo.model;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

/**
 * Represents an entry in a range dispatch model.
 */
public class JRangeEntry implements Cloneable {
    public static final Codec<JRangeEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("threshold").forGetter(JRangeEntry::getThreshold),
            JItemModel.CODEC.fieldOf("model").forGetter(JRangeEntry::getModel)
    ).apply(instance, JRangeEntry::of));

    private double threshold;
    private JItemModel model;

    public JRangeEntry() {}

    public JRangeEntry(double threshold, JItemModel model) {
        this.threshold = threshold;
        this.model = model;
    }

    public static JRangeEntry of(double threshold, JItemModel model) {
        return new JRangeEntry(threshold, model);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("threshold", threshold);
        json.add("model", model.toJson());
        return json;
    }

    // Getters and Setters
    public double getThreshold() {
        return threshold;
    }

    public JItemModel getModel() {
        return model;
    }

    @Override
    public JRangeEntry clone() {
        return new JRangeEntry(this.threshold, (this.model != null) ? this.model.clone() : null);
    }
}
