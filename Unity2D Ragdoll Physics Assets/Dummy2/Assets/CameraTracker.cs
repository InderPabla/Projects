using UnityEngine;
using System.Collections;

/// <summary>
/// CameraTracker class handles camera tracking of objects and the overall position of the camera.
/// </summary>
public class CameraTracker : MonoBehaviour 
{
	public Transform boundLeft = null; //left most camera restriction (child of this camera object)
	public Transform boundRight = null; //right most camera restriction (child of this camera object)

	private Transform[] objectsTemp; //temporary objects to track

	private float maxLeft = 0f; //max left x value
	private float maxRight = 0f; //max right x value
	private float maxDown = 0f; //max bottom y value
	
	private float cameraHeight = 0f; //height of the camera
	private float cameraWidth = 0f; //width of the camera
	private float currentLeft = 0f; //current left most part of the camera
	private float currentRight = 0f; //current right most part of the camera
	private float currentDown = 0f; //current bottom most part of the camera
	private float followThreshold = 0.25f; //min distance before tracking starts
	private float warpSpeed = 100f; //NOT USED - speed to warp to the ball 

	private bool follow = false; //false = do not track objects, true = track objects
	private bool warp = true; //false = do not jump camera to objects, true = jump camera to objects
	
	/// <summary>
	/// Initialize components.
	/// </summary>
	void Start () 
	{
		//initializations
		maxLeft = boundLeft.position.x; 
		maxRight = boundRight.position.x;
		maxDown = transform.position.y;

		calculateCurrentCameraBounds(); //calculate current camera bounds
	}

	/// <summary>
	/// Update is called once per frame.
	/// </summary>
	void Update () 
	{
		calculateCurrentCameraBounds(); //calculate current camera bounds

		//if follow camera is true
		if(follow == true)
		{
			Vector2 position2D = calculateAverageObjectsPosition(); //calculate average position of the objects to track
			Vector3 position3D = new Vector3(position2D.x,position2D.y,-100); //3D vector with -100 on the z-axis

			Vector3 newCameraPosition = position3D; 

			//if new camera position's y is less than max down allowed then set to max down
			if(newCameraPosition.y < maxDown)
				newCameraPosition.y = maxDown;

			//if warp is false
			if(warp == false)
			{
				float diff = Mathf.Abs(newCameraPosition.x - transform.position.x); //get absolute difference in camera position x-axis and new camera position x-axis
				newCameraPosition.x = transform.position.x; //important to bring back to initial camera x

				//if difference is <= threshold, then commence warping
				if(diff<=followThreshold)
				{
					newCameraPosition.x = transform.position.x; //move new camera position to current camera position (warpped)
					warp = true;
				}
			}
			//else warp is true
			else
			{
				//if left most part of camera out of bounds
				if(position3D.x-(cameraWidth/2f)<maxLeft)
				{
					newCameraPosition.x = (maxLeft + (cameraWidth/2f)); //move camera back to normal bounds
				}
				//if right most part of camera out of bounds
				else if(position3D.x+(cameraWidth/2f)>maxRight)
				{
					newCameraPosition.x = (maxRight - (cameraWidth/2f)); //move camera back to normal bounds
				}
			}
			transform.position = newCameraPosition; //set camera to new camera position calculated
		}

		//if mouse down then interrupt camera tracking
		if(Input.GetMouseButtonDown(0))
		{
			follow = false;	
		}

		//fixCurrentCameraPosition(); //fix any irregularities in camera position
	}

	/// <summary>
	/// Calculate current boundaries.
	/// </summary>
	private void calculateCurrentCameraBounds()
	{
		cameraHeight = 2f*camera.orthographicSize; //camera height
		cameraWidth = cameraHeight*camera.aspect; //camera width
		currentLeft = transform.position.x - cameraWidth/2f; //left most part of camera (x-axis)
		currentRight = transform.position.x + cameraWidth/2f; //right most part of camera (x-axis)
		currentDown = transform.position.y; //bottom most part of camera (y-axis)
	}

	/// <summary>
	/// Calculate average object positions (there might be many objects to track at one time.
	/// </summary>
	private Vector2 calculateAverageObjectsPosition()
	{
		Vector2 averagePosition = Vector2.zero;

		//if more then one object to track
		if(objectsTemp.Length>1)
		{
			//calculate average position of all objects
			for(int i=0;i<objectsTemp.Length;i++)
			{
				averagePosition.x += objectsTemp[i].position.x;
				averagePosition.y += objectsTemp[i].position.y;
			}
			averagePosition.x /= objectsTemp.Length;
			averagePosition.y /= objectsTemp.Length;
		}
		//no need to calculate average if there is only 1 object
		else
		{
			averagePosition.x = objectsTemp[0].position.x;
			averagePosition.y = objectsTemp[0].position.y;
		}

		return averagePosition;
	}

	/// <summary>
	/// Method is called from the the object which wants to be tracked.
	/// </summary>
	/// <param name = 'objects'> Transform of the objects given to tracked </param>
	public void trackObjects(Transform[] objects)
	{
		//copy objects to objectsTemp
		objectsTemp = new Transform[objects.Length];

		for(int i=0;i<objects.Length;i++)
		{
			objectsTemp[i] = objects[i];
		}

		follow = true; //start tracking objects
		warp = false; //resert warp
	}

	/// <summary>
	/// Fix camera irregularities if any exist.
	/// </summary>
	public void fixCurrentCameraPosition()
	{
		//if both max left and max right are NOT inside camera
		//making sure due to camera jitter effects if both are inside camera bounds
		if(!(currentLeft<maxLeft && currentRight>maxRight))
		{
			//if left most part of camera out of bounds
			if(currentLeft<maxLeft)
			{
				Vector3 position = transform.position;
				position.x = (maxLeft + (cameraWidth/2f));
				position.z = -100;
				if(position.y<maxDown)
					position.y = maxDown;
				transform.position = position;
			}

			//if right most part of camera out of bounds
			else if(currentRight>maxRight)
			{
				Vector3 position = transform.position;
				position.x = (maxRight - (cameraWidth/2f));
				position.z = -100;
				if(position.y<maxDown)
					position.y = maxDown;
				transform.position = position;
			}

			else if(currentDown < maxDown)
			{
				Vector3 position = transform.position;
				position.y = maxDown;
				transform.position = position;
			}
		}
	}

	/// <summary>
	/// Fix camera irregularities as if the given vector was the new camera position.
	/// </summary>
	/// <param name = 'newCameraPosition'> The vector to check for irregularities and set to camera position. </param>
	public void fixGivenCameraPosition(Vector3 newCameraPosition)
	{
		//calculate new current  bounds
		currentLeft = newCameraPosition.x - cameraWidth/2f; 
		currentRight = newCameraPosition.x + cameraWidth/2f;
	
		//if both max left and max right are NOT inside camera
		//making sure due to camera jitter effects if both are inside camera bounds
		if(!(currentLeft<maxLeft && currentRight>maxRight))
		{
			//if left most part of camera out of bounds
			if(currentLeft<maxLeft)
			{
				Vector3 position = transform.position;
				position.x = (maxLeft + (cameraWidth/2f));
				position.z = -100;
				position.y = maxDown;
				transform.position = position;
			}

			//if right most part of camera out of bounds
			else if(currentRight>maxRight)
			{
				Vector3 position = transform.position;
				position.x = (maxRight - (cameraWidth/2f));
				position.z = -100;
				position.y = maxDown;
				transform.position = position;
			}

			//set new camera position to camera
			else
			{
				transform.position = newCameraPosition;
			}
		}	
	}
}