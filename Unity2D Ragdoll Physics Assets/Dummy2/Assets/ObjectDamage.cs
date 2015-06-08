using UnityEngine;
using System.Collections;

public class ObjectDamage : MonoBehaviour {
	public Sprite[] objects = new Sprite[3];
	int index = 0;
	SpriteRenderer spriteRenderer;
	//private GameObject currentObject;

	void Start () 
	{
		spriteRenderer = gameObject.GetComponent<SpriteRenderer>();
	}

	void Update () 
	{
	
	}

	void OnCollisionEnter2D(Collision2D collision)
	{
		float otherMass; // other object's mass
		if (collision.rigidbody)
			otherMass = collision.rigidbody.mass;
		else 
			otherMass = 10; // static collider means huge mass
		float force = collision.relativeVelocity.sqrMagnitude * otherMass;
		Debug.Log(force);

		if(force>2000)
		{
			index += 3;
		}
		else if (force>1000)
		{
			index += 2;
		}
		else if (force>500)
		{
			index += 1;
		}

		if(index>=3)
		{
			Destroy(gameObject);
		}
		else
		{
			spriteRenderer.sprite = objects[index];
		}
	}
}
