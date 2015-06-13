using UnityEngine;
using System.Collections;

/// <summary>
/// BodyHandler class handles body's damage models and informing GameMaster when this body is dead.
/// </summary>
public class BodyHandler : MonoBehaviour {

	private int health = 10; //max health is 10
	private int deadHealth = 0; //This body dies at 0 health
	private bool dead = false; //false = alive, true = dead
	private int scorePerDestroy = 100;

	private const string ENTITY_DEATH_INFORM_METHOD = "entityDeathInform"; //method in GameMaster to inform when health = 0
	private const string ADD_SCORE = "addScore"; //Method in GameMaster to increment score

	public GameObject gameMaster; //GameMaster object that is currently in the scene.
	
	/// <summary>
	/// Deals damage to the body and checks if body is dead.
	/// </summary>
	/// <param name = 'damageAmount'> Damage amount to subtract from health </param>
	public void damage(int damageAmount)
	{
		gameMaster.SendMessage(ADD_SCORE,scorePerDestroy);
		//If body is still alive
		if(dead == false)
		{
			health -= damageAmount; //deal damage

			//If body is dead
			if(health <= deadHealth)
			{
				dead = true;
				gameMaster.SendMessage(ADD_SCORE,scorePerDestroy*2);
				gameMaster.SendMessage(ENTITY_DEATH_INFORM_METHOD); //send message to GameMaster informing of death
			}
		}
	}
}
