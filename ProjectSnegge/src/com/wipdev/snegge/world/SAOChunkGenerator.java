package com.wipdev.snegge.world;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class SAOChunkGenerator extends ChunkGenerator{

	@Override
	public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
		ChunkData chunk = createChunkData(world);

        for (int X = 0; X < 16; X++)
            for (int Z = 0; Z < 16; Z++) {
                final int currentHeight = 64;
                chunk.setBlock(X, currentHeight, Z, Material.GRASS);
                chunk.setBlock(X, currentHeight-1, Z, Material.DIRT);
                for (int i = currentHeight-2; i > 0; i--)
                    chunk.setBlock(X, i, Z, Material.STONE);
                chunk.setBlock(X, 0, Z, Material.BEDROCK);
            }
        return chunk;
	}
	
}
