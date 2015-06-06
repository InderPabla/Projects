using UnityEngine;
using System.Collections;

/// <summary>
/// CannonBallSingle class is a script attached to a single cannon ball.
/// </summary>
public class CannonBallSingle : MonoBehaviour {

	private Transform mainCamera; //Transform of the main camera

	private const string TRACK_OBJECTS_METHOD = "trackObjects"; //Method in CameraTracker to track this object

	/// <summary>
	/// Initialize components.
	/// </summary>
	void Start () 
	{
		mainCamera = Camera.main.transform;
	}

	/// <summary>
	/// Method is called from CannonAmmoHandler to set ammo physics.
	/// </summary>
	/// <param name = 'ammoPhysics'> Physics to be set upon this object. </param>
	public void fireAmmo(CannonAmmoPhysics ammoPhysics)
	{
		transform.position = ammoPhysics.positionOfAction; //postion set to position of action
		transform.rigidbody2D.velocity = ammoPhysics.velocity; //ridigbody2D velocity to given ammo velocity
		mainCamera.SendMessage(TRACK_OBJECTS_METHOD,new Transform[]{transform}); //inform CameraTracker to track this object
	}
}
