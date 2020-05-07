package com.irtimaled.bbor.common.events;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.level.LevelProperties;

public class WorldLoaded {
    private final int dimensionId;
    private final long seed;
    private final int spawnX;
    private final int spawnZ;

    public WorldLoaded(ServerWorld world) {
        this.dimensionId = world.getDimension().getType().getRawId();
        this.seed = world.getLevelProperties().getSeed();
        this.spawnX = world.getLevelProperties().getSpawnX();
        this.spawnZ = world.getLevelProperties().getSpawnZ();
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public long getSeed() {
        return seed;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnZ() {
        return spawnZ;
    }
}
