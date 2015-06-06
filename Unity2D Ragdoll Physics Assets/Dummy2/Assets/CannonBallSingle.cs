using UnityEngine;
using System.Collections;

public class CannonBallSingle : MonoBehaviour {

	private Transform mainCamera;
	private const string TRACK_OBJECTS_METHOD = "trackObjects";

	void Start () 
	{
		mainCamera = Camera.main.transform;
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public void fireAmmo(CannonAmmoPhysics ammoPhysics)
	{
		transform.position = ammoPhysics.positionOfAction;
		transform.rigidbody2D.velocity = ammoPhysics.velocity;
		mainCamera.SendMessage(TRACK_OBJECTS_METHOD,new Transform[]{transform});
	}
}
