using UnityEngine;
using System.Collections;

/// <summary>
/// GameMaster class handles level transitions, level operation, and entity deaths.
/// </summary>
public class GameMaster : MonoBehaviour 
{
	public int entityCount = 0; //entity count in the scene
	public GameObject slowMotionEnd; //slow motion layer prefab

	private bool finished = false; //false = level still playing, true = level ended
	private bool childrenLoaded = false; //false = children not loaded, true = children loaded

	private const int NUMBER_OF_LEVELS = 2; //total numbers of levels in the game
	private const float LEVEL_END_TIME_SCALE = 0.25f; //change world time scale after level end

	private const string CHANGE_GRAVITY_METHOD = "changeGravity"; //Method in WorldPhysics 
	private const string CHANGE_TIME_SCALE_METHOD = "changeTimeScale"; //Method in WorldPhysics 

	private Transform worldPhysics = null; //world physics object in the scene (child of GameMaster)
	
	/// <summary>
	/// Update is called once per frame.
	/// </summary>
	void Update () 
	{
		//load children if false
		if(childrenLoaded == false)
		{
			getChildren();
		}
	}

	/// <summary>
	/// Method is called from BodyHandler to inform when an entity dies.
	/// </summary>
	public void entityDeathInform()
	{
		//if level is not finished
		if(finished == false)
		{
			entityCount --; //decrease entity count

			//if entity count reaches 0
			if(entityCount == 0)
			{
				finished = true; //level is finished
				worldPhysics.SendMessage(CHANGE_TIME_SCALE_METHOD,LEVEL_END_TIME_SCALE); //set new time scale
				GameObject slowMotionEndLayer = Instantiate(slowMotionEnd) as GameObject; // create slowmo layer
			}
		}
	}

	/// <summary>
	/// Method which initializes children and its components to variables
	/// </summary>
	private void getChildren()
	{
		if(transform.GetChild(0)!=null)
		{
			worldPhysics = transform.GetChild(0);
			childrenLoaded = true;
		}
	}


}
