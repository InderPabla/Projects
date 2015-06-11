using UnityEngine;
using System.Collections;

public class ObjectDamage : MonoBehaviour {
	public Sprite[] objects = new Sprite[3];
	int index = 0;
	SpriteRenderer spriteRenderer;

	public GameObject woodChips = null;
	//private GameObject currentObject;

	void Start () 
	{
		spriteRenderer = gameObject.GetComponent<SpriteRenderer>();
	}

	void OnCollisionEnter2D(Collision2D collision)
	{
		/*bool damaged = false;
		float otherMass; // other object's mass
		if (collision.rigidbody)
			otherMass = collision.rigidbody.mass;
		else 
			otherMass = 10; // static collider means huge mass
		float force = collision.relativeVelocity.sqrMagnitude * otherMass;
		Debug.Log(force);



		if(force>1200)
		{
			index += 4;
		}
		else if(force>500)
		{
			index += 3;
			damaged = true;
		}
		else if (force>200)
		{
			index += 2;
			damaged = true;
		}
		else if (force>80)
		{
			index += 1;
			damaged = true;
		}

		if(index>=4)
		{
			GameObject chips = Instantiate(woodChips,transform.position,transform.rotation) as GameObject;
			Destroy(gameObject);
		}
		else if (damaged == true)
		{
			spriteRenderer.sprite = objects[index-1];	
		}*/
	}

	public void damage(int damageLevel)
	{
		index += damageLevel;

		if(index>=4)
		{
			GameObject chips = Instantiate(woodChips,transform.position,transform.rotation) as GameObject;
			Destroy(gameObject);
		}
		else
		{
			if(index-1>=0)
				spriteRenderer.sprite = objects[index-1];	
		}
	}
}
