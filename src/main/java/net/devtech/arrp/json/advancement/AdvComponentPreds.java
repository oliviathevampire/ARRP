package net.devtech.arrp.json.advancement;// AdvComponentPreds.java

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public final class AdvComponentPreds {
  private AdvComponentPreds() {}

  public static Identifier id(ComponentType<?> type) {
    Identifier id = Registries.DATA_COMPONENT_TYPE.getId(type);
    if (id == null) throw new IllegalStateException("Unregistered component type: " + type);
    return id;
  }

  public static <V> JsonElement encodeValue(ComponentType<V> type, V value) {
    return type.getCodec().encodeStart(JsonOps.INSTANCE, value).getOrThrow();
  }
}
