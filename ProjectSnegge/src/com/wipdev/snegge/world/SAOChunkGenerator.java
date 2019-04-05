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
                if(isInRadius(x, z, X, z, 1000)) {
                	final int currentHeight = 64;
                    chunk.setBlock(X, currentHeight, Z, Material.GRASS);
                    chunk.setBlock(X, currentHeight-1, Z, Material.DIRT);
                    for (int i = currentHeight-2; i > 0; i--)
                        chunk.setBlock(X, i, Z, Material.STONE);
                    chunk.setBlock(X, 0, Z, Material.BEDROCK);
                }else {
                	//air
                }
            }
        return chunk;
	}
	
	
	private boolean isInRadius(int chunkX,int chunkZ,int x,int z,int radius){
		int xOff = (int) (Math.sqrt(Math.pow(chunkX,2))*16 +x);
		int zOff = (int) (Math.sqrt(Math.pow(chunkZ,2))*16 +z);
		
		int off = (int) Math.round(Math.sqrt(Math.pow(xOff, 2)+Math.pow(zOff, 2)));
		return off<=radius;
	}
}
