package net.devtech.arrp.json.equipmentinfo;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.resources.Identifier;

public final class JLayer {
	public static final Codec<JLayer> CODEC = RecordCodecBuilder.create(i -> i.group(
			Codec.STRING.fieldOf("texture").forGetter(l -> l.texture),
			JDyeable.CODEC.optionalFieldOf("dyeable").forGetter(l -> l.dyeable),
			Codec.BOOL.optionalFieldOf("use_player_texture", false).forGetter(l -> l.usePlayerTexture)
	).apply(i, JLayer::new));

	private final String texture;
	private final Optional<JDyeable> dyeable;
	private final boolean usePlayerTexture;

	private JLayer(String texture, Optional<JDyeable> dyeable, boolean usePlayerTexture) {
		this.texture = texture;
		this.dyeable = dyeable;
		this.usePlayerTexture = usePlayerTexture;
	}

	public static Builder builder(String texture) {
		return new Builder(texture);
	}

	public static Builder builder(Identifier textureId) {
		return new Builder(textureId.toString());
	}

	public JsonObject toJson() {
		JsonObject obj = new JsonObject();
		obj.addProperty("texture", texture);
		dyeable.ifPresent(d -> obj.add("dyeable", d.toJson()));
		if (usePlayerTexture) obj.addProperty("use_player_texture", true);
		return obj;
	}

	public static final class Builder {
		private final String texture;
		private Optional<JDyeable> dyeable = Optional.empty();
		private boolean usePlayerTexture = false;

		private Builder(String texture) {
			this.texture = texture;
		}

		public Builder dyeable(Optional<Integer> colorWhenUndyed) {
			this.dyeable = Optional.of(new JDyeable(colorWhenUndyed));
			return this;
		}

		public Builder usePlayerTexture(boolean flag) {
			this.usePlayerTexture = flag;
			return this;
		}

		public JLayer build() {
			return new JLayer(texture, dyeable, usePlayerTexture);
		}
	}
}
