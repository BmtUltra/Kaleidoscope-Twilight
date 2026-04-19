package com.bmt.kaleidoscope_twilight.init;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class KTFoods {
    // 暮色浆果沙拉
    public static final FoodProperties TWILIGHT_BERRY_SALAD = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(ModEffects.MUSTARD.get(), 300 * 20), 1.0F)
            .alwaysEat().build();

    // 暮色猪儿虫
    public static final FoodProperties TWILIGHT_CATERPILLAR = new FoodProperties.Builder()
            .nutrition(18)
            .saturationMod(0.2f)
            .alwaysEat().build();

    // 鹿肉焖土豆
    public static final FoodProperties DEER_STEW_POTATO = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 鹿肉焖土豆盖饭
    public static final FoodProperties DEER_STEW_POTATO_RICE_BOWL = new FoodProperties.Builder()
            .nutrition(16)
            .saturationMod(1.0f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD.get(), 180 * 20), 1.0F)
            .alwaysEat().build();

    // 米诺陶卷
    public static final FoodProperties MINOTAUR_ROLL = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 180 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 暮光惠灵顿牛排
    public static final FoodProperties TWILIGHT_WELLINGTON_STEAK = new FoodProperties.Builder()
            .nutrition(20)
            .saturationMod(1.2f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, -1, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, -1, 0), 1.0f)
            .alwaysEat().build();

    // 盐焗娜迦
    public static final FoodProperties SALT_BAKED_NAGA = new FoodProperties.Builder()
            .nutrition(18)
            .saturationMod(1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 暮初恶魂意面
    public static final FoodProperties TWILIGHT_GHOST_PASTA = new FoodProperties.Builder()
            .nutrition(14)
            .saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 300 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 九头蛇肉酱面
    public static final FoodProperties HYDRA_BOLOGNESE = new FoodProperties.Builder()
            .nutrition(16)
            .saturationMod(1.1f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 240 * 20, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 240 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 荧光蘑菇瓦罐汤
    public static final FoodProperties GLOW_MUSHROOM_POT_SOUP = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 300 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 300 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 浇汁蛇宴
    public static final FoodProperties SAUCED_SNAKE_FEAST = new FoodProperties.Builder()
            .nutrition(22)
            .saturationMod(1.3f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 360 * 20, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 360 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 极光冰淇淋
    public static final FoodProperties AURORA_ICE_CREAM = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 180 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 魔豆汤
    public static final FoodProperties MAGIC_BEAN_SOUP = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 300 * 20, 1), 1.0f)
            .alwaysEat().build();

    // 冰川蛋糕
    public static final FoodProperties GLACIER_CAKE = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.4f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 荷花鸡
    public static final FoodProperties LOTUS_CHICKEN = new FoodProperties.Builder()
            .nutrition(14)
            .saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 300 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 生娜迦肉
    public static final FoodProperties RAW_NAGA_MEAT = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.3f)
            .alwaysEat().build();

    // 熟娜迦肉
    public static final FoodProperties COOKED_NAGA_MEAT = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.8f)
            .alwaysEat().build();

    // 战斧牛排
    public static final FoodProperties TOMAHAWK_STEAK = new FoodProperties.Builder()
            .nutrition(16)
            .saturationMod(1.2f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 240 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 生雪怪肉
    public static final FoodProperties RAW_YETI_MEAT = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.3f)
            .alwaysEat().build();

    // 熟雪怪肉
    public static final FoodProperties COOKED_YETI_MEAT = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.9f)
            .alwaysEat().build();

    // 填馅谜题羊角
    public static final FoodProperties STUFFED_PUZZLE_CROISSANT = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 600 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 巫术大骨汤
    public static final FoodProperties WITCHCRAFT_BONE_SOUP = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(KTEffects.WITCHCRAFT_PROTECTION.get(), 240 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 巫术可颂
    public static final FoodProperties WITCHCRAFT_CROISSANT = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(KTEffects.WITCHCRAFT_PROTECTION.get(), 120 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 幻影汤面
    public static final FoodProperties EVIL_SOUL_NOODLE_SOUP = new FoodProperties.Builder()
            .nutrition(14)
            .saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(KTEffects.PHANTOM.get(), 180 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 冰冻馒头
    public static final FoodProperties FROZEN_BUN = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 火炬浆果曲奇
    public static final FoodProperties TORCHBERRY_COOKIE = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.4f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 180 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 火炬浆果鹿肉三明治
    public static final FoodProperties TORCHBERRY_DEER_SANDWICH = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0F)
            .alwaysEat().build();

    // 试验品115号烤串
    public static final FoodProperties EXPERIMENT_115_SKEWER = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.1F)
            .fast()
            .alwaysEat().build();

    // 小炒肥虫
    public static final FoodProperties STIR_FRIED_FAT_CATERPILLAR = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 小炒肥虫盖饭
    public static final FoodProperties STIR_FRIED_FAT_CATERPILLAR_RICE_BOWL = new FoodProperties.Builder()
            .nutrition(14)
            .saturationMod(1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600 * 20, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 300 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 暮色蕨菜
    public static final FoodProperties TWILIGHT_FERN = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.2f)
            .alwaysEat().build();

    // 凉拌蕨菜
    public static final FoodProperties COLD_TOSSED_FERN = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 300 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 冰晶九头蛇排
    public static final FoodProperties ICE_CRYSTAL_HYDRA_STEAK = new FoodProperties.Builder()
            .nutrition(20)
            .saturationMod(1.5f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(KTEffects.FROST_CLOUD.get(), 300 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 冰晶慕斯
    public static final FoodProperties ICE_CRYSTAL_MOUSSE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 300 * 20, 1), 1.0f)
            .alwaysEat().build();

    // 暮色风味小鸡炖蘑菇
    public static final FoodProperties TWILIGHT_CHICKEN_MUSHROOM_STEW = new FoodProperties.Builder()
            .nutrition(14)
            .saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 120 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 300 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 迷宫夹心薄饼
    public static final FoodProperties MAZE_STUFFED_PANCAKE = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 600 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 巫术蛋糕
    public static final FoodProperties WITCHCRAFT_CAKE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(KTEffects.WITCHCRAFT_PROTECTION.get(), 300 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 180 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 幻影蕨菜煲
    public static final FoodProperties PHANTOM_FERN_STEW = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(KTEffects.PHANTOM.get(), 240 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 120 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 生暮色恶魂触手
    public static final FoodProperties RAW_TWILIGHT_GHOST_TENTACLE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 30 * 20, 0), 0.3f)
            .alwaysEat().build();

    // 熟暮色恶魂触手
    public static final FoodProperties COOKED_TWILIGHT_GHOST_TENTACLE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 180 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 10 * 20, 0), 0.5f)
            .alwaysEat().build();

    // 牛头人沙拉酱意面
    public static final FoodProperties MINOTAUR_SALAD_PASTA = new FoodProperties.Builder()
            .nutrition(16)
            .saturationMod(1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 120 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 暮色恶魂刺身
    public static final FoodProperties TWILIGHT_GHOST_SASHIMI = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 240 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 180 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 彩虹糖
    public static final FoodProperties RAINBOW_CANDY = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 60 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 300 * 20, 0), 1.0f)
            .fast()
            .alwaysEat().build();

    // 异色炫彩馒头
    public static final FoodProperties RAINBOW_BUN = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 180 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 300 * 20, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120 * 20, 0), 1.0f)
            .alwaysEat().build();

    // 茶枣子
    public static final FoodProperties TEA_DATE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 3 * 60 * 20, 0), 0.5f)
            .alwaysEat().build();
}