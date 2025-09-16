package net.devtech.arrp.json.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Optional;

public final class JIcon {
	public static final Codec<JIcon> CODEC = RecordCodecBuilder.create(i -> i.group(
			Identifier.CODEC.fieldOf("item").forGetter(ic -> ic.item),
			Codec.INT.optionalFieldOf("count").forGetter(ic -> Optional.ofNullable(ic.count)),
			ComponentChanges.CODEC.optionalFieldOf("components").forGetter(ic -> Optional.ofNullable(ic.components))
	).apply(i, (item, count, comps) -> {
		JIcon ic = new JIcon();
		ic.item = item;
		ic.count = count.orElse(null);
		ic.components = comps.orElse(null);
		return ic;
	}));

	public Identifier item;
	public Integer count;                 // optional
	public ComponentChanges components; // optional (1.20.5+)

	// builder sugar
	public static JIcon of(Identifier itemId) {
		JIcon ic = new JIcon();
		ic.item = itemId;
		return ic;
	}

	public static JIcon of(Item item) {
		JIcon ic = new JIcon();
		ic.item = Registries.ITEM.getId(item);
		return ic;
	}

	public JIcon count(int c) {
		this.count = c;
		return this;
	}

	public JIcon components(ComponentChanges p) {
		this.components = p;
		return this;
	}
}
