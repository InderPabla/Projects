﻿using UnityEngine;
using System.Collections;

public class CannonTouch : MonoBehaviour 
{
	private Camera mainCamera = null;

	private Transform parent = null;
	private Transform cannonTouch = null;
	private Transform cannonNose = null;

	private LineRenderer line = null;
	private LineRenderer cannonNoseLine = null;

	private bool childrenLoaded = false;
	private bool touch = false;
	private bool touchCamera = false;
	private bool touchLock = false;

	private const string CANNON_TOUCH_NAME = "CannonTouch";
	private const string FIRE_CANNON_AMMO_METHOD = "fireCannonAmmo";
	private const string TOGGLE_TOUCH_LOCK_METHOD = "toggleTouchLock";
	private const string CANNON_RICOCHET_ANIMATE_METHOD = "cannonRicochetAnimate";
	private const string TRANSFORM_TO_FOLLOW_METHOD = "transformToFollow";
	private const string FIX_GIVEN_CAMERA_POSITION_METHOD = "fixGivenCameraPosition";

	private float angleRad = 0f;
	private float touchLockToggleWaitTime = 2f;
	private Vector2 velocity = Vector2.zero;
	private Vector3 touchPosition = Vector3.zero;

	public Transform cannonAmmo = null;
	public GameObject cannonFire = null;

	public float lineDistance = 0f;
	public float margin  = 10f;
	public int numberOfPoints = 10;

	public Material lineShaderMaterial;

	void Start ()
	{
		this.mainCamera = Camera.main;
		this.line = this.GetComponent<LineRenderer>();
		this.line.material = lineShaderMaterial;
		this.parent = transform.parent;
	}

	void Update () 
	{
		initialTouch();
		afterTouch();
		releaseTouch();

		if(touchCamera == true)
		{
			Vector3 touchPosition2 = mainCamera.ScreenToWorldPoint(Input.mousePosition);
			Vector3 newCameraPosition = Vector3.zero;
			newCameraPosition.x = Camera.main.transform.position.x + (touchPosition.x-touchPosition2.x)*0.5f;
			newCameraPosition.z = -100f;
			newCameraPosition.y = 0f;

			mainCamera.SendMessage(FIX_GIVEN_CAMERA_POSITION_METHOD,newCameraPosition);
		}

		if(childrenLoaded == false)
		{
			getChildren();
		}
	}

	private void releaseTouch()
	{
		if(Input.GetMouseButtonUp(0))
		{
			if(touch == true)
			{
				line.SetPosition(0,Vector3.zero);
				line.SetPosition(1,Vector3.zero);


				for(int i = 0;i<numberOfPoints;i++)
				{
					cannonNoseLine.SetPosition(i,Vector3.zero);
				}

				CannonAmmoPhysics ammoPhysics = new CannonAmmoPhysics(angleRad,velocity,cannonNose.position);
				cannonAmmo.SendMessage(FIRE_CANNON_AMMO_METHOD,ammoPhysics);

				GameObject fire = Instantiate(cannonFire) as GameObject;
				fire.transform.position = cannonNose.position;
				fire.transform.eulerAngles = new Vector3(0,0,cannonFire.transform.eulerAngles.z + (Mathf.Rad2Deg*angleRad));
				fire.SendMessage(TRANSFORM_TO_FOLLOW_METHOD,cannonNose);

				touch = false;
				toggleTouchLock();
				Invoke (TOGGLE_TOUCH_LOCK_METHOD,touchLockToggleWaitTime);

				this.SendMessage(CANNON_RICOCHET_ANIMATE_METHOD);

				touchLock = true;
			}
			touchCamera = false;
		}
	}

	private void initialTouch()
	{
		if(touch == false)
		{
			if(Input.GetMouseButtonDown(0))
			{
				if(touchLock == false){
					touchPosition = mainCamera.ScreenToWorldPoint(Input.mousePosition);
					RaycastHit2D hit = Physics2D.Raycast(touchPosition, Vector2.zero);
					
					if(hit.transform!=null)
					{
						string hitName = hit.collider.name;
						
						if(hitName.Equals(CANNON_TOUCH_NAME))
						{
							touch = true;
						}
						else
						{
							touchCamera = true;
						}
					}
					else
					{
						touchCamera = true;
					}
				}
				else
				{
					touchCamera = true;
				}
			}
		}
		else
		{
			if(Input.GetMouseButtonDown(0))
			{
				touchCamera = true;
			}
		}
	}

	private void afterTouch()
	{
		if(touch == true)
		{
			touchPosition = mainCamera.ScreenToWorldPoint(Input.mousePosition);

			angleRad = Mathf.Atan2((parent.position.y-touchPosition.y) , (parent.position.x-touchPosition.x));
			float angleDeg = Mathf.Rad2Deg*angleRad;

			transform.eulerAngles = new Vector3(0,0,angleDeg);

			float distance = lineSet(cannonTouch.position,touchPosition,angleRad + 180*Mathf.Deg2Rad);

			velocity = new Vector2(Mathf.Cos(angleRad)*margin*distance,Mathf.Sin(angleRad)*margin*distance);
			drawProjectileLine(velocity);
		}
	}

	private float lineSet(Vector3 from, Vector3 to, float angleRad)
	{
		line.SetPosition(0,from);

		to.z = 0;
		float distance = Vector3.Distance(from,to);
		if(distance>lineDistance)
		{
			distance = lineDistance;
		}

		Vector2 newTo2D = GetPosition(from.x,from.y,angleRad,distance);
		Vector3 newTo3D = new Vector3(newTo2D.x,newTo2D.y,-3);
		line.SetPosition(1,newTo3D);

		return distance;
	}

	private void getChildren()
	{
		if(transform.GetChild(0)!=null && transform.GetChild(1)!=null)
		{
			cannonNoseLine = transform.GetChild(0).GetComponent<LineRenderer>();
			cannonNoseLine.material = lineShaderMaterial;
			cannonNose = transform.GetChild(0);
			cannonTouch = transform.GetChild(1);
			childrenLoaded = true;
		}
	}

	private Vector2 GetPosition(float x,float y, float angle, float distance)
	{
		Vector2 newVector;
		float opposite = Mathf.Sin(angle) * distance; //Get SOH
		float ajacent = Mathf.Cos(angle) * distance; //Get CAH
		newVector.x = x+ajacent; //ajacent : Add to old Vector
		newVector.y = y+opposite; //opposite : Add to old Vector
		return newVector;				
	}

	private void drawProjectileLine(Vector2 velocity)
	{
		float velocityMagnitude = Mathf.Sqrt((velocity.x * velocity.x) + (velocity.y * velocity.y));
		float angleRad = Mathf.Atan2(velocity.y ,velocity.x);
		float fTime = 0;
		cannonNoseLine.SetVertexCount(10);
		for(int i = 0;i<numberOfPoints;i++){
			float dx = velocityMagnitude * fTime * Mathf.Cos(angleRad);
			float dy = velocityMagnitude * fTime * Mathf.Sin(angleRad) - (Physics2D.gravity.magnitude * fTime * fTime / 2.0f);
			Vector3 pos = new Vector3(cannonNose.position.x + dx ,cannonNose.position.y + dy,-3);
			cannonNoseLine.SetPosition(i,pos);
			fTime += 0.075f;
		}
	}

	private void toggleTouchLock()
	{
		touchLock = !touchLock;
	}
}
