using UnityEngine;
using System.Collections;

public class CannonBallSingle : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public void fireAmmo(CannonAmmoPhysics ammoPhysics)
	{
		transform.position = ammoPhysics.positionOfAction;
		transform.rigidbody2D.velocity = ammoPhysics.velocity;
	}
}
