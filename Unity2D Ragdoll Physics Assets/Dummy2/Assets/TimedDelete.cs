using UnityEngine;
using System.Collections;

public class TimedDelete : MonoBehaviour 
{
	public float timeToDelete = 1f;

	void Start () 
	{
		Invoke("Delete",timeToDelete);
	}


	void Delete()
	{
		Destroy(gameObject);
	}
}
