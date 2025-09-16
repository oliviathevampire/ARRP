package net.devtech.arrp.json.iteminfo.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.devtech.arrp.json.iteminfo.tint.JTint;

public final class JModelEmpty extends JItemModel {
	public static final String TYPE = "minecraft:empty";

	public static final Codec<JModelEmpty> CODEC = RecordCodecBuilder.create(i -> i.group(
			// base fields first
			JTint.CODEC.listOf().optionalFieldOf("tints").forGetter(JModelEmpty::codecGetTints),
			LAZY_SELF.optionalFieldOf("fallback").forGetter(JModelEmpty::codecGetFallback)
	).apply(i, (tints, fallback) -> {
		JModelEmpty m = new JModelEmpty();
		JItemModel.applyBase(m, tints, fallback);
		return m;
	}));

	static {
		JItemModel.register(TYPE, CODEC.xmap(m -> {
			m.type = TYPE;
			return m;
		}, m -> m));
	}

	public JModelEmpty() { super(TYPE); }

	@Override public JModelEmpty clone() {
		JModelEmpty c = new JModelEmpty();
		if (this.tints != null) for (JTint t : this.tints) c.tint(t.clone());
		if (this.fallback != null) c.fallback(this.fallback.clone());
		return c;
	}
}
