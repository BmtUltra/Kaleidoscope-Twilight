package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KTBrews {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(KaleidoscopeTwilight.MODID);
    
    // 洞窟萤火酿
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
    
    // 暮光晨露
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
    
    // 巫术秘酿
    public static final DeferredBlock<Block> WITCHCRAFT_SECRET_BREW = BLOCKS.register("witchcraft_secret_brew", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());
    
    // 蛇蜕利
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
    
    // 冰晶霜露
    public static final DeferredBlock<Block> ICE_CRYSTAL_FROST_DEW = BLOCKS.register("ice_crystal_frost_dew", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());
    
    // 魔豆酿
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
    
    // 烬瞳
    public static final DeferredBlock<Block> EMBER_EYE = BLOCKS.register("ember_eye", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());
    
    // 呦呦鹿鸣
    public static final DeferredBlock<Block> DEER_SONG = BLOCKS.register("deer_song", 
            () -> DrinkBlock.create().maxCount(3).irregular().shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());
    
    // 荆棘之心
    public static final DeferredBlock<Block> THORN_HEART = BLOCKS.register("thorn_heart", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());
    
    // 德鲁伊秘酿
    public static final DeferredBlock<Block> DRUID_SECRET_BREW = BLOCKS.register("druid_secret_brew", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());
    
    // 辉夜鸟之歌
    public static final DeferredBlock<Block> GLOWING_NIGHT_BIRD_SONG = BLOCKS.register("glowing_night_bird_song", 
            () -> DrinkBlock.create().maxCount(4).shapes(
                    Block.box(6, 0, 6, 10, 16, 10),
                    Block.box(2, 0, 6, 14, 16, 10),
                    Shapes.or(
                            Block.box(2, 0, 10, 14, 16, 14),
                            Block.box(6, 0, 2, 10, 16, 14)
                    ),
                    Block.box(2, 0, 2, 14, 16, 14)
            ).build().get());
    
    // 冰川霜露
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
    
    // 巨人烈酒
    public static final DeferredBlock<Block> GIANT_SPIRIT = BLOCKS.register("giant_spirit", 
            () -> DrinkBlock.create().maxCount(3).shapes(
                    Block.box(4, 0, 4, 12, 15, 12),
                    Block.box(0, 0, 4, 16, 15, 12),
                    Shapes.or(
                            Block.box(0, 0, 8, 16, 15, 16),
                            Block.box(4, 0, 0, 12, 15, 16)
                    ),
                    Block.box(0, 0, 0, 16, 16, 16)
            ).build().get());
    
    // 自然之灵
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
}