using UnityEngine;
using System.Collections;
using System.IO;

public class LevelDetailHandler 
{

	private string filePath = null;
	private string fileName = "Ragdoll_Settings.txt";

	public float[] levelScores = new float[20];

	private float[,] levelScoresTemplate = new float[,] 
	{ //levels
		{ //level 1
		 	200,300,400 },
		{ //level 2		 
		 	800,900,1000 },
		{ //level 3		 
		 	1000,1200,1400 },
		{ //level 4		 
		 	2000,2400,2500 },
		{ //level 5		 
		 	2500,2700,2900 },
		{ //level 6		 
		 	900,1000,1100 },
		{ //level 7		 
		 	2600,3200,3500 },
		{ //level 8		 
		 	1000,1000,1000 },
		{ //level 9		 
		 	1000,1000,1000 },
		{ //level 10	    
		 	1000,1000,1000 },
		{ //level 11	    
		 	1000,1000,1000 },
		{ //level 12	    
		 	1000,1000,1000 },
		{ //level 13	    
		 	1000,1000,1000 },
		{ //level 14	    
		 	1000,1000,1000 },
		{ //level 15	    
		 	1000,1000,1000 },
		{ //level 16	    
		 	1000,1000,1000 },
		{ //level 17	    
		 	1000,1000,1000 },
		{ //level 18	    
		 	1000,1000,1000 },
		{ //level 19	    
		 	1000,1000,1000 },
		{ //level 20	    
			1000,1000,1000 }
	};

	public LevelDetailHandler ()
	{
		filePath = Application.persistentDataPath + "/" + fileName;
		if(!File.Exists(filePath))
		{
			string[] linesToWrite = new string[20];
			for(int i=0;i<20;i++)
			{
				linesToWrite[i] = "0";
				levelScores[i] = 0;
			}
			File.WriteAllLines(filePath,linesToWrite);

			Debug.Log(filePath);
		}
		else
		{
			string[] linesToRead = File.ReadAllLines(filePath);
			for(int i=0;i<20;i++)
			{
				levelScores[i] = float.Parse(linesToRead[i]);
			}
		}
	}

	public int getStarIndex (int level)
	{
		int levelIndex = level-1;
		float score = levelScores[levelIndex];

		if (score>=levelScoresTemplate[levelIndex,2])
		{
			return 3;
		}
		else if (score>=levelScoresTemplate[levelIndex,1])
		{
			return 2;
		}
		else if (score>=levelScoresTemplate[levelIndex,0])
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	public int getStarIndex (LevelDetail detail)
	{
		float score = detail.score;
		int levelIndex = detail.level-1;

		if(score>levelScores[levelIndex])
		{
			levelScores[levelIndex] = score;
			save ();
		}

		if (score>=levelScoresTemplate[levelIndex,2])
		{
			return 3;
		}
		else if (score>=levelScoresTemplate[levelIndex,1])
		{
			return 2;
		}
		else if (score>=levelScoresTemplate[levelIndex,0])
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	public void save()
	{
		string[] linesToWrite = new string[20];
		for(int i=0;i<20;i++)
		{
			linesToWrite[i] = levelScores[i]+"";
		}
		File.WriteAllLines(filePath,linesToWrite);
	}


}
