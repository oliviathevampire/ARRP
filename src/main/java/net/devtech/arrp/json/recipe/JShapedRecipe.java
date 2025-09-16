package net.devtech.arrp.json.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

public class JShapedRecipe extends JResultRecipe {
	public static final Codec<JShapedRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			JPattern.CODEC.fieldOf("pattern").forGetter(JShapedRecipe::getPattern),
			JKeys.CODEC.fieldOf("key").forGetter(JShapedRecipe::getKey),
			JResult.CODEC.fieldOf("result").forGetter(JShapedRecipe::getResult)
	).apply(instance, JShapedRecipe::new));

	static {
		JRecipe.register(Identifier.of("crafting_shaped"), CODEC);
	}

	protected final JPattern pattern;
	protected final JKeys key;

	JShapedRecipe(JPattern pattern, JKeys keys, JResult result) {
		super(Identifier.ofVanilla("crafting_shaped"), result);

		this.pattern = pattern;
		this.key = keys;
	}

	public JPattern getPattern() {
		return pattern;
	}

	public JKeys getKey() {
		return key;
	}

	@Override
	public JShapedRecipe group(final String group) {
		return (JShapedRecipe) super.group(group);
	}

	@Override
	protected JShapedRecipe clone() {
		return (JShapedRecipe) super.clone();
	}
}
