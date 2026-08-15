package com.bmt.kaleidoscope_twilight.util;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.Objects;

public class AttributeModifierHelper {

    public static class Builder {
        private final AttributeMap attributes;
        private final String prefix;

        public Builder(AttributeMap attributes, String modId, String effectName) {
            this.attributes = attributes;
            this.prefix = modId + ":" + effectName + "_";
        }

        public Builder add(Holder<Attribute> attribute, double value, AttributeModifier.Operation operation) {
            ResourceLocation id = ResourceLocation.parse(prefix + Objects.requireNonNull(attribute.getKey()).location().getPath());
            updateAttribute(attributes, attribute, id, value, operation);
            return this;
        }

        public Builder remove(Holder<Attribute> attribute) {
            ResourceLocation id = ResourceLocation.parse(prefix + Objects.requireNonNull(attribute.getKey()).location().getPath());
            removeBonus(attributes, attribute, id);
            return this;
        }
    }

    public static void updateAttribute(AttributeMap attributes, Holder<Attribute> attribute, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        AttributeInstance instance = attributes.getInstance(attribute);
        if (instance != null) {
            AttributeModifier existing = instance.getModifier(id);
            if (existing == null || existing.amount() != amount) {
                instance.removeModifier(id);
                instance.addTransientModifier(new AttributeModifier(id, amount, operation));
            }
        }
    }

    public static void removeBonus(AttributeMap attributes, Holder<Attribute> attribute, ResourceLocation id) {
        AttributeInstance instance = attributes.getInstance(attribute);
        if (instance != null) {
            instance.removeModifier(id);
        }
    }
}