using UnityEngine;
using System.Collections;

public class CameraTracker : MonoBehaviour 
{
	public Transform boundLeft = null;
	public Transform boundRight = null;

	private Transform[] objectsTemp;

	private float maxLeft = 0f;
	private float maxRight = 0f;
	private float maxDown = 0f;
	
	private float cameraHeight = 0f;
	private float cameraWidth = 0f;
	private float currentLeft = 0f;
	private float currentRight = 0f;
	private float currentDown = 0f;
	private float warpSpeed = 100f;

	private bool follow = false;
	private bool warp = false;

	void Start () 
	{
		maxLeft = boundLeft.position.x;

		maxRight = boundRight.position.x;
		maxDown = transform.position.y;
		calculateCurrentCameraBounds();
	}
	void Update () 
	{
		calculateCurrentCameraBounds();
		if(follow == true)
		{
			Vector2 position2D = calculateAverageObjectsPosition();
			Vector3 position3D = new Vector3(position2D.x,position2D.y,-100);
			float timeStep = warpSpeed * Time.deltaTime;
			Vector3 newCameraPosition = position3D;

			if(newCameraPosition.y < maxDown)
				newCameraPosition.y = maxDown;

			if(warp == true)
			{
				float diff = Mathf.Abs(newCameraPosition.x - transform.position.x);
				newCameraPosition.x = transform.position.x;
				if(diff<=0.25f)
				{
					warp = false;
				}
			}
			else
			{
				if(position3D.x-(cameraWidth/2f)<maxLeft)
				{
					newCameraPosition.x = (maxLeft + (cameraWidth/2f));
				}
				else if(position3D.x+(cameraWidth/2f)>maxRight)
				{
					newCameraPosition.x = (maxRight - (cameraWidth/2f));
				}
			}
			transform.position = newCameraPosition;
		}
		if(follow == true)
		{
			if(Input.GetMouseButtonDown(0))
			{
				follow = false;	
			}
		}

		fixCurrentCameraPosition();
	}

	private void calculateCurrentCameraBounds()
	{
		cameraHeight = 2f*camera.orthographicSize;
		cameraWidth = cameraHeight*camera.aspect;
		currentLeft = transform.position.x - cameraWidth/2f;
		currentRight = transform.position.x + cameraWidth/2f;
		currentDown = transform.position.y;
	}

	private Vector2 calculateAverageObjectsPosition()
	{
		Vector2 averagePosition = Vector2.zero;

		if(objectsTemp.Length>1)
		{
			for(int i=0;i<objectsTemp.Length;i++)
			{
				averagePosition.x += objectsTemp[i].position.x;
				averagePosition.y += objectsTemp[i].position.y;
			}
			averagePosition.x /= objectsTemp.Length;
			averagePosition.y /= objectsTemp.Length;
		}
		else
		{
			averagePosition.x = objectsTemp[0].position.x;
			averagePosition.y = objectsTemp[0].position.y;
		}

		return averagePosition;
	}

	public void trackObjects(Transform[] objects)
	{
		objectsTemp = new Transform[objects.Length];
		for(int i=0;i<objects.Length;i++)
		{
			objectsTemp[i] = objects[i];
		}
		follow = true;
		warp = true;
	}

	public void fixCurrentCameraPosition()
	{
		if(!(currentLeft<maxLeft && currentRight>maxRight))
		{
			if(currentLeft<maxLeft){
				Vector3 position = transform.position;
				position.x = (maxLeft + (cameraWidth/2f));
				position.z = -100;
				if(position.y<maxDown)
					position.y = maxDown;
				transform.position = position;
			}
			if(currentRight>maxRight)
			{
				Vector3 position = transform.position;
				position.x = (maxRight - (cameraWidth/2f));
				position.z = -100;
				if(position.y<maxDown)
					position.y = maxDown;
				transform.position = position;
			}
		}
	}

	public void fixGivenCameraPosition(Vector3 newCameraPosition)
	{
		currentLeft = newCameraPosition.x - cameraWidth/2f;
		currentRight = newCameraPosition.x + cameraWidth/2f;
	
		if(!(currentLeft<maxLeft && currentRight>maxRight))
		{
			if(currentLeft<maxLeft)
			{
				Vector3 position = transform.position;
				position.x = (maxLeft + (cameraWidth/2f));
				position.z = -100;
				if(position.y<maxDown)
					position.y = maxDown;
				transform.position = position;
			}
			else if(currentRight>maxRight)
			{
				Vector3 position = transform.position;
				position.x = (maxRight - (cameraWidth/2f));
				position.z = -100;
				if(position.y<maxDown)
					position.y = maxDown;
				transform.position = position;
			}
			else
			{
				transform.position = newCameraPosition;
			}
		}	
	}
}