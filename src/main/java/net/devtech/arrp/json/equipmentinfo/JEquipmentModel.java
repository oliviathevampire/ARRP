package net.devtech.arrp.json.equipmentinfo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class JEquipmentModel {
	// ----- CODEC -----
	// JSON shape:
	// { "layers": { "<layerType>": [ {…layer…}, {…layer…} ], ... } }
	public static final Codec<JEquipmentModel> CODEC = RecordCodecBuilder.create(i -> i.group(
			Codec.unboundedMap(Codec.STRING, JLayer.CODEC.listOf())
					.fieldOf("layers")
					.forGetter(m -> {
						Map<String, List<JLayer>> out = new LinkedHashMap<>();
						m.layers.forEach((k, arr) -> {
							List<JLayer> list = new ArrayList<>(arr.size());
							for (var el : arr) {
								// parse each stored layer JSON back to JLayer
								list.add(JLayer.CODEC.parse(com.mojang.serialization.JsonOps.INSTANCE, el).getOrThrow());
							}
							out.put(k, list);
						});
						return out;
					})
	).apply(i, map -> {
		JEquipmentModel m = new JEquipmentModel();
		map.forEach((k, list) -> {
			var arr = new com.google.gson.JsonArray();
			for (JLayer layer : list) {
				arr.add(JLayer.CODEC.encodeStart(com.mojang.serialization.JsonOps.INSTANCE, layer).getOrThrow());
			}
			m.layers.put(k, arr);
		});
		return m;
	}));

	private Map<String, List<JsonElement>> toCodecMap() {
		Map<String, List<JsonElement>> out = new LinkedHashMap<>();
		for (var e : layers.entrySet()) {
			List<JsonElement> list = new ArrayList<>(e.getValue().size());
			for (JsonElement el : e.getValue()) list.add(el);
			out.put(e.getKey(), list);
		}
		return out;
	}

	private static JEquipmentModel fromCodecMap(Map<String, List<JsonElement>> map) {
		JEquipmentModel m = new JEquipmentModel();
		for (var e : map.entrySet()) {
			JsonArray arr = new JsonArray();
			for (JsonElement el : e.getValue()) arr.add(el);
			m.layers.put(e.getKey(), arr);
		}
		return m;
	}

	private final Map<String, JsonArray> layers = new LinkedHashMap<>();

	private JEquipmentModel() {
	}

	public static Builder builder() {
		return new Builder();
	}

	public JsonObject toJson() {
		JsonObject root = new JsonObject();
		JsonObject layersObj = new JsonObject();
		layers.forEach(layersObj::add);
		root.add("layers", layersObj);
		return root;
	}

	public static final class Builder {
		private final JEquipmentModel model = new JEquipmentModel();

		public Builder addLayer(String layerType, JLayer... layer) {
			JsonArray arr = model.layers.computeIfAbsent(layerType, k -> new JsonArray());
			for (JLayer l : layer) arr.add(l.toJson());
			return this;
		}

		public Builder addLayer(Identifier type, JLayer... layers) {
			return addLayer(type.toString(), layers);
		}

		public Builder addLayer(LayerType type, JLayer... layer) {
			if (type == LayerType.CUSTOM) {
				throw new IllegalArgumentException("Use addLayerCustom(String,…) for CUSTOM types");
			}
			return addLayer(type.asString(), layer);
		}

		public Builder addLayerCustom(String customType, JLayer... layer) {
			return addLayer(customType, layer);
		}

		public Builder addLayerCustom(Identifier customType, JLayer... layers) {
			return addLayer(customType.toString(), layers);
		}

		public JEquipmentModel build() {
			return model;
		}
	}
}
