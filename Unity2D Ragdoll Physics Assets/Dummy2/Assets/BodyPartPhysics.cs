using UnityEngine;
using System.Collections;

public class BodyPartPhysics : MonoBehaviour {

	private bool broken = false;
	private HingeJoint2D joint;

	public int damage = 10;
	public float breakForce = 250000f;
	public GameObject bloodSplat = null;

	private const string DAMAGE_METHOD = "damage";

	void Start () 
	{
		joint = GetComponent<HingeJoint2D>();
	}

	void FixedUpdate () 
	{
		if(broken == false)
		{
			Vector2 forceOnJoint = Vector2.zero;

			forceOnJoint = joint.GetReactionForce (Time.deltaTime);

			float forceOnJointMagnitude = forceOnJoint.sqrMagnitude;

			if(forceOnJointMagnitude>breakForce)
			{
				broken = true;
				Destroy(joint);
				GameObject blood = Instantiate(bloodSplat,transform.position,transform.rotation) as GameObject;
				transform.parent.SendMessage(DAMAGE_METHOD,damage);
			}
		}

	}
}
