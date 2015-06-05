using UnityEngine;
using System.Collections;

public class CannonFire : MonoBehaviour {
	private float particleDecrease = 250f;
	private Vector3 followPosition;
	private bool follow = false;
	private Transform followTransform = null; 

	void Start () {

	}
	

	void Update () {
		particleEmitter.minEmission -= particleDecrease*Time.deltaTime;
		particleEmitter.maxEmission -= particleDecrease*Time.deltaTime;

		if(follow == true)
		{
			transform.position = followTransform.position;
		}

	}

	public void transformToFollow(Transform followTransform)
	{
		this.followTransform = followTransform;
		follow = true;
	}
}
