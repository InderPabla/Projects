using UnityEngine;
using System.Collections;

public class BodyHandler : MonoBehaviour {
	private int health = 10;
	private int deadHealth = 0;
	private bool dead = false;

	private const string ENTITY_DEATH_INFORM_METHOD = "entityDeathInform";

	public GameObject gameMaster;

	public void damage(int damageAmount)
	{
		if(dead == false)
		{
			health -= damageAmount;

			if(health <= deadHealth)
			{
				dead = true;
				gameMaster.SendMessage(ENTITY_DEATH_INFORM_METHOD);
			}
		}
	}
}
