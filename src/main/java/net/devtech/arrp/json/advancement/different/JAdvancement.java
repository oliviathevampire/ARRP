package net.devtech.arrp.json.advancement.different;

import com.google.gson.annotations.SerializedName;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JAdvancement {
    private AdvancementDisplay display;
    private Identifier parent;
    private Map<String, AdvancementCriterion<?>> criteria;
    private List<List<String>> requirements;
    private AdvancementRewards rewards;
    @SerializedName("sends_telemetry_event")
    private Boolean sendsTelemetryEvent;

    public JAdvancement parent(Identifier parent) {
        this.parent = parent;
        return this;
    }

    public JAdvancement display(AdvancementDisplay displayInfo) {
        this.display = displayInfo;
        return this;
    }

    public JAdvancement rewards(AdvancementRewards rewards) {
        this.rewards = rewards;
        return this;
    }

    public JAdvancement criteria(String name, AdvancementCriterion<?> criteria) {
        if (this.criteria == null) {
            this.criteria = new HashMap<>();
        }
        this.criteria.put(name, criteria);
        return this;
    }

    public JAdvancement requirement(List<String> requirement) {
        if (this.requirements == null) {
            this.requirements = new ArrayList<>();
        }
        this.requirements.add(requirement);
        return this;
    }

    public JAdvancement sendsTelemetryEvent() {
        this.sendsTelemetryEvent = true;
        return this;
    }
}