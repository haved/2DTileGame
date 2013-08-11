package me.engine.physics;

import java.util.ArrayList;


/**
 * Interfacet som brukes i kollisjons-sammenheng for MovingEntity
 * 
 * @author Havard
 */
public interface CollisionPool
{
	/**
	 * Legger til newCollider i listen over Collider's
	 * 
	 * @param newCollider
	 */
	public void addCollider(Collider newCollider);
	
	/**
	 * Fjerner newCollider fra listen over Collider's
	 * 
	 * @param newCollider
	 */
	public void removeCollider(Collider newCollider);
	
	/**
	 * @return En ArrayList<Collider> med alle colliderene
	 */
	public ArrayList<Collider> getColliders();
	
	/**
	 * @param collider blir sjekket opp mot lista
	 * @return sant, hvis parameteret finnes i CollisionPool'en
	 */
	public boolean hasCollider(Collider collider);
	
	/**
	 * @return en TileMap hvis collisionPool'en har en
	 */
	public TileMap getTileMap();
	
	/**
	 * @return sant hvis den har en TileMap
	 */
	public boolean hasTileMap();
}
