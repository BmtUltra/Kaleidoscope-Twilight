package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopetavern.item.DrinkBlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KTBrewItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KaleidoscopeTwilight.MODID);
    
    // 洞窟萤火酿
    public static final DeferredItem<Item> CAVE_FIREFLY_BREW_ITEM = ITEMS.register("cave_firefly_brew", 
            () -> new DrinkBlockItem(KTBrews.CAVE_FIREFLY_BREW.get()));
    
    // 暮光晨露
    public static final DeferredItem<Item> TWILIGHT_DEW_ITEM = ITEMS.register("twilight_dew", 
            () -> new DrinkBlockItem(KTBrews.TWILIGHT_DEW.get()));
    
    // 巫术秘酿
    public static final DeferredItem<Item> WITCHCRAFT_SECRET_BREW_ITEM = ITEMS.register("witchcraft_secret_brew", 
            () -> new DrinkBlockItem(KTBrews.WITCHCRAFT_SECRET_BREW.get()));
    
    // 蛇蜕利
    public static final DeferredItem<Item> SNAKE_SKIN_LIQUOR_ITEM = ITEMS.register("snake_skin_liquor", 
            () -> new DrinkBlockItem(KTBrews.SNAKE_SKIN_LIQUOR.get()));
    
    // 冰晶霜露
    public static final DeferredItem<Item> ICE_CRYSTAL_FROST_DEW_ITEM = ITEMS.register("ice_crystal_frost_dew", 
            () -> new DrinkBlockItem(KTBrews.ICE_CRYSTAL_FROST_DEW.get()));
    
    // 魔豆酿
    public static final DeferredItem<Item> MAGIC_BEAN_BREW_ITEM = ITEMS.register("magic_bean_brew", 
            () -> new DrinkBlockItem(KTBrews.MAGIC_BEAN_BREW.get()));
    
    // 烬瞳
    public static final DeferredItem<Item> EMBER_EYE_ITEM = ITEMS.register("ember_eye", 
            () -> new DrinkBlockItem(KTBrews.EMBER_EYE.get()));
    
    // 呦呦鹿鸣
    public static final DeferredItem<Item> DEER_SONG_ITEM = ITEMS.register("deer_song", 
            () -> new DrinkBlockItem(KTBrews.DEER_SONG.get()));
    
    // 荆棘之心
    public static final DeferredItem<Item> THORN_HEART_ITEM = ITEMS.register("thorn_heart", 
            () -> new DrinkBlockItem(KTBrews.THORN_HEART.get()));
    
    // 德鲁伊秘酿
    public static final DeferredItem<Item> DRUID_SECRET_BREW_ITEM = ITEMS.register("druid_secret_brew", 
            () -> new DrinkBlockItem(KTBrews.DRUID_SECRET_BREW.get()));
    
    // 辉夜鸟之歌
    public static final DeferredItem<Item> GLOWING_NIGHT_BIRD_SONG_ITEM = ITEMS.register("glowing_night_bird_song", 
            () -> new DrinkBlockItem(KTBrews.GLOWING_NIGHT_BIRD_SONG.get()));
    
    // 冰川霜露
    public static final DeferredItem<Item> GLACIER_FROST_DEW_ITEM = ITEMS.register("glacier_frost_dew", 
            () -> new DrinkBlockItem(KTBrews.GLACIER_FROST_DEW.get()));
    
    // 巨人烈酒
    public static final DeferredItem<Item> GIANT_SPIRIT_ITEM = ITEMS.register("giant_spirit", 
            () -> new DrinkBlockItem(KTBrews.GIANT_SPIRIT.get()));
    
    // 自然之灵
    public static final DeferredItem<Item> NATURE_SPIRIT_ITEM = ITEMS.register("nature_spirit", 
            () -> new DrinkBlockItem(KTBrews.NATURE_SPIRIT.get()));

    // 火炬浆果桶
    public static final DeferredItem<Item> TORCHBERRY_BUCKET = ITEMS.register("torchberry_bucket",
            () -> new BucketItem(KTFluids.TORCHBERRY_JUICE.get(), new Item.Properties().stacksTo(16).craftRemainder(Items.BUCKET)));
}