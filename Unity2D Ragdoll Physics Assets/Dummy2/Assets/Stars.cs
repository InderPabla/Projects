using UnityEngine;
using System.Collections;

public class Stars : MonoBehaviour 
{
	public Sprite[] stars = new Sprite[4];
	public int index = 0;
	private SpriteRenderer spriteRenderer;

	void Start () 
	{
		spriteRenderer = GetComponent<SpriteRenderer>();
		spriteRenderer.sprite = stars[index];

	}

	void Update () 
	{
	
	}

	public void setStar(int index)
	{
		spriteRenderer.sprite = stars[index];
	}
}
