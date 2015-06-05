using UnityEngine;
using System.Collections;

public class CannonAmmoPhysics 
{
	public float angleRad = 0f;
	public Vector2 velocity = Vector2.zero;
	public Vector3 positionOfAction = Vector3.zero;

	public CannonAmmoPhysics(float angleRad, Vector2 velocity, Vector3 positionOfAction)
	{
		this.angleRad = angleRad;
		this.velocity = velocity;
		this.positionOfAction = positionOfAction;
	}
}
