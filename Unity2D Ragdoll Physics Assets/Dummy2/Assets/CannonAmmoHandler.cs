using UnityEngine;
using System.Collections;

public class CannonAmmoHandler : MonoBehaviour 
{
	private int maxAmmoCount = 10;
	private int ammoCount = 0;
	private int index = 0;

	private const string FIRE_AMMO = "fireAmmo";

	public Transform[] cannonAmmos = new Transform[10];
	
	void Start () 
	{
		for(int i=0;i<maxAmmoCount;i++)
		{
			if(cannonAmmos[i] != null)
			{
				ammoCount++;
			}
			else
			{
				break;
			}
		}
	}
	
	void Update () 
	{
		
	}
	
	public void fireCannonAmmo(CannonAmmoPhysics ammoPhysics)
	{
		if(ammoCount>0)
		{
			if(index<ammoCount)
			{
				cannonAmmos[index].SendMessage(FIRE_AMMO,ammoPhysics);
				index++;
			}
		}
	}
}
