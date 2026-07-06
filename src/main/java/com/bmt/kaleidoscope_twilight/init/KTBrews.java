package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KTBrews {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, KaleidoscopeTwilight.MODID);

    // 洞窟萤火酿
    public static final RegistryObject<Block> CAVE_FIREFLY_BREW = BLOCKS.register("cave_firefly_brew",
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    // 暮光晨露
    public static final RegistryObject<Block> TWILIGHT_DEW = BLOCKS.register("twilight_dew",
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    // 巫术秘酿
    public static final RegistryObject<Block> WITCHCRAFT_SECRET_BREW = BLOCKS.register("witchcraft_secret_brew",
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    // 蛇蜕利
    public static final RegistryObject<Block> SNAKE_SKIN_LIQUOR = BLOCKS.register("snake_skin_liquor",
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    // 冰晶露
    public static final RegistryObject<Block> ICE_CRYSTAL_FROST_DEW = BLOCKS.register("ice_crystal_frost_dew",
            () -> DrinkBlock.create().maxCount(2).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    // 魔豆酿
    public static final RegistryObject<Block> MAGIC_BEAN_BREW = BLOCKS.register("magic_bean_brew",
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    // 灰烬之眼
    public static final RegistryObject<Block> EMBER_EYE = BLOCKS.register("ember_eye",
            () -> DrinkBlock.create().maxCount(3).shapes(
                    Block.box(3, 0, 6, 13, 12, 10),
                    Block.box(1, 0, 3, 15, 12, 12),
                    Block.box(0, 0, 1, 16, 12, 13)
            ).build().get());

    // 呦呦鹿鸣
    public static final RegistryObject<Block> DEER_SONG = BLOCKS.register("deer_song",
            () -> DrinkBlock.create().maxCount(3).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    // 荆棘之心
    public static final RegistryObject<Block> THORN_HEART = BLOCKS.register("thorn_heart",
            () -> DrinkBlock.create().maxCount(2).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    // 德鲁伊秘酿
    public static final RegistryObject<Block> DRUID_SECRET_BREW = BLOCKS.register("druid_secret_brew",
            () -> DrinkBlock.create().maxCount(2).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    // 辉夜鸟之歌
    public static final RegistryObject<Block> GLOWING_NIGHT_BIRD_SONG = BLOCKS.register("glowing_night_bird_song",
            () -> DrinkBlock.create().maxCount(2).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    // 冰川霜露
    public static final RegistryObject<Block> GLACIER_FROST_DEW = BLOCKS.register("glacier_frost_dew",
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    // 巨人烈酒
    public static final RegistryObject<Block> GIANT_SPIRIT = BLOCKS.register("giant_spirit",
            () -> DrinkBlock.create().maxCount(1).shapes(
                    Block.box(4, 0, 4, 12, 10, 12),
                    Block.box(2, 0, 2, 14, 12, 14),
                    Shapes.or(
                            Block.box(1, 0, 1, 15, 14, 15),
                            Block.box(3, 14, 3, 13, 16, 13)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    // 自然葡萄酒
    public static final RegistryObject<Block> NATURE_SPIRIT = BLOCKS.register("nature_spirit",
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}