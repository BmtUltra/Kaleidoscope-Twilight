package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.item.*;
import com.bmt.kaleidoscope_twilight.tier.BlazingIronTier;
import com.bmt.kaleidoscope_twilight.tier.IronwoodTier;
import com.bmt.kaleidoscope_twilight.tier.KnightTier;
import com.bmt.kaleidoscope_twilight.tier.SteelleafTier;
import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodOnlyItem;
import com.github.ysbbbbbb.kaleidoscopecookery.item.FoodWithEffectsItem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KTItems {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, KaleidoscopeTwilight.MODID);

    public static final BlazingIronTier BLAZING_IRON_TIER = new BlazingIronTier();
    public static final KnightTier KNIGHT_TIER = new KnightTier();
    public static final IronwoodTier IRONWOOD_TIER = new IronwoodTier();
    public static final SteelleafTier STEELLEAF_TIER = new SteelleafTier();

    // 暮色炉灶
    public static final DeferredHolder<Item, Item> TWILIGHT_STOVE = ITEMS.register("twilight_stove",
            () -> new BlockItem(KTBlocks.TWILIGHT_STOVE.get(), new Item.Properties()));

    // 暮色浆果沙拉
    public static final DeferredHolder<Item, BowlFoodOnlyItem> TWILIGHT_BERRY_SALAD_ITEM = ITEMS.register("twilight_berry_salad",
            () -> new BowlFoodOnlyItem(KTFoods.TWILIGHT_BERRY_SALAD));

    // 暮色猪儿虫
    public static final DeferredHolder<Item, Item> TWILIGHT_CATERPILLAR_ITEM = ITEMS.register("twilight_caterpillar",
            () -> new Item(new Item.Properties().food(KTFoods.TWILIGHT_CATERPILLAR)));

    // 鹿肉焖土豆
    public static final DeferredHolder<Item, BowlFoodOnlyItem> DEER_STEW_POTATO_ITEM = ITEMS.register("deer_stew_potato",
            () -> new BowlFoodOnlyItem(KTFoods.DEER_STEW_POTATO));

    // 鹿肉焖土豆盖饭
    public static final DeferredHolder<Item, BowlFoodOnlyItem> DEER_STEW_POTATO_RICE_BOWL_ITEM = ITEMS.register("deer_stew_potato_rice_bowl",
            () -> new BowlFoodOnlyItem(KTFoods.DEER_STEW_POTATO_RICE_BOWL));

    // 米诺陶卷
    public static final DeferredHolder<Item, FoodWithEffectsItem> MINOTAUR_ROLL_ITEM = ITEMS.register("minotaur_roll",
            () -> new FoodWithEffectsItem(KTFoods.MINOTAUR_ROLL));

    // 暮光惠灵顿牛排
    public static final DeferredHolder<Item, FoodWithEffectsItem> TWILIGHT_WELLINGTON_STEAK_ITEM = ITEMS.register("twilight_wellington_steak",
            () -> new FoodWithEffectsItem(KTFoods.TWILIGHT_WELLINGTON_STEAK));

    // 盐焗娜迦
    public static final DeferredHolder<Item, FoodWithEffectsItem> SALT_BAKED_NAGA_ITEM = ITEMS.register("salt_baked_naga",
            () -> new FoodWithEffectsItem(KTFoods.SALT_BAKED_NAGA));

    // 暮初恶魂意面
    public static final DeferredHolder<Item, BowlFoodOnlyItem> TWILIGHT_GHOST_PASTA_ITEM = ITEMS.register("twilight_ghost_pasta",
            () -> new BowlFoodOnlyItem(KTFoods.TWILIGHT_GHOST_PASTA));

    // 九头蛇肉酱面
    public static final DeferredHolder<Item, BowlFoodOnlyItem> HYDRA_BOLOGNESE_ITEM = ITEMS.register("hydra_bolognese",
            () -> new BowlFoodOnlyItem(KTFoods.HYDRA_BOLOGNESE));

    // 荧光蘑菇瓦罐汤
    public static final DeferredHolder<Item, BowlFoodOnlyItem> GLOW_MUSHROOM_POT_SOUP_ITEM = ITEMS.register("glow_mushroom_pot_soup",
            () -> new BowlFoodOnlyItem(KTFoods.GLOW_MUSHROOM_POT_SOUP));

    // 浇汁蛇宴
    public static final DeferredHolder<Item, FoodWithEffectsItem> SAUCED_SNAKE_FEAST_ITEM = ITEMS.register("sauced_snake_feast",
            () -> new FoodWithEffectsItem(KTFoods.SAUCED_SNAKE_FEAST));

    // 极光冰淇淋
    public static final DeferredHolder<Item, FoodWithEffectsItem> AURORA_ICE_CREAM_ITEM = ITEMS.register("aurora_ice_cream",
            () -> new FoodWithEffectsItem(KTFoods.AURORA_ICE_CREAM));

    // 魔豆汤
    public static final DeferredHolder<Item, BowlFoodOnlyItem> MAGIC_BEAN_SOUP_ITEM = ITEMS.register("magic_bean_soup",
            () -> new BowlFoodOnlyItem(KTFoods.MAGIC_BEAN_SOUP));

    // 冰川蛋糕
    public static final DeferredHolder<Item, FoodWithEffectsItem> GLACIER_CAKE_ITEM = ITEMS.register("glacier_cake",
            () -> new FoodWithEffectsItem(KTFoods.GLACIER_CAKE));

    // 荷花鸡
    public static final DeferredHolder<Item, FoodWithEffectsItem> LOTUS_CHICKEN_ITEM = ITEMS.register("lotus_chicken",
            () -> new FoodWithEffectsItem(KTFoods.LOTUS_CHICKEN));

    // 生娜迦肉
    public static final DeferredHolder<Item, Item> RAW_NAGA_MEAT_ITEM = ITEMS.register("raw_naga_meat",
            () -> new Item(new Item.Properties().food(KTFoods.RAW_NAGA_MEAT)));

    // 熟娜迦肉
    public static final DeferredHolder<Item, Item> COOKED_NAGA_MEAT_ITEM = ITEMS.register("cooked_naga_meat",
            () -> new Item(new Item.Properties().food(KTFoods.COOKED_NAGA_MEAT)));

    // 战斧牛排
    public static final DeferredHolder<Item, FoodWithEffectsItem> TOMAHAWK_STEAK_ITEM = ITEMS.register("tomahawk_steak",
            () -> new FoodWithEffectsItem(KTFoods.TOMAHAWK_STEAK));

    // 生雪怪肉
    public static final DeferredHolder<Item, Item> RAW_YETI_MEAT_ITEM = ITEMS.register("raw_yeti_meat",
            () -> new Item(new Item.Properties().food(KTFoods.RAW_YETI_MEAT)));

    // 熟雪怪肉
    public static final DeferredHolder<Item, Item> COOKED_YETI_MEAT_ITEM = ITEMS.register("cooked_yeti_meat",
            () -> new Item(new Item.Properties().food(KTFoods.COOKED_YETI_MEAT)));

    // 填馅谜题羊角
    public static final DeferredHolder<Item, FoodWithEffectsItem> STUFFED_PUZZLE_CROISSANT_ITEM = ITEMS.register("stuffed_puzzle_croissant",
            () -> new FoodWithEffectsItem(KTFoods.STUFFED_PUZZLE_CROISSANT));

    // 巫术骨头
    public static final DeferredHolder<Item, Item> WITCHCRAFT_BONE_ITEM = ITEMS.register("witchcraft_bone",
            () -> new Item(new Item.Properties()));

    // 巫术大骨汤
    public static final DeferredHolder<Item, BowlFoodOnlyItem> WITCHCRAFT_BONE_SOUP_ITEM = ITEMS.register("witchcraft_bone_soup",
            () -> new BowlFoodOnlyItem(KTFoods.WITCHCRAFT_BONE_SOUP));

    // 巫术可颂
    public static final DeferredHolder<Item, FoodWithEffectsItem> WITCHCRAFT_CROISSANT_ITEM = ITEMS.register("witchcraft_croissant",
            () -> new FoodWithEffectsItem(KTFoods.WITCHCRAFT_CROISSANT));

    // 幻影精华
    public static final DeferredHolder<Item, Item> EVIL_SOUL_ITEM = ITEMS.register("evil_soul",
            () -> new Item(new Item.Properties()));

    // 幻影汤面
    public static final DeferredHolder<Item, BowlFoodOnlyItem> EVIL_SOUL_NOODLE_SOUP_ITEM = ITEMS.register("evil_soul_noodle_soup",
            () -> new BowlFoodOnlyItem(KTFoods.EVIL_SOUL_NOODLE_SOUP));

    // 冰冻馒头
    public static final DeferredHolder<Item, FoodWithEffectsItem> FROZEN_BUN_ITEM = ITEMS.register("frozen_bun",
            () -> new FoodWithEffectsItem(KTFoods.FROZEN_BUN));

    // 火炬浆果曲奇
    public static final DeferredHolder<Item, FoodWithEffectsItem> TORCHBERRY_COOKIE_ITEM = ITEMS.register("torchberry_cookie",
            () -> new FoodWithEffectsItem(KTFoods.TORCHBERRY_COOKIE));

    // 火炬浆果鹿肉三明治
    public static final DeferredHolder<Item, Item> TORCHBERRY_DEER_SANDWICH_ITEM = ITEMS.register("torchberry_deer_sandwich",
            () -> new FoodWithEffectsItem(KTFoods.TORCHBERRY_DEER_SANDWICH));

    // 试验品115号烤串
    public static final DeferredHolder<Item, Experiment115SkewerItem> EXPERIMENT_115_SKEWER_ITEM = ITEMS.register("experiment_115_skewer",
            () -> new Experiment115SkewerItem(KTFoods.EXPERIMENT_115_SKEWER));

    // 小炒肥虫
    public static final DeferredHolder<Item, BowlFoodOnlyItem> STIR_FRIED_FAT_CATERPILLAR_ITEM = ITEMS.register("stir_fried_fat_caterpillar",
            () -> new BowlFoodOnlyItem(KTFoods.STIR_FRIED_FAT_CATERPILLAR));

    // 小炒肥虫盖饭
    public static final DeferredHolder<Item, BowlFoodOnlyItem> STIR_FRIED_FAT_CATERPILLAR_RICE_BOWL_ITEM = ITEMS.register("stir_fried_fat_caterpillar_rice_bowl",
            () -> new BowlFoodOnlyItem(KTFoods.STIR_FRIED_FAT_CATERPILLAR_RICE_BOWL));

    // 暮色蕨菜
    public static final DeferredHolder<Item, Item> TWILIGHT_FERN_ITEM = ITEMS.register("twilight_fern",
            () -> new Item(new Item.Properties().food(KTFoods.TWILIGHT_FERN)) {
                @Override
                public InteractionResult useOn(UseOnContext context) {
                    Level level = context.getLevel();
                    BlockPos pos = context.getClickedPos();
                    BlockState state = level.getBlockState(pos);

                    // 检查是否可以种植在耕地上
                    if (state.getBlock() instanceof net.minecraft.world.level.block.FarmBlock) {
                        // 将耕地变成暮色蕨菜作物
                        level.setBlock(pos.above(), KTBlocks.TWILIGHT_FERN_CROP.get().defaultBlockState(), 3);

                        // 消耗物品
                        if (!context.getPlayer().isCreative()) {
                            context.getItemInHand().shrink(1);
                        }

                        return InteractionResult.SUCCESS;
                    }

                    return super.useOn(context);
                }
            });

    // 凉拌蕨菜
    public static final DeferredHolder<Item, BowlFoodOnlyItem> COLD_TOSSED_FERN_ITEM = ITEMS.register("cold_tossed_fern",
            () -> new BowlFoodOnlyItem(KTFoods.COLD_TOSSED_FERN));

    // 四叶冰晶
    public static final DeferredHolder<Item, Item> FOUR_LEAF_ICE_CRYSTAL_ITEM = ITEMS.register("four_leaf_ice_crystal",
            () -> new Item(new Item.Properties()));

    // 冰晶九头蛇排
    public static final DeferredHolder<Item, FoodWithEffectsItem> ICE_CRYSTAL_HYDRA_STEAK_ITEM = ITEMS.register("ice_crystal_hydra_steak",
            () -> new FoodWithEffectsItem(KTFoods.ICE_CRYSTAL_HYDRA_STEAK));

    // 冰晶慕斯
    public static final DeferredHolder<Item, FoodWithEffectsItem> ICE_CRYSTAL_MOUSSE_ITEM = ITEMS.register("ice_crystal_mousse",
            () -> new FoodWithEffectsItem(KTFoods.ICE_CRYSTAL_MOUSSE));

    // 炽铁菜刀
    public static final DeferredHolder<Item, BlazingIronKitchenKnifeItem> BLAZING_IRON_KITCHEN_KNIFE = ITEMS.register("blazing_iron_kitchen_knife",
            () -> new BlazingIronKitchenKnifeItem(BLAZING_IRON_TIER,
                    new Item.Properties()
                            .attributes(BlazingIronKitchenKnifeItem.createAttributes(BLAZING_IRON_TIER, 3.0F, -2.0F))));

    // 骑士菜刀
    public static final DeferredHolder<Item, KnightKitchenKnifeItem> KNIGHT_KITCHEN_KNIFE = ITEMS.register("knight_kitchen_knife",
            () -> new KnightKitchenKnifeItem(KNIGHT_TIER,
                    new Item.Properties()
                            .attributes(KnightKitchenKnifeItem.createAttributes(KNIGHT_TIER, 3.0F, -2.0F))));

    // 铁木菜刀
    public static final DeferredHolder<Item, IronwoodKitchenKnifeItem> IRONWOOD_KITCHEN_KNIFE = ITEMS.register("ironwood_kitchen_knife",
            () -> new IronwoodKitchenKnifeItem(IRONWOOD_TIER,
                    new Item.Properties()
                            .attributes(IronwoodKitchenKnifeItem.createAttributes(IRONWOOD_TIER, 3.0F, -2.0F))));

    // 钢叶菜刀
    public static final DeferredHolder<Item, SteelleafKitchenKnifeItem> STEELLEAF_KITCHEN_KNIFE = ITEMS.register("steelleaf_kitchen_knife",
            () -> new SteelleafKitchenKnifeItem(STEELLEAF_TIER,
                    new Item.Properties()
                            .attributes(SteelleafKitchenKnifeItem.createAttributes(STEELLEAF_TIER, 3.0F, -2.0F))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}