package net.devtech.arrp.json.blockstate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;

public class JBlockModel implements Cloneable {
	private final Identifier model;
	private Integer x;
	private Integer y;
	private Boolean uvlock;
	private Integer weight;

	// ---- Codecs ----
	public static final Codec<JBlockModel> CODEC = RecordCodecBuilder.create(inst -> inst.group(
			Identifier.CODEC.fieldOf("model").forGetter(m -> m.model),
			Codec.INT.optionalFieldOf("x").forGetter(m -> java.util.Optional.ofNullable(m.x)),
			Codec.INT.optionalFieldOf("y").forGetter(m -> java.util.Optional.ofNullable(m.y)),
			Codec.BOOL.optionalFieldOf("uvlock").forGetter(m -> java.util.Optional.ofNullable(m.uvlock)),
			Codec.INT.optionalFieldOf("weight").forGetter(m -> java.util.Optional.ofNullable(m.weight))
	).apply(inst, (modelId, x, y, uv, w) -> {
		JBlockModel m = new JBlockModel(modelId);
		x.ifPresent(m::x);
		y.ifPresent(m::y);
		uv.ifPresent(u -> { if (u) m.uvlock(); });
		w.ifPresent(m::weight);
		return m;
	}));

	public JBlockModel(Identifier model) {
		this.model = model;
	}

	@Override
	public JBlockModel clone() {
		try {
			return (JBlockModel) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}

	public JBlockModel x(int x) {
		this.x = x;
		return this;
	}

	public JBlockModel y(int y) {
		this.y = y;
		return this;
	}

	public JBlockModel uvlock() {
		this.uvlock = true;
		return this;
	}

	public JBlockModel weight(int weight) {
		this.weight = weight;
		return this;
	}

}
