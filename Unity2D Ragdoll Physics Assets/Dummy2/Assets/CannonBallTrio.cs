using UnityEngine;
using System.Collections;

public class CannonBallTrio : MonoBehaviour {

	bool childrenLoaded = false;
	float[] scalarQuantity = {1f,0.25f,0.5f};

	void Start () {
	
	}

	void Update () {
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
		transform.position = ammoPhysics.positionOfAction;
		for(int i=0;i<3;i++)
		{
			Vector2 velocity = ammoPhysics.velocity;
			velocity = new Vector2(velocity.x*scalarQuantity[i],velocity.y*scalarQuantity[i]);
			Transform child = transform.GetChild(i);
			child.rigidbody2D.velocity = velocity;
		}
	}
}
