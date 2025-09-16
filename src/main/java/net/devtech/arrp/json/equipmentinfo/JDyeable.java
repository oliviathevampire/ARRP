package net.devtech.arrp.json.equipmentinfo;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public record JDyeable(Optional<Integer> colorWhenUndyed) {
	public static final Codec<JDyeable> CODEC = RecordCodecBuilder.create(i -> i.group(
			Codec.INT.optionalFieldOf("color_when_undyed").forGetter(JDyeable::colorWhenUndyed)
	).apply(i, JDyeable::new));

	public JsonObject toJson() {
		JsonObject obj = new JsonObject();
		colorWhenUndyed.ifPresent(c -> obj.addProperty("color_when_undyed", c));
		return obj;
	}


}
