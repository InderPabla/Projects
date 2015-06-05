using UnityEngine;
using System.Collections;

public class MenuTouch : MonoBehaviour {

	private Camera mainCamera;

	private const string PLAY_BUTTON = "PlayButton";
	private const string LEVEL_1 = "Level_1";

	void Start () 
	{
		this.mainCamera = Camera.main;
	}

	void Update () 
	{
		Vector3 touchPosition = mainCamera.ScreenToWorldPoint(Input.mousePosition);
		
		if(Input.GetMouseButtonUp(0))
		{
			RaycastHit2D hit = Physics2D.Raycast(touchPosition, Vector2.zero);

			if(hit.transform!=null)
			{
				string hitName = hit.collider.name;

				if(hitName.Equals(PLAY_BUTTON))
					Application.LoadLevel(LEVEL_1);
			}
		}
	}	
}
