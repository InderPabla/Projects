using UnityEngine;
using System.Collections;

public class ScoreTracker : MonoBehaviour {

	private int score = 0;
	void Start () 
	{
	
	}

	void Update () 
	{
	
	}

	void addScore (int scoreAdd)
	{
		score += scoreAdd;
		Debug.Log(score);
	}
}
