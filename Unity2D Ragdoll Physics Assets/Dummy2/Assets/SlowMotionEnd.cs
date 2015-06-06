using UnityEngine;
using System.Collections;

public class SlowMotionEnd : MonoBehaviour {
	
	void Start () {
		Time.timeScale = 0.25f;
		renderer.material.color  = new Color(1f,1f,1f,1f);
	}

	void Update () {
		Color color = renderer.material.color;

		float r = updateColor(color.r,0f,-1);
		float g = updateColor(color.g,0.75f,-0.5f);
		float b = updateColor(color.b,0.90f,-0.5f);
		float a = updateColor(color.a,0.25f,-2);
		renderer.material.color  = new Color(r,g,b,a);
	}

	float updateColor(float color,float stop, float amountPerTime){
		color+=amountPerTime*Time.deltaTime;
		if(amountPerTime>0){
			if(color>=stop)
				return stop;
		}
		else if(amountPerTime<0){
			if(color<=stop)
				return stop;
		}
		return color;
	}

}
