using UnityEngine;
using System.Collections;

public class WorldPhysics : MonoBehaviour 
{
	public float gravity = -9.81f;
	public float timeScale = 1;

	void Start () 
	{
		Time.timeScale = this.timeScale;
		Physics2D.gravity = new Vector2(0,gravity);
	}

	public void changeGravity(float newGravity)
	{
		Physics2D.gravity = new Vector2(0,newGravity);
	}

	public void changeTimeScale(float newTimeScale)
	{
		Time.timeScale = newTimeScale;
	}
}
