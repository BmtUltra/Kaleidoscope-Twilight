package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KTBrews {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(KaleidoscopeTwilight.MODID);

    public static final DeferredBlock<Block> CAVE_FIREFLY_BREW = BLOCKS.register("cave_firefly_brew", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    public static final DeferredBlock<Block> TWILIGHT_DEW = BLOCKS.register("twilight_dew", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    public static final DeferredBlock<Block> WITCHCRAFT_SECRET_BREW = BLOCKS.register("witchcraft_secret_brew", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    public static final DeferredBlock<Block> SNAKE_SKIN_LIQUOR = BLOCKS.register("snake_skin_liquor", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    public static final DeferredBlock<Block> ICE_CRYSTAL_FROST_DEW = BLOCKS.register("ice_crystal_frost_dew", 
            () -> DrinkBlock.create().maxCount(2).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    public static final DeferredBlock<Block> MAGIC_BEAN_BREW = BLOCKS.register("magic_bean_brew", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    public static final DeferredBlock<Block> EMBER_EYE = BLOCKS.register("ember_eye", 
            () -> DrinkBlock.create().maxCount(3).shapes(
                    Block.box(3, 0, 6, 13, 12, 10),
                    Block.box(1, 0, 3, 15, 12, 12),
                    Block.box(0, 0, 1, 16, 12, 13)
            ).build().get());

    public static final DeferredBlock<Block> DEER_SONG = BLOCKS.register("deer_song", 
            () -> DrinkBlock.create().maxCount(3).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    public static final DeferredBlock<Block> THORN_HEART = BLOCKS.register("thorn_heart", 
            () -> DrinkBlock.create().maxCount(2).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    public static final DeferredBlock<Block> DRUID_SECRET_BREW = BLOCKS.register("druid_secret_brew", 
            () -> DrinkBlock.create().maxCount(2).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    public static final DeferredBlock<Block> GLOWING_NIGHT_BIRD_SONG = BLOCKS.register("glowing_night_bird_song", 
            () -> DrinkBlock.create().maxCount(2).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    public static final DeferredBlock<Block> GLACIER_FROST_DEW = BLOCKS.register("glacier_frost_dew", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    public static final DeferredBlock<Block> GIANT_SPIRIT = BLOCKS.register("giant_spirit", 
            () -> DrinkBlock.create().maxCount(1).shapes(
                    Block.box(4, 0, 4, 12, 10, 12),
                    Block.box(2, 0, 2, 14, 12, 14),
                    Shapes.or(
                            Block.box(1, 0, 1, 15, 14, 15),
                            Block.box(3, 14, 3, 13, 16, 13)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());

    public static final DeferredBlock<Block> NATURE_SPIRIT = BLOCKS.register("nature_spirit", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());

    public static final DeferredBlock<?>[] ALL_BREWS = {
            CAVE_FIREFLY_BREW,
            TWILIGHT_DEW,
            WITCHCRAFT_SECRET_BREW,
            SNAKE_SKIN_LIQUOR,
            ICE_CRYSTAL_FROST_DEW,
            MAGIC_BEAN_BREW,
            EMBER_EYE,
            DEER_SONG,
            THORN_HEART,
            DRUID_SECRET_BREW,
            GLOWING_NIGHT_BIRD_SONG,
            GLACIER_FROST_DEW,
            GIANT_SPIRIT,
            NATURE_SPIRIT
    };
}