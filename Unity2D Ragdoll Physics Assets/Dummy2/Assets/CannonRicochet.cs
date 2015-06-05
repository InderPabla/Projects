using UnityEngine;
using System.Collections;

public class CannonRicochet : MonoBehaviour {

	private float time = 0f;
	private float timeMax = 0.25f;
	private bool animate = false;
	private bool state = false;
	void Start () {
	
	}

	void Update () {
		if(animate==true){
			if(state==false){
				
				transform.localPosition -= Time.deltaTime*transform.right;
				time+=Time.deltaTime;
				if(time>=timeMax){
					time = 0;
					state = true;
				}//time is greater than timeMax
			}//state is false
			else{
				transform.localPosition += Time.deltaTime*transform.right;
				time+=Time.deltaTime;
				if(time>=timeMax){
					time = 0;
					state = false;
					animate = false;
				}//time is greater than timeMax
			}//state is false
		}//cannonAni is true
	}

	public void cannonRicochetAnimate()
	{
		animate = true;
	}

}
