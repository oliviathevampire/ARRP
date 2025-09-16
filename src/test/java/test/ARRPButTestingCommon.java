package test;

import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.advancement.*;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.lang.JLang;
import net.devtech.arrp.json.models.JElement;
import net.devtech.arrp.json.models.JFace;
import net.devtech.arrp.json.models.JFaces;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.recipe.*;
import net.devtech.arrp.json.tags.JTag;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class ARRPButTestingCommon {
	public static final String MOD_ID = "arrp_but_testing";

	public static void main(String[] args) {
		RuntimeResourcePack pack = RuntimeResourcePack.create(Identifier.of(MOD_ID, "before_user"));
		pack.addLang(Identifier.of(MOD_ID, "en_us"), new JLang()
				.block(Identifier.ofVanilla("torch"), "Torch but it's different but it's not so it's the same")
				.item(Identifier.ofVanilla("stick"), "It's still a stick")
		);
		pack.addBlockState(new JState().add(
				new JVariant()
						.put(Map.of("facing", "east", "half", "lower", "hinge", "left", "open", "false"),
								new JBlockModel(Identifier.ofVanilla("block/spruce_door_bottom_left")))
		), Identifier.ofVanilla("acacia_door"));
		pack.addRecipe(Identifier.of(MOD_ID, "pumpkin"),
				JRecipe.shaped(
						JPattern.pattern("PPP", "P P", "PPP"),
						JKeys.keys().key("P", JIngredient.ingredient().item(Identifier.ofVanilla("pumpkin_pie"))),
						JResult.stackedResult(Identifier.ofVanilla("pumpkin"), 3)
				)
		);
		pack.addRecipe(
				Identifier.of(MOD_ID, "golden_sword"),
				JRecipe.shapeless(
						JIngredients.ingredients()
								.add(JIngredient.ingredient().item(Identifier.ofVanilla("stick")))
								.add(JIngredient.ingredient().item(Identifier.ofVanilla("gold_ingot")))
								.add(JIngredient.ingredient().item(Identifier.ofVanilla("gold_ingot")))
								.add(JIngredient.ingredient().item(Identifier.ofVanilla("gold_ingot"))),
						JResult.result(Identifier.ofVanilla("golden_sword")).components(builder -> builder
								.add(DataComponentTypes.DAMAGE, 3)
								.add(DataComponentTypes.RARITY, Rarity.RARE)
						)
				)
		);
		Text burntBreadName = Text.literal("Burnt Bread").setStyle(Style.EMPTY.withFormatting(Formatting.BOLD));
		List<Text> burntBreadLore = List.of(Text.literal("A burnt piece of bread"), Text.literal("Does nothing"));
		pack.addRecipe(Identifier.of(MOD_ID, "burnt_bread"),
				JRecipe.blasting(JIngredient.ingredient().item(Identifier.ofVanilla("bread")),
						JResult.result(Identifier.ofVanilla("coal"))
								.components(builder -> builder
										.add(DataComponentTypes.ITEM_NAME, burntBreadName)
										.add(DataComponentTypes.LORE, new LoreComponent(burntBreadLore))
								)
				).cookingTime(30)
		);
		pack.addAdvancement(
				new JAdvancement()
						.display(new JDisplay()
								.icon(JIcon.of(Identifier.ofVanilla("bread")))
								.title(Text.literal("Cooked Bread?"))
								.description(Text.literal("Burn a piece of bread. Congratulations?"))
								.frame(JDisplay.Frame.GOAL)
						)
						.criterion("burn_bread", JCriterion.inventoryChanged(
								AdvConditions.ItemPredicate.anyOf(Identifier.ofVanilla("bread"))
										.componentEquals(
												AdvComponentPreds.id(DataComponentTypes.ITEM_NAME),
												AdvComponentPreds.encodeValue(DataComponentTypes.ITEM_NAME, burntBreadName)
										).componentEquals(
												AdvComponentPreds.id(DataComponentTypes.LORE),
												AdvComponentPreds.encodeValue(DataComponentTypes.LORE, LoreComponent.DEFAULT
														.with(burntBreadLore.getFirst())
												)
										)
								)
						),
				Identifier.of(MOD_ID, "root")
		);
		pack.addRecipe(Identifier.of(MOD_ID, "bread_furnace"),
				JCookingRecipe.smelting(JIngredient.ingredient().item(Identifier.ofVanilla("wheat")), JResult.result(Identifier.ofVanilla("bread")))
						.cookingTime(100)
						.experience(50)
		);
		pack.addRecipe(Identifier.of(MOD_ID, "bread_blast"),
				JCookingRecipe.blasting(JIngredient.ingredient().item(Identifier.ofVanilla("wheat")), JResult.result(Identifier.ofVanilla("bread")))
						.cookingTime(80)
						.experience(500)
		);
		pack.addRecipe(Identifier.of(MOD_ID, "bread_smoker"),
				JCookingRecipe.smoking(JIngredient.ingredient().item(Identifier.ofVanilla("wheat")), JResult.result(Identifier.ofVanilla("bread")))
						.cookingTime(50)
						.experience(5000)
		);
		pack.addRecipe(Identifier.of(MOD_ID, "bread_campfire"),
				JCookingRecipe.campfire(JIngredient.ingredient().item(Identifier.ofVanilla("wheat")), JResult.result(Identifier.ofVanilla("bread")))
						.cookingTime(10)
		);
		pack.addRecipe(
				Identifier.of(MOD_ID, "bread_trims"),
				JRecipe.smithingTrim(
						JIngredient.ingredient().tag(Identifier.ofVanilla("trimmable_armor")),
						JIngredient.ingredient().item(Identifier.ofVanilla("bread")),
						JIngredient.ingredient().tag(Identifier.ofVanilla("trim_templates"))
				)
		);

		// Custom Model Test
		pack.addModel(new JModel().element(
				new JElement()
						.bounds(11, 0, 8, 13, 2, 10)
						.faces(JFaces.allSame(new JFace("missing").uv(0, 0, 2, 2)))
		).element(
				new JElement()
						.bounds(10, 0, 7, 12, 2, 9)
						.faces(JFaces.allSame(new JFace("missing").uv(0, 0, 2, 2)))
		), Identifier.of(MOD_ID, "block/test_model"));
		// Replace cobblestone block state
		pack.addBlockState(
				new JState()
						.add(new JVariant().put("", new JBlockModel(Identifier.of(MOD_ID, "block/test_model")))),
				Identifier.ofVanilla("cobblestone")
		);
		pack.addTag(Identifier.ofVanilla("block/mineable/pickaxe"), new JTag().add(Identifier.ofVanilla("oak_log")));

		pack.dumpDirect(Path.of("aaaaaaaa"));
	}
}