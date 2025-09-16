package net.devtech.arrp.json.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentChanges;
import net.minecraft.util.Identifier;

public class JStackedResult extends JResult {
	public static final Codec<JStackedResult> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Identifier.CODEC.fieldOf("item").forGetter(JStackedResult::getItem),
			ComponentChanges.CODEC.fieldOf("components").forGetter(JStackedResult::getComponents),
			Codec.INT.fieldOf("count").forGetter(JStackedResult::getCount)
	).apply(instance, JStackedResult::new));
	protected Integer count;

	JStackedResult(final Identifier item) {
		super(item);
	}

	public JStackedResult(Identifier item, ComponentChanges components, Integer count) {
		super(item, components);
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
}
