using UnityEngine;
using System.Collections;

/// <summary>
/// BodyHandler class handles body's damage models and informing GameMaster when this body is dead.
/// </summary>
public class BodyHandler : MonoBehaviour {

	private int health = 10; //max health is 10
	private int deadHealth = 0; //This body dies at 0 health
	private bool dead = false; //false = alive, true = dead

	private const string ENTITY_DEATH_INFORM_METHOD = "entityDeathInform"; //method in GameMaster to inform when health = 0

	public GameObject gameMaster; //GameMaster object that is currently in the scene.
	
	/// <summary>
	/// Deals damage to the body and checks if body is dead.
	/// </summary>
	/// <param name = 'damageAmount'> Damage amount to subtract from health </param>
	public void damage(int damageAmount)
	{
		//If body is still alive
		if(dead == false)
		{
			health -= damageAmount; //deal damage

			//If body is dead
			if(health <= deadHealth)
			{
				dead = true;
				gameMaster.SendMessage(ENTITY_DEATH_INFORM_METHOD); //send message to GameMaster informing of death
			}
		}
	}
}
