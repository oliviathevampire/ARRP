package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.impl.RuntimeResourcePackImpl;
import net.devtech.arrp.json.JsonBytes;
import net.devtech.arrp.json.ResourceLocationTypeAdapter;
import net.devtech.arrp.json.blockstate.Connectables;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.equipmentinfo.JEquipmentModel;
import net.devtech.arrp.json.equipmentinfo.JLayer;
import net.devtech.arrp.json.equipmentinfo.LayerType;
import net.devtech.arrp.json.iteminfo.JItemInfo;
import net.devtech.arrp.json.iteminfo.model.*;
import net.devtech.arrp.json.iteminfo.property.*;
import net.devtech.arrp.json.iteminfo.tint.JTint;
import net.devtech.arrp.json.iteminfo.tint.JTintConstant;
import net.devtech.arrp.json.iteminfo.tint.JTintDye;
import net.devtech.arrp.json.iteminfo.tint.JTintTeam;
import net.devtech.arrp.json.lang.JLang;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.devtech.arrp.json.blockstate.JState.*;
import static net.devtech.arrp.json.loot.JLootTable.*;
import static net.devtech.arrp.json.models.JModel.*;

public class RRPPreTest {

	private static Identifier id(String path) {
		return Identifier.ofVanilla(path);
	}

	private static Identifier id(String namespace, String path) {
		return Identifier.of(namespace, path);
	}

	public static void main(String[] args) {
		RuntimeResourcePack pack = RuntimeResourcePack.create("test:test");
		pack.addLang(Identifier.tryParse("aaaa:aaaa"), new JLang().entry("aaaa", "bbbbb"));
		pack.addLang(Identifier.tryParse("modid:lang/en_us"), new JLang().entry("item.custom", "Custom Item"));
		pack.addLang(Identifier.tryParse("modid:lang/es_es"), new JLang().entry("item.custom", "Artículo Personalizado"));
		pack.addItemModelInfo(JItemInfo.item().model(JItemModel.select()
				.property(JPropertyComponent.component("minecraft:item_name"))
				.addCase(JSelectCase.of("Diamond", JModelBasic.of("minecraft:item/diamond")))
				.addCase(JSelectCase.of("Netherite Ingot", JModelBasic.of("minecraft:item/netherite_ingot")))
				.addCase(JSelectCase.of("Dirt", JModelBasic.of("minecraft:block/dirt")))
				.addCase(JSelectCase.of("Coal", JItemModel.rangeDispatch()
						.property(JPropertyCount.count())
						.entry(JRangeEntry.of(10, JModelBasic.of("minecraft:item/charcoal")))
						.fallback(JModelBasic.of("minecraft:item/coal"))
				))
				.fallback(JModelBasic.of("minecraft:block/stone"))), Identifier.of("test", "test_item"));
		pack.addEquipmentModel(JEquipmentModel.builder().addLayer(LayerType.HUMANOID, JLayer.builder("test:a/test").build()).build(), Identifier.of("test", "test_armor"));
		pack.dumpDirect(Path.of("aaaa"));

		JState iron_block = state(variant(JState.model(id("block/iron_block"))));
		JState oak_fence = state(multipart(JState.model(id("block/oak_fence_post"))),
				multipart(JState.model(id("block/oak_fence_side")).uvlock()).when(when().isTrue("north")),
				multipart(JState.model(id("block/oak_fence_side")).y(90).uvlock()).when(when().isTrue("east")),
				multipart(JState.model(id("block/oak_fence_side")).y(180).uvlock()).when(when().isTrue("south")),
				multipart(JState.model(id("block/oak_fence_side")).y(270).uvlock()).when(when().isTrue("west")));

		JModel model = JModel.model().textures(JModel.textures().var("all", "block/bamboo_stalk").particle("block/bamboo_stalk"))
				.element(element().bounds(7, 0, 7, 9, 16, 9)
						.faces(faces().down(face("all").cullface(Direction.DOWN).uv(13, 4, 15, 6))
								.up(face("all").cullface(Direction.UP).uv(13, 0, 15, 2))
								.north(face("all").uv(9, 0, 11, 16))
								.south(face("all").uv(9, 0, 11, 16))
								.west(face("all").uv(9, 0, 11, 16))
								.east(face("all").uv(9, 0, 11, 16))
						)
				);


		Gson gson = new GsonBuilder()
				.registerTypeAdapter(JTextures.class, new JTextures.Serializer())
				.registerTypeAdapter(Identifier.class, new ResourceLocationTypeAdapter())
				.setPrettyPrinting()
				.create();

		JLang lang = JLang.lang().allPotionOf(Identifier.tryParse("mod_id", "potion_id"), "Example");

		System.out.println(RuntimeResourcePackImpl.GSON.toJson(loot("minecraft:block").pool(pool().rolls(1)
				.entry(entry().type("minecraft:item").name("minecraft:diamond"))
				.condition(predicate("minecraft:survives_explosion")))));
		System.out.println(gson.toJson(iron_block));
		System.out.println(gson.toJson(oak_fence));
		System.out.println(gson.toJson(model));

		System.out.println(gson.toJson(lang));

		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(JModelBasic.of("minecraft:item/template_spawn_egg")
				.tint(new JTintConstant(-278045))
				.tint(new JTintConstant(-5886604))
		)));


		JItemInfo itemInfo = JItemInfo.item().model(
				JItemModel.model("minecraft:item/template_spawn_egg")
						.tints(JTint.constant(-278045), JTint.constant(-5886604))
		).handAnimationOnSwap(true);
		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, itemInfo));

		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(JItemModel.model("minecraft:item/bamboo"))));

		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(JItemModel.rangeDispatch()
				.property(JPropertyUseCycle.useCycle().period(10f))
				.scale(0.1)
				.entry(JRangeEntry.of(0.25, JModelBasic.of("minecraft:item/brush_brushing_0")))
				.entry(JRangeEntry.of(0.5, JModelBasic.of("minecraft:item/brush_brushing_1")))
				.entry(JRangeEntry.of(0.75, JModelBasic.of("minecraft:item/brush_brushing_2")))
				.fallback(JModelBasic.of("minecraft:item/brush"))
		)));

		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(JItemModel.condition()
				.property(new JPropertyUsingItem())
				.onTrue(JModelBasic.of("minecraft:item/using_model"))
				.onFalse(JModelBasic.of("minecraft:item/default_model"))
		)));

		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(JItemModel.composite()
				.model(JModelBasic.of("minecraft:item/part1"))
				.model(JModelBasic.of("minecraft:item/part2"))
		)));

		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(JItemModel.rangeDispatch()
				.property(JPropertyDamage.of(true))
				.entry(JRangeEntry.of(0.25, JModelBasic.of("minecraft:item/damage_low")))
				.entry(JRangeEntry.of(0.5, JModelBasic.of("minecraft:item/damage_medium")))
				.entry(JRangeEntry.of(0.75, JModelBasic.of("minecraft:item/damage_high")))
				.fallback(JModelBasic.of("minecraft:item/damage_full"))
		)));

//		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(JModelSpecial.head()
//				.kind(SkullBlock.Type.DRAGON)
//				.animation(0.5f)
//		).handAnimationOnSwap(false)));

		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(JItemModel.condition()
				.property(new JPropertyKeybindDown("key.sneak"))
				.onTrue(JModelBasic.of("minecraft:item/sneaking_model"))
				.onFalse(JModelBasic.of("minecraft:item/normal_model"))
		)));

		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(JModelBasic
				.of("minecraft:item/team_colored_item")
				.tint(JTintTeam.of(-1))
		)));


		// Create range dispatch entries
		List<JRangeEntry> entries = new ArrayList<>();
		for (int i = 0; i <= 31; i++) {
			entries.add(new JRangeEntry(
					i / 32.0, // normalized threshold
					JItemModel.model("minecraft:item/compass_" + (i < 10 ? "0" : "") + i)
			));
		}

		// Create range dispatch for "on_true"
		JModelRangeDispatch onTrueDispatch = JItemModel.rangeDispatch()
				.property(new JPropertyCompass().target("lodestone"))
				.scale(32.0)
				.entries(entries);

		// Create range dispatch for fallback
		JModelRangeDispatch fallbackDispatch = JItemModel.rangeDispatch()
				.property(new JPropertyCompass().target("none"))
				.scale(32.0)
				.entries(entries);

		// Create select model for "on_false"
		JItemModel onFalseSelect = JItemModel.select()
				.property(new JPropertyContextDimension())
				.addCase(JSelectCase.of("minecraft:overworld", JItemModel.rangeDispatch()
						.property(new JPropertyCompass().target("spawn"))
						.scale(32.0)
						.entries(entries)
				)).fallback(fallbackDispatch);

		// Create the full condition model
		JModelCondition compassModel = JItemModel.condition()
				.property(JPropertyHasComponent.of("minecraft:lodestone_tracker"))
				.onTrue(onTrueDispatch)
				.onFalse(onFalseSelect);

		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(compassModel)));

		// Create the condition property
		JPropertyUsingItem usingItemProperty = new JPropertyUsingItem();

		// Create the nested shield model
//		JItemModel shieldNestedModel = JModelSpecial.shield();
//
//		// Create the on_false model (not blocking)
//		JModelSpecial onFalseModel = JModelSpecial.specialModel("minecraft:item/shield", shieldNestedModel);
//
//		// Create the on_true model (blocking)
//		JModelSpecial onTrueModel = JModelSpecial.specialModel("minecraft:item/shield_blocking", shieldNestedModel);
//
//		// Create the condition model
//		JModelCondition conditionModel = JItemModel.condition()
//				.property(usingItemProperty)
//				.onFalse(onFalseModel)
//				.onTrue(onTrueModel);
//
//		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(conditionModel)));

		JPropertyComponent componentProperty = JPropertyComponent.component("minecraft:item_name");

		// Build the select model with cases
		JItemModel selectModel = JItemModel.select()
				.property(componentProperty)
				.addCase(JSelectCase.of("Diamond", JModelBasic.of("minecraft:item/diamond")))
				.addCase(JSelectCase.of("Netherite Ingot", JModelBasic.of("minecraft:item/netherite_ingot")))
				.addCase(JSelectCase.of("Dirt", JModelBasic.of("minecraft:block/dirt")))
				.addCase(JSelectCase.of("Coal", JItemModel.rangeDispatch()
						.property(JPropertyCount.count())
						.entry(JRangeEntry.of(10, JModelBasic.of("minecraft:item/charcoal")))
						.fallback(JModelBasic.of("minecraft:item/coal"))
				))
				.fallback(JModelBasic.of("minecraft:block/stone"));
		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(selectModel)));

		selectModel = JItemModel.select()
				.property(componentProperty)
				.addCase(JSelectCase.of("Diamond", JModelBasic.of("minecraft:item/diamond")))
				.addCase(JSelectCase.of("Netherite Ingot", JModelBasic.of("minecraft:item/netherite_ingot")))
				.addCase(JSelectCase.of("Dirt", JModelBasic.of("minecraft:block/dirt")))
				.addCase(JSelectCase.of("Coal", JItemModel.rangeDispatch()
						.property(JPropertyCount.count())
						.entry(JRangeEntry.of(10, JModelBasic.of("minecraft:item/charcoal")))
						.fallback(JModelBasic.of("minecraft:item/coal"))
				))
				.fallback(JModelBasic.of("minecraft:block/stone").tint(new JTintDye(0xFF00FF)));
		System.out.println(JsonBytes.encodeToPrettyString(JItemInfo.CODEC, JItemInfo.item().model(selectModel)));

		JEquipmentModel eq = JEquipmentModel.builder()
				.addLayer("wings", JLayer.builder("minecraft:elytra").usePlayerTexture(true).build())
				.build();
		System.out.println(JsonBytes.encodeToPrettyString(JEquipmentModel.CODEC, eq));

		JEquipmentModel armorModel = JEquipmentModel.builder()
				// leather leggings, dyeable with default color
				.addLayer(LayerType.HUMANOID_LEGGINGS,
						JLayer.builder("minecraft:leather_leggings")
								.dyeable(Optional.of(0xA06540))   // default brown if undyed
								.build()
				)
				// main body layer, dyeable
				.addLayer(LayerType.HUMANOID,
						JLayer.builder("minecraft:leather")
								.dyeable(Optional.empty())        // no default color
								.build()
				)
				// overlay (non‑dyeable)
				.addLayer(LayerType.HUMANOID,
						JLayer.builder("minecraft:leather_overlay")
								.usePlayerTexture(false)
								.build()
				)
				.build();
		System.out.println(JsonBytes.encodeToPrettyString(JEquipmentModel.CODEC, armorModel));

		JEquipmentModel saddleModel = JEquipmentModel.builder()
				.addLayer(LayerType.HORSE_BODY,
						JLayer.builder("minecraft:saddle")
								.build()
				)
				.build();
		System.out.println(JsonBytes.encodeToPrettyString(JEquipmentModel.CODEC, saddleModel));

		JEquipmentModel backpack = JEquipmentModel.builder()
				.addLayerCustom("backpack",
						JLayer.builder("mymod:backpack")
								.build()
				)
				.build();
		System.out.println(JsonBytes.encodeToPrettyString(JEquipmentModel.CODEC, backpack));

		JLayer wolfLayer = JLayer.builder("mymod:wolf_tag")
				.usePlayerTexture(false)
				.build();

		JLayer bodyLayer = JLayer.builder("mymod:wolf_body")
				.dyeable(Optional.of(0xCCCCCC))
				.build();

		JEquipmentModel wolfWithTag = JEquipmentModel.builder()
				.addLayer(LayerType.WOLF_BODY, bodyLayer)
				.addLayerCustom("wolf_tag", wolfLayer)
				.build();
		System.out.println(JsonBytes.encodeToPrettyString(JEquipmentModel.CODEC, wolfWithTag));

		JEquipmentModel model1 = JEquipmentModel.builder()
				.addLayer(LayerType.HORSE_BODY,
						JLayer.builder("minecraft:diamond")
								.build()
				)
				.addLayer(LayerType.HUMANOID,
						JLayer.builder("minecraft:diamond")
								.build()
				)
				.addLayer(LayerType.HUMANOID_LEGGINGS,
						JLayer.builder("minecraft:diamond")
								.build()
				)
				.build();
		System.out.println(JsonBytes.encodeToPrettyString(JEquipmentModel.CODEC, model1));


		var baseModel = id("test", "furniture/aaaaaaaaa");

		JVariant variant = new JVariant()
				// north
				.put("facing=north,lit=false,occupied=false", JState.model(baseModel))
				.put("facing=north,lit=false,occupied=true",  JState.model(baseModel))
				.put("facing=north,lit=true,occupied=false",  JState.model(baseModel))
				.put("facing=north,lit=true,occupied=true",   JState.model(baseModel))
				// east
				.put("facing=east,lit=false,occupied=false",  JState.model(baseModel).y(90))
				.put("facing=east,lit=false,occupied=true",   JState.model(baseModel).y(90))
				.put("facing=east,lit=true,occupied=false",   JState.model(baseModel).y(90))
				.put("facing=east,lit=true,occupied=true",    JState.model(baseModel).y(90))
				// south
				.put("facing=south,lit=false,occupied=false", JState.model(baseModel).y(180))
				.put("facing=south,lit=false,occupied=true",  JState.model(baseModel).y(180))
				.put("facing=south,lit=true,occupied=false",  JState.model(baseModel).y(180))
				.put("facing=south,lit=true,occupied=true",   JState.model(baseModel).y(180))
				// west
				.put("facing=west,lit=false,occupied=false",  JState.model(baseModel).y(270))
				.put("facing=west,lit=false,occupied=true",   JState.model(baseModel).y(270))
				.put("facing=west,lit=true,occupied=false",   JState.model(baseModel).y(270))
				.put("facing=west,lit=true,occupied=true",    JState.model(baseModel).y(270));
		System.out.println(JsonBytes.encodeToPrettyString(JState.CODEC, state(variant)));

		{
			// Fence-like: post always, arms when north/east/south/west are true
			Identifier POST = Identifier.tryParse("block/oak_fence_post");
			Identifier ARM  = Identifier.tryParse("block/oak_fence_side");

			JState fenceLike = JState.state(Connectables.fenceLike(
					POST, ARM,
					"north", "east", "south", "west",
					true // uvlock
			));
			System.out.println("// Connectables.fenceLikeState");
			System.out.println(JsonBytes.encodeToPrettyString(JState.CODEC, fenceLike));
		}

		{
			// Pane-like: optional center + side per direction
			Identifier CENTER = Identifier.tryParse("block/glass_pane_noside");
			Identifier SIDE   = Identifier.tryParse("block/glass_pane_side");
			JState paneLike = JState.state(Connectables.paneLike(
					CENTER, SIDE,
					"north", "east", "south", "west",
					true
			));
			System.out.println("// Connectables.paneLike");
			System.out.println(JsonBytes.encodeToPrettyString(JState.CODEC, paneLike));
		}

		{
			// Counter-like: top always, edges per side, corners when two sides meet
			Identifier TOP    = Identifier.tryParse("test:block/counter/top");
			Identifier EDGE   = Identifier.tryParse("test:block/counter/edge");
			Identifier INNER  = Identifier.tryParse("test:block/counter/inner_corner");
			Identifier OUTER  = Identifier.tryParse("test:block/counter/outer_corner");

			JState counterLike = JState.state(Connectables.counterLike(
					TOP, EDGE, INNER, OUTER,
					"north", "east", "south", "west",
					true
			));
			System.out.println("// Connectables.counterLike");
			System.out.println(JsonBytes.encodeToPrettyString(JState.CODEC, counterLike));
		}
	}
}
