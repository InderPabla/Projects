using UnityEngine;
using System.Collections;

public class CannonBallTrio : MonoBehaviour {

	private bool childrenLoaded = false;
	private float[] scalarQuantity = {1f,0.8f,0.9f};

	private Transform mainCamera;

	private const string TRACK_OBJECTS_METHOD = "trackObjects";

	void Start () 
	{
		mainCamera = Camera.main.transform;
	}

	void Update () 
	{
		if(childrenLoaded == false)
		{
			getChildren();
		}
	}

	private void getChildren()
	{
		if(transform.GetChild(0)!=null && transform.GetChild(1)!=null && transform.GetChild(2)!=null)
		{
			childrenLoaded = true;
		}
	}

	public void fireAmmo(CannonAmmoPhysics ammoPhysics)
	{
		Transform[] childArray = new Transform[3]; 
		transform.position = ammoPhysics.positionOfAction;
		for(int i=0;i<3;i++)
		{
			Vector2 velocity = ammoPhysics.velocity;
			velocity = new Vector2(velocity.x*scalarQuantity[i],velocity.y*scalarQuantity[i]);
			Transform child = transform.GetChild(i);
			child.rigidbody2D.velocity = velocity;
			childArray[i] = child;
		}
		mainCamera.SendMessage(TRACK_OBJECTS_METHOD,childArray);
	}
}
