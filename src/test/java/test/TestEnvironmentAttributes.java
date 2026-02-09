package test;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.attribute.AttributeRange;
import net.minecraft.world.attribute.EnvironmentAttribute;
import net.minecraft.world.level.MoonPhase;

public interface TestEnvironmentAttributes {

	EnvironmentAttribute<Integer> FOG_COLOR_VISUAL = register(
			"visual/fog_color", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.RGB_COLOR).defaultValue(0).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> FOG_START_DISTANCE_VISUAL = register(
			"visual/fog_start_distance", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.FLOAT).defaultValue(0.0F).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> FOG_END_DISTANCE_VISUAL = register(
			"visual/fog_end_distance",
			EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.FLOAT).defaultValue(1024.0F).valueRange(AttributeRange.NON_NEGATIVE_FLOAT).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> SKY_FOG_END_DISTANCE_VISUAL = register(
			"visual/sky_fog_end_distance",
			EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.FLOAT).defaultValue(512.0F).valueRange(AttributeRange.NON_NEGATIVE_FLOAT).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> CLOUD_FOG_END_DISTANCE_VISUAL = register(
			"visual/cloud_fog_end_distance",
			EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.FLOAT).defaultValue(2048.0F).valueRange(AttributeRange.NON_NEGATIVE_FLOAT).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Integer> WATER_FOG_COLOR_VISUAL = register(
			"visual/water_fog_color", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.RGB_COLOR).defaultValue(-16448205).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> WATER_FOG_START_DISTANCE_VISUAL = register(
			"visual/water_fog_start_distance", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.FLOAT).defaultValue(-8.0F).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> WATER_FOG_END_DISTANCE_VISUAL = register(
			"visual/water_fog_end_distance",
			EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.FLOAT).defaultValue(96.0F).valueRange(AttributeRange.NON_NEGATIVE_FLOAT).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Integer> SKY_COLOR_VISUAL = register(
			"visual/sky_color", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.RGB_COLOR).defaultValue(0).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Integer> SUNRISE_SUNSET_COLOR_VISUAL = register(
			"visual/sunrise_sunset_color", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.ARGB_COLOR).defaultValue(0).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Integer> CLOUD_COLOR_VISUAL = register(
			"visual/cloud_color", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.ARGB_COLOR).defaultValue(0).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> CLOUD_HEIGHT_VISUAL = register(
			"visual/cloud_height", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.FLOAT).defaultValue(192.33F).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> SUN_ANGLE_VISUAL = register(
			"visual/sun_angle", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.ANGLE_DEGREES).defaultValue(0.0F).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> MOON_ANGLE_VISUAL = register(
			"visual/moon_angle", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.ANGLE_DEGREES).defaultValue(0.0F).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> STAR_ANGLE_VISUAL = register(
			"visual/star_angle", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.ANGLE_DEGREES).defaultValue(0.0F).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<MoonPhase> MOON_PHASE_VISUAL = register(
			"visual/moon_phase", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.MOON_PHASE).defaultValue(MoonPhase.FULL_MOON).syncable()
	);
	EnvironmentAttribute<Float> STAR_BRIGHTNESS_VISUAL = register(
			"visual/star_brightness",
			EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.FLOAT).defaultValue(0.0F).valueRange(AttributeRange.UNIT_FLOAT).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Integer> SKY_LIGHT_COLOR_VISUAL = register(
			"visual/sky_light_color", EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.RGB_COLOR).defaultValue(-1).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> SKY_LIGHT_FACTOR_VISUAL = register(
			"visual/sky_light_factor",
			EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.FLOAT).defaultValue(1.0F).valueRange(AttributeRange.UNIT_FLOAT).spatiallyInterpolated().syncable()
	);
	EnvironmentAttribute<Float> SKY_LIGHT_LEVEL_GAMEPLAY = register(
			"gameplay/sky_light_level",
			EnvironmentAttribute.builder(TestEnvironmentAttributeTypes.FLOAT).defaultValue(15.0F).valueRange(AttributeRange.ofFloat(0.0F, 15.0F)).notPositional().syncable()
	);

	private static <Value> EnvironmentAttribute<Value> register(String path, EnvironmentAttribute.Builder<Value> builder) {
		EnvironmentAttribute<Value> environmentAttribute = builder.build();
		Registry.register(BuiltInRegistries.ENVIRONMENT_ATTRIBUTE, Identifier.withDefaultNamespace(path), environmentAttribute);
		return environmentAttribute;
	}
}
