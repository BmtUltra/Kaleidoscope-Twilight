package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.item.*;
import com.bmt.kaleidoscope_twilight.util.tier.BlazingIronTier;
import com.bmt.kaleidoscope_twilight.util.tier.IronwoodTier;
import com.bmt.kaleidoscope_twilight.util.tier.KnightTier;
import com.bmt.kaleidoscope_twilight.util.tier.SteelleafTier;
import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodOnlyItem;
import com.github.ysbbbbbb.kaleidoscopecookery.item.FoodWithEffectsItem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class KTItems {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, KaleidoscopeTwilight.MODID);

    public static final BlazingIronTier BLAZING_IRON_TIER = new BlazingIronTier();
    public static final KnightTier KNIGHT_TIER = new KnightTier();
    public static final IronwoodTier IRONWOOD_TIER = new IronwoodTier();
    public static final SteelleafTier STEELLEAF_TIER = new SteelleafTier();

    // 暮色炉灶
    public static final RegistryObject<Item> TWILIGHT_STOVE = ITEMS.register("twilight_stove",
            () -> new BlockItem(KTBlocks.TWILIGHT_STOVE.get(), new Item.Properties()));

    // 暮色浆果沙拉
    public static final RegistryObject<BowlFoodOnlyItem> TWILIGHT_BERRY_SALAD_ITEM = ITEMS.register("twilight_berry_salad",
            () -> new BowlFoodOnlyItem(KTFoods.TWILIGHT_BERRY_SALAD));

    // 暮色猪儿虫
    public static final RegistryObject<Item> TWILIGHT_CATERPILLAR_ITEM = ITEMS.register("twilight_caterpillar",
            () -> new Item(new Item.Properties().food(KTFoods.TWILIGHT_CATERPILLAR)));

    // 鹿肉焖土豆
    public static final RegistryObject<BowlFoodOnlyItem> DEER_STEW_POTATO_ITEM = ITEMS.register("deer_stew_potato",
            () -> new BowlFoodOnlyItem(KTFoods.DEER_STEW_POTATO));

    // 鹿肉焖土豆盖饭
    public static final RegistryObject<BowlFoodOnlyItem> DEER_STEW_POTATO_RICE_BOWL_ITEM = ITEMS.register("deer_stew_potato_rice_bowl",
            () -> new BowlFoodOnlyItem(KTFoods.DEER_STEW_POTATO_RICE_BOWL));

    // 米诺陶卷
    public static final RegistryObject<FoodWithEffectsItem> MINOTAUR_ROLL_ITEM = ITEMS.register("minotaur_roll",
            () -> new FoodWithEffectsItem(KTFoods.MINOTAUR_ROLL));

    // 暮光惠灵顿牛排
    public static final RegistryObject<FoodWithEffectsItem> TWILIGHT_WELLINGTON_STEAK_ITEM = ITEMS.register("twilight_wellington_steak",
            () -> new FoodWithEffectsItem(KTFoods.TWILIGHT_WELLINGTON_STEAK));

    // 盐焗娜迦
    public static final RegistryObject<FoodWithEffectsItem> SALT_BAKED_NAGA_ITEM = ITEMS.register("salt_baked_naga",
            () -> new FoodWithEffectsItem(KTFoods.SALT_BAKED_NAGA));

    // 暮初恶魂意面
    public static final RegistryObject<BowlFoodOnlyItem> TWILIGHT_GHOST_PASTA_ITEM = ITEMS.register("twilight_ghost_pasta",
            () -> new BowlFoodOnlyItem(KTFoods.TWILIGHT_GHOST_PASTA));

    // 九头蛇肉酱面
    public static final RegistryObject<BowlFoodOnlyItem> HYDRA_BOLOGNESE_ITEM = ITEMS.register("hydra_bolognese",
            () -> new BowlFoodOnlyItem(KTFoods.HYDRA_BOLOGNESE));

    // 荧光蘑菇瓦罐汤
    public static final RegistryObject<BowlFoodOnlyItem> GLOW_MUSHROOM_POT_SOUP_ITEM = ITEMS.register("glow_mushroom_pot_soup",
            () -> new BowlFoodOnlyItem(KTFoods.GLOW_MUSHROOM_POT_SOUP));

    // 浇汁蛇宴
    public static final RegistryObject<FoodWithEffectsItem> SAUCED_SNAKE_FEAST_ITEM = ITEMS.register("sauced_snake_feast",
            () -> new FoodWithEffectsItem(KTFoods.SAUCED_SNAKE_FEAST));

    // 极光冰淇淋
    public static final RegistryObject<FoodWithEffectsItem> AURORA_ICE_CREAM_ITEM = ITEMS.register("aurora_ice_cream",
            () -> new FoodWithEffectsItem(KTFoods.AURORA_ICE_CREAM));

    // 魔豆汤
    public static final RegistryObject<BowlFoodOnlyItem> MAGIC_BEAN_SOUP_ITEM = ITEMS.register("magic_bean_soup",
            () -> new BowlFoodOnlyItem(KTFoods.MAGIC_BEAN_SOUP));

    // 冰川蛋糕
    public static final RegistryObject<FoodWithEffectsItem> GLACIER_CAKE_ITEM = ITEMS.register("glacier_cake",
            () -> new FoodWithEffectsItem(KTFoods.GLACIER_CAKE));

    // 荷花鸡
    public static final RegistryObject<FoodWithEffectsItem> LOTUS_CHICKEN_ITEM = ITEMS.register("lotus_chicken",
            () -> new FoodWithEffectsItem(KTFoods.LOTUS_CHICKEN));

    // 生娜迦肉
    public static final RegistryObject<Item> RAW_NAGA_MEAT_ITEM = ITEMS.register("raw_naga_meat",
            () -> new Item(new Item.Properties().food(KTFoods.RAW_NAGA_MEAT)));

    // 熟娜迦肉
    public static final RegistryObject<Item> COOKED_NAGA_MEAT_ITEM = ITEMS.register("cooked_naga_meat",
            () -> new Item(new Item.Properties().food(KTFoods.COOKED_NAGA_MEAT)));

    // 战斧牛排
    public static final RegistryObject<FoodWithEffectsItem> TOMAHAWK_STEAK_ITEM = ITEMS.register("tomahawk_steak",
            () -> new FoodWithEffectsItem(KTFoods.TOMAHAWK_STEAK));

    // 生雪怪肉
    public static final RegistryObject<Item> RAW_YETI_MEAT_ITEM = ITEMS.register("raw_yeti_meat",
            () -> new Item(new Item.Properties().food(KTFoods.RAW_YETI_MEAT)));

    // 熟雪怪肉
    public static final RegistryObject<Item> COOKED_YETI_MEAT_ITEM = ITEMS.register("cooked_yeti_meat",
            () -> new Item(new Item.Properties().food(KTFoods.COOKED_YETI_MEAT)));

    // 填馅谜题羊角
    public static final RegistryObject<FoodWithEffectsItem> STUFFED_PUZZLE_CROISSANT_ITEM = ITEMS.register("stuffed_puzzle_croissant",
            () -> new FoodWithEffectsItem(KTFoods.STUFFED_PUZZLE_CROISSANT));

    // 巫术骨头
    public static final RegistryObject<Item> WITCHCRAFT_BONE_ITEM = ITEMS.register("witchcraft_bone",
            () -> new Item(new Item.Properties()));

    // 巫术大骨汤
    public static final RegistryObject<BowlFoodOnlyItem> WITCHCRAFT_BONE_SOUP_ITEM = ITEMS.register("witchcraft_bone_soup",
            () -> new BowlFoodOnlyItem(KTFoods.WITCHCRAFT_BONE_SOUP));

    // 巫术可颂
    public static final RegistryObject<FoodWithEffectsItem> WITCHCRAFT_CROISSANT_ITEM = ITEMS.register("witchcraft_croissant",
            () -> new FoodWithEffectsItem(KTFoods.WITCHCRAFT_CROISSANT));

    // 幻影精华
    public static final RegistryObject<Item> EVIL_SOUL_ITEM = ITEMS.register("evil_soul",
            () -> new Item(new Item.Properties()));

    // 幻影汤面
    public static final RegistryObject<BowlFoodOnlyItem> EVIL_SOUL_NOODLE_SOUP_ITEM = ITEMS.register("evil_soul_noodle_soup",
            () -> new BowlFoodOnlyItem(KTFoods.EVIL_SOUL_NOODLE_SOUP));

    // 冰冻馒头
    public static final RegistryObject<FoodWithEffectsItem> FROZEN_BUN_ITEM = ITEMS.register("frozen_bun",
            () -> new FoodWithEffectsItem(KTFoods.FROZEN_BUN));

    // 火炬浆果曲奇
    public static final RegistryObject<FoodWithEffectsItem> TORCHBERRY_COOKIE_ITEM = ITEMS.register("torchberry_cookie",
            () -> new FoodWithEffectsItem(KTFoods.TORCHBERRY_COOKIE));

    // 火炬浆果鹿肉三明治
    public static final RegistryObject<Item> TORCHBERRY_DEER_SANDWICH_ITEM = ITEMS.register("torchberry_deer_sandwich",
            () -> new FoodWithEffectsItem(KTFoods.TORCHBERRY_DEER_SANDWICH));

    // 试验品115号烤串
    public static final RegistryObject<Experiment115SkewerItem> EXPERIMENT_115_SKEWER_ITEM = ITEMS.register("experiment_115_skewer",
            () -> new Experiment115SkewerItem(KTFoods.EXPERIMENT_115_SKEWER));

    // 小炒肥虫
    public static final RegistryObject<BowlFoodOnlyItem> STIR_FRIED_FAT_CATERPILLAR_ITEM = ITEMS.register("stir_fried_fat_caterpillar",
            () -> new BowlFoodOnlyItem(KTFoods.STIR_FRIED_FAT_CATERPILLAR));

    // 小炒肥虫盖饭
    public static final RegistryObject<BowlFoodOnlyItem> STIR_FRIED_FAT_CATERPILLAR_RICE_BOWL_ITEM = ITEMS.register("stir_fried_fat_caterpillar_rice_bowl",
            () -> new BowlFoodOnlyItem(KTFoods.STIR_FRIED_FAT_CATERPILLAR_RICE_BOWL));

    // 暮色风味小鸡炖蘑菇
    public static final RegistryObject<BowlFoodOnlyItem> TWILIGHT_CHICKEN_MUSHROOM_STEW_ITEM = ITEMS.register("twilight_chicken_mushroom_stew",
            () -> new BowlFoodOnlyItem(KTFoods.TWILIGHT_CHICKEN_MUSHROOM_STEW));

    // 迷宫夹心薄饼
    public static final RegistryObject<FoodWithEffectsItem> MAZE_STUFFED_PANCAKE_ITEM = ITEMS.register("maze_stuffed_pancake",
            () -> new FoodWithEffectsItem(KTFoods.MAZE_STUFFED_PANCAKE));

    // 巫术蛋糕
    public static final RegistryObject<FoodWithEffectsItem> WITCHCRAFT_CAKE_ITEM = ITEMS.register("witchcraft_cake",
            () -> new FoodWithEffectsItem(KTFoods.WITCHCRAFT_CAKE));

    // 幻影蕨菜煲
    public static final RegistryObject<BowlFoodOnlyItem> PHANTOM_FERN_STEW_ITEM = ITEMS.register("phantom_fern_stew",
            () -> new BowlFoodOnlyItem(KTFoods.PHANTOM_FERN_STEW));

    // 生暮色恶魂触手
    public static final RegistryObject<Item> RAW_TWILIGHT_GHOST_TENTACLE_ITEM = ITEMS.register("raw_twilight_ghost_tentacle",
            () -> new Item(new Item.Properties().food(KTFoods.RAW_TWILIGHT_GHOST_TENTACLE)));

    // 熟暮色恶魂触手
    public static final RegistryObject<Item> COOKED_TWILIGHT_GHOST_TENTACLE_ITEM = ITEMS.register("cooked_twilight_ghost_tentacle",
            () -> new Item(new Item.Properties().food(KTFoods.COOKED_TWILIGHT_GHOST_TENTACLE)));

    // 牛头人沙拉酱意面
    public static final RegistryObject<BowlFoodOnlyItem> MINOTAUR_SALAD_PASTA_ITEM = ITEMS.register("minotaur_salad_pasta",
            () -> new BowlFoodOnlyItem(KTFoods.MINOTAUR_SALAD_PASTA));

    // 暮色恶魂刺身
    public static final RegistryObject<FoodWithEffectsItem> TWILIGHT_GHOST_SASHIMI_ITEM = ITEMS.register("twilight_ghost_sashimi",
            () -> new FoodWithEffectsItem(KTFoods.TWILIGHT_GHOST_SASHIMI));

    // 彩虹糖
    public static final RegistryObject<FoodWithEffectsItem> RAINBOW_CANDY_ITEM = ITEMS.register("rainbow_candy",
            () -> new FoodWithEffectsItem(KTFoods.RAINBOW_CANDY));

    // 异色炫彩馒头
    public static final RegistryObject<FoodWithEffectsItem> RAINBOW_BUN_ITEM = ITEMS.register("rainbow_bun",
            () -> new FoodWithEffectsItem(KTFoods.RAINBOW_BUN));

    // 蕨菜
    public static final RegistryObject<Item> TWILIGHT_FERN_ITEM = ITEMS.register("twilight_fern",
            () -> new Item(new Item.Properties().food(KTFoods.TWILIGHT_FERN)) {
                @Override
                public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
                    Level level = context.getLevel();
                    BlockPos pos = context.getClickedPos();
                    BlockState state = level.getBlockState(pos);

                    if (state.getBlock() instanceof FarmBlock) {
                        level.setBlock(pos.above(), KTBlocks.TWILIGHT_FERN_CROP.get().defaultBlockState(), 3);
                        if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
                            context.getItemInHand().shrink(1);
                        }
                        return InteractionResult.SUCCESS;
                    }
                    return super.useOn(context);
                }
            });

    // 凉拌蕨菜
    public static final RegistryObject<BowlFoodOnlyItem> COLD_TOSSED_FERN_ITEM = ITEMS.register("cold_tossed_fern",
            () -> new BowlFoodOnlyItem(KTFoods.COLD_TOSSED_FERN));

    // 四叶冰晶
    public static final RegistryObject<Item> FOUR_LEAF_ICE_CRYSTAL_ITEM = ITEMS.register("four_leaf_ice_crystal",
            () -> new Item(new Item.Properties()));

    // 冰晶九头蛇排
    public static final RegistryObject<FoodWithEffectsItem> ICE_CRYSTAL_HYDRA_STEAK_ITEM = ITEMS.register("ice_crystal_hydra_steak",
            () -> new FoodWithEffectsItem(KTFoods.ICE_CRYSTAL_HYDRA_STEAK));

    // 冰晶慕斯
    public static final RegistryObject<FoodWithEffectsItem> ICE_CRYSTAL_MOUSSE_ITEM = ITEMS.register("ice_crystal_mousse",
            () -> new FoodWithEffectsItem(KTFoods.ICE_CRYSTAL_MOUSSE));

    // 茶枣子
    public static final RegistryObject<FoodWithEffectsItem> TEA_DATE_ITEM = ITEMS.register("tea_date",
            () -> new FoodWithEffectsItem(KTFoods.TEA_DATE));

    // 炽铁菜刀
    public static final RegistryObject<BlazingIronKitchenKnifeItem> BLAZING_IRON_KITCHEN_KNIFE = ITEMS.register("blazing_iron_kitchen_knife",
            () -> new BlazingIronKitchenKnifeItem(BLAZING_IRON_TIER,
                    new Item.Properties()));

    // 骑士菜刀
    public static final RegistryObject<KnightKitchenKnifeItem> KNIGHT_KITCHEN_KNIFE = ITEMS.register("knight_kitchen_knife",
            () -> new KnightKitchenKnifeItem(KNIGHT_TIER,
                    new Item.Properties()));

    // 铁木菜刀
    public static final RegistryObject<IronwoodKitchenKnifeItem> IRONWOOD_KITCHEN_KNIFE = ITEMS.register("ironwood_kitchen_knife",
            () -> new IronwoodKitchenKnifeItem(IRONWOOD_TIER,
                    new Item.Properties()));

    // 钢叶菜刀
    public static final RegistryObject<SteelleafKitchenKnifeItem> STEELLEAF_KITCHEN_KNIFE = ITEMS.register("steelleaf_kitchen_knife",
            () -> new SteelleafKitchenKnifeItem(STEELLEAF_TIER,
                    new Item.Properties()));

    // 保管符袋
    public static final RegistryObject<KeepingPouchItem> KEEPING_POUCH_ITEM = ITEMS.register("keeping_pouch",
            KeepingPouchItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}