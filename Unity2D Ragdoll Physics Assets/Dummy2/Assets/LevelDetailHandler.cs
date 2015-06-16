using UnityEngine;
using System.Collections;
using System.IO;

public class LevelDetailHandler 
{

	private string filePath = null;
	private string fileName = "Ragdoll_Settings.txt";

	float[,] levelScores = 
	new float[,] 
	{ //levels
		{ //level 1
		 	1000,1000,1000 },
		{ //level 2		 
		 	1000,1000,1000 },
		{ //level 3		 
		 	1000,1000,1000 },
		{ //level 4		 
		 	1000,1000,1000 },
		{ //level 5		 
		 	1000,1000,1000 },
		{ //level 6		 
		 	1000,1000,1000 },
		{ //level 7		 
		 	1000,1000,1000 },
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

		}
	}

	public int getStarIndex (LevelDetail detail)
	{
		float score = detail.score;
		int levelIndex = detail.level-1;

		if (score>=levelScores[levelIndex,2])
		{
			return 3;
		}
		else if (score>=levelScores[levelIndex,1])
		{
			return 2;
		}
		else if (score>=levelScores[levelIndex,0])
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}


}
