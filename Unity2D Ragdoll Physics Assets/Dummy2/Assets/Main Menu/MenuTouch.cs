﻿using UnityEngine;
using System.Collections;

public class MenuTouch : MonoBehaviour {

	public GameObject mainMenu_Prefab = null;
	public GameObject levelPrefab = null;
	

	private Camera mainCamera;
	private const string CHANGE_LEVEL_NAMES_METHOD = "changeLevelNames";
	private const string LOAD_LEVEL_METHOD = "loadLevel";
	private const string PLAY_BUTTON = "PlayButton";
	private const string NEXT_BUTTON = "Next";
	private const string PREVIOUS_BUTTON = "Previous";
	private const string LEVEL_1 = "Level_1";

	private GameObject tempObject = null;
	private bool cameraLoaded = false;
	private int index = 0;
	void Start () 
	{
		tempObject = Instantiate(mainMenu_Prefab) as GameObject;
	}

	void Update () 
	{
		if (Input.GetKeyDown(KeyCode.Escape)) 
		{ 
			Application.Quit(); 
		}

		if(cameraLoaded == true)
		{
			Vector3 touchPosition = mainCamera.ScreenToWorldPoint(Input.mousePosition);
			
			if(Input.GetMouseButtonUp(0))
			{
				RaycastHit2D hit = Physics2D.Raycast(touchPosition, Vector2.zero);

				if(hit.transform!=null)
				{
					string hitName = hit.collider.name;

					if(hitName.Equals(PLAY_BUTTON))
					{
						index = 0;

						Invoke(LOAD_LEVEL_METHOD,0.25f);
						//Application.LoadLevel(LEVEL_1);
					}
					else if(hitName.Equals(NEXT_BUTTON))
					{
						index ++;
						if(index>=2)
						{
							index --;
						}
						else
						{
							Invoke(LOAD_LEVEL_METHOD,0.25f);
						}
					}
					else if(hitName.Equals(PREVIOUS_BUTTON))
					{
						index --;
						if(index<0)
						{
							index ++;
						}
						else
						{
							Invoke(LOAD_LEVEL_METHOD,0.25f);
						}						
					}
					else
					{
						Application.LoadLevel(int.Parse(hitName));
						Debug.Log(hitName);
					}
				}
			}
		}
		else
		{
			if(Camera.main!=null)
			{
				this.mainCamera = Camera.main;
				cameraLoaded = true;
			}
		}
	}

	private void loadLevel()
	{
		Destroy(tempObject);
		tempObject = Instantiate(levelPrefab) as GameObject;
		tempObject.transform.GetChild(1).SendMessage(CHANGE_LEVEL_NAMES_METHOD,index);
	}
}
