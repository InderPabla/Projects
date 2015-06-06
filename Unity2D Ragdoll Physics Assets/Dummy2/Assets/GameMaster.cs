using UnityEngine;
using System.Collections;

public class GameMaster : MonoBehaviour 
{
	public int entityCount = 0;
	public GameObject slowMotionEnd;

	private bool finished = false;
	private bool childrenLoaded = false;

	private const int NUMBER_OF_LEVELS = 2;
	private const float LEVEL_END_TIME_SCALE = 0.25f;

	private const string CHANGE_GRAVITY_METHOD = "changeGravity";
	private const string CHANGE_TIME_SCALE_METHOD = "changeTimeScale";

	private Transform worldPhysics = null;

	void Start () 
	{
	
	}

	void Update () 
	{
		//Debug.Log(1.0f / Time.deltaTime);
		if(childrenLoaded == false)
		{
			getChildren();
		}
	}

	public void entityDeathInform()
	{
		if(finished == false)
		{
			entityCount --;

			if(entityCount == 0)
			{
				finished = true;
				worldPhysics.SendMessage(CHANGE_TIME_SCALE_METHOD,LEVEL_END_TIME_SCALE);
				GameObject slowMotionEndLayer = Instantiate(slowMotionEnd) as GameObject;
				//Debug.Log("FINISHED");
			}
		}
	}

	private void getChildren()
	{
		if(transform.GetChild(0)!=null)
		{
			worldPhysics = transform.GetChild(0);
			childrenLoaded = true;
		}
	}


}
