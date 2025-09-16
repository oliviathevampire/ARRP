package net.devtech.arrp.json.iteminfo.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.devtech.arrp.json.iteminfo.tint.JTint;

import java.util.ArrayList;
import java.util.List;

public final class JModelComposite extends JItemModel {
	public static final String TYPE = "minecraft:composite";

	private List<JItemModel> parts;

	public JModelComposite() {
		super(TYPE);
		this.parts = new ArrayList<>();
	}

	// fluent adders
	public JModelComposite model(JItemModel child) { this.parts.add(child); return this; }
	public JModelComposite models(java.util.Collection<? extends JItemModel> children) { this.parts.addAll(children); return this; }

	// getter for codec
	public List<JItemModel> getParts() { return parts; }

	public static final Codec<JModelComposite> CODEC = RecordCodecBuilder.create(i -> i.group(
			// base fields first
			JTint.CODEC.listOf().optionalFieldOf("tints").forGetter(JModelComposite::codecGetTints),
			LAZY_SELF.optionalFieldOf("fallback").forGetter(JModelComposite::codecGetFallback),
			// subtype
			LAZY_SELF.listOf().fieldOf("parts").forGetter(JModelComposite::getParts)
	).apply(i, (tints, fallback, parts) -> {
		JModelComposite m = new JModelComposite();
		m.parts = parts;
		JItemModel.applyBase(m, tints, fallback);
		return m;
	}));

	static {
		JItemModel.register(TYPE, CODEC.xmap(m -> {
			m.type = TYPE;
			return m;
		}, m -> m));
	}

	@Override public JModelComposite clone() {
		List<JItemModel> cp = new ArrayList<>();
		for (JItemModel p : parts) cp.add(p.clone());
		JModelComposite c = new JModelComposite();
		c.parts = cp;
		if (this.tints != null) for (JTint t : this.tints) c.tint(t.clone());
		if (this.fallback != null) c.fallback(this.fallback.clone());
		return c;
	}
}
