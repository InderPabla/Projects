--Name:   Inderpreet Pabla

-- HOW TO USE --
-- start		< This will begin the program
-- a2 N 5		< You will now be able to input freely (location direction amount) 
-- a3 N			< OR (location direction)
--				< When you are finished inputting, press enter (enter an empty line) and you will see the result.

import Data.Char
import System.IO

-- The following is the initial chess board. It is implemented as a list of tuples. Each tuple has a letter (lowercase signifies black pieces and
-- uppercase signifies white pieces) and two coordinates (x and y essentially). r = rook, n = knight, b = bishop, q = queen, k = king, p = pawn.
initialBoard = [('r',1,8),('n',2,8),('b',3,8),('q',4,8),('k',5,8),('b',6,8),('n',7,8),('r',8,8),
				('p',1,7),('p',2,7),('p',3,7),('p',4,7),('p',5,7),('p',6,7),('p',7,7),('p',8,7),
				('_',1,6),('_',2,6),('_',3,6),('_',4,6),('_',5,6),('_',6,6),('_',7,6),('_',8,6),
				('_',1,5),('_',2,5),('_',3,5),('_',4,5),('_',5,5),('_',6,5),('_',7,5),('_',8,5),
				('_',1,4),('_',2,4),('_',3,4),('_',4,4),('_',5,4),('_',6,4),('_',7,4),('_',8,4),
				('_',1,3),('_',2,3),('_',3,3),('_',4,3),('_',5,3),('_',6,3),('_',7,3),('_',8,3),
				('P',1,2),('P',2,2),('P',3,2),('P',4,2),('P',5,2),('P',6,2),('P',7,2),('P',8,2),
				('R',1,1),('N',2,1),('B',3,1),('Q',4,1),('K',5,1),('B',6,1),('N',7,1),('R',8,1)	
			   ]
			   
-- The following prints the board in a user friendly and easy to read manner. It also prints the the list of which enemies pieces were killed.
showBoard [(c1,1,8),(c2,2,8),(c3,3,8),(c4,4,8),(c5,5,8),(c6,6,8),(c7,7,8),(c8,8,8),
			(c9,1,7),(c10,2,7),(c11,3,7),(c12,4,7),(c13,5,7),(c14,6,7),(c15,7,7),(c16,8,7),
			(c17,1,6),(c18,2,6),(c19,3,6),(c20,4,6),(c21,5,6),(c22,6,6),(c23,7,6),(c24,8,6),
			(c25,1,5),(c26,2,5),(c27,3,5),(c28,4,5),(c29,5,5),(c30,6,5),(c31,7,5),(c32,8,5),
			(c33,1,4),(c34,2,4),(c35,3,4),(c36,4,4),(c37,5,4),(c38,6,4),(c39,7,4),(c40,8,4),
			(c41,1,3),(c42,2,3),(c43,3,3),(c44,4,3),(c45,5,3),(c46,6,3),(c47,7,3),(c48,8,3),
			(c49,1,2),(c50,2,2),(c51,3,2),(c52,4,2),(c53,5,2),(c54,6,2),(c55,7,2),(c56,8,2),
			(c57,1,1),(c58,2,1),(c59,3,1),(c60,4,1),(c61,5,1),(c62,6,1),(c63,7,1),(c64,8,1)	
		   ] [a,b,c,d,e,f,g,h,i,j,k,l] = putStr((show c1)  ++ (show c2)  ++ (show c3)  ++ (show c4)  ++ (show c5)  ++ (show c6)  ++ (show c7)  ++ (show c8)  ++ "\n" ++
			   (show c9)  ++ (show c10) ++ (show c11) ++ (show c12) ++ (show c13) ++ (show c14) ++ (show c15) ++ (show c16) ++ "\n" ++
			   (show c17) ++ (show c18) ++ (show c19) ++ (show c20) ++ (show c21) ++ (show c22) ++ (show c23) ++ (show c24) ++ "\n" ++
			   (show c25) ++ (show c26) ++ (show c27) ++ (show c28) ++ (show c29) ++ (show c30) ++ (show c31) ++ (show c32) ++ "\n" ++
			   (show c33) ++ (show c34) ++ (show c35) ++ (show c36) ++ (show c37) ++ (show c38) ++ (show c39) ++ (show c40) ++ "\n" ++
			   (show c41) ++ (show c42) ++ (show c43) ++ (show c44) ++ (show c45) ++ (show c46) ++ (show c47) ++ (show c48) ++ "\n" ++
			   (show c49) ++ (show c50) ++ (show c51) ++ (show c52) ++ (show c53) ++ (show c54) ++ (show c55) ++ (show c56) ++ "\n" ++
			   (show c57) ++ (show c58) ++ (show c59) ++ (show c60) ++ (show c61) ++ (show c62) ++ (show c63) ++ (show c64) ++ "\n" ++
			   (show "---Dead List---")++"\n"++
			   (show "Total Dead White Pawns:")++(show a)++"\n"++
			   (show "Total Dead Black Pawns:")++(show b)++"\n"++
			   (show "Total Dead White Rooks:")++(show c)++"\n"++
			   (show "Total Dead Black Rooks:")++(show d)++"\n"++
			   (show "Total Dead White Knights:")++(show e)++"\n"++
			   (show "Total Dead Black Knights:")++(show f)++"\n"++
			   (show "Total Dead White Bishops:")++(show g)++"\n"++
			   (show "Total Dead Black Bishops:")++(show h)++"\n"++
			   (show "Dead White Queen:")++(show i)++"\n"++
			   (show "Dead Black Queen:")++(show j)++"\n"++
			   (show "Dead White King:")++(show k)++"\n"++
			   (show "Dead Black King:")++(show l)++ "\n") 
-- getPiece: Given a piece's location (x and y) and the board, this function recursively finds what piece is at that location.
getPiece x y ((p,a,b):[]) = p 
getPiece x y ((p,a,b):t) =  if (a==x && y==b) then p else (getPiece x y t)

-- isCoordValid: Given a location (x and y) and the board, this function will return if a location is empty or not.
isCoordValid x y ((p,a,b):[]) = (p=='_') 
isCoordValid x y ((p,a,b):t) = if (a==x && y==b) then (p=='_') else (isCoordValid x y t)

-- findNum: Given a location (x and y) and the board, this function will find which square is what number (bottom right corner = 64, top left = 1)
findNum x y ((p,_,_):[]) = 1
findNum x y ((p,a,b):t) = if(x==a && y==b) then 1 else ((findNum x y t) + 1 )

-- replacePiece: Given an initial location of a piece and a destination location of a piece and a board, this function will move the piece
-- to it's destination location, and delete the piece from it's original location to simulate a move.
replacePiece x2 y2 x y board = 
			removePiece x2 y2 (let (xs,ys) = splitAt ((findNum x y board)-1) board in xs ++ [((getPiece x2 y2 board),x,y)] ++ tail ys)

-- removePiece: Given a location and a board, this function will make a location on the board empty to simulate a piece being removed.
removePiece x y board = 
			let (xs,ys) = splitAt ((findNum x y board)-1) board in xs ++ [('_',x,y)] ++ tail ys

-- The assignment described the x axis as being letters. The following takes the letter representation and converts it to a number to make it easier to work with.
alphaToNum 'a' = 1
alphaToNum 'b' = 2
alphaToNum 'c' = 3
alphaToNum 'd' = 4
alphaToNum 'e' = 5
alphaToNum 'f' = 6
alphaToNum 'g' = 7
alphaToNum 'h' = 8
alphaToNum '1' = 1
alphaToNum '2' = 2
alphaToNum '3' = 3
alphaToNum '4' = 4
alphaToNum '5' = 5
alphaToNum '6' = 6
alphaToNum '7' = 7
alphaToNum '8' = 8

-- move: Simulates moving a piece using helper functions removePiece and replacePiece.
move (x:y:z) dir n board = 
	if ((isCoordValid (alphaToNum x) (alphaToNum y) board)==False) 
		then (if((moveValues (x:y:z) dir n board)==[]) 
			then board 
		else 
			if ((restrictMoves (getPiece (alphaToNum x) (alphaToNum y) board ) dir ((alphaToNum x),(alphaToNum y)) (head( reverse (moveValues (x:y:z) dir n board))) board)==True)
				then (let (x2,y2) = (head( reverse (moveValues (x:y:z) dir n board))) in (replacePiece (alphaToNum x) (alphaToNum y) x2 y2 board))
			else board)
				
	else board

-- moveValues: Finds the place to move the piece.		
moveValues (x:y:z) dir n board = getAllValid (take (maxMoves (getPiece (alphaToNum x) (alphaToNum y) board ) n) (tail (getCoords ((alphaToNum x),(alphaToNum y)) dir))) board (getPiece (alphaToNum x) (alphaToNum y) board)

-- getAllValid: Finds all valid places a piece can move.
getAllValid ([]) board p = []
getAllValid ((x,y):[]) board p = if((isCoordValid x y board)==True) then ((x,y):[]) else ( if((isCoordEnemy p (x,y) board)==True) then ((x,y):[]) else ([]) )
getAllValid ((x,y):t) board p  =  if((isCoordValid x y board)==True)then ((x,y):(getAllValid t board p)) else ( if((isCoordEnemy p (x,y) board)==True) then ((x,y):[]) else ([]) )

-- getCoords: Finds all the valid places a piece can move relative to a direction.
getCoords (x,y) "N"  = if(y<8) then ((x,y):(getCoords (x,y+1) "N")) else ((x,y):[])
getCoords (x,y) "NW" = if(y<8 && x>1) then ((x,y):(getCoords (x-1,y+1) "NW")) else ((x,y):[])
getCoords (x,y) "NE" = if(y<8 && x<8) then ((x,y):(getCoords (x+1,y+1) "NE")) else ((x,y):[])

getCoords (x,y) "S"  = if(y>1) then ((x,y):(getCoords (x,y-1) "S")) else ((x,y):[])
getCoords (x,y) "SW" = if(y>1 && x>1) then ((x,y):(getCoords (x-1,y-1) "SW")) else ((x,y):[])
getCoords (x,y) "SE" = if(y>1 && x<8) then ((x,y):(getCoords (x+1,y-1) "SE")) else ((x,y):[])

getCoords (x,y) "W" = if(x>1) then ((x,y):(getCoords (x-1,y) "W")) else ((x,y):[])
getCoords (x,y) "E" = if(x<8) then ((x,y):(getCoords (x+1,y) "E")) else ((x,y):[])

getCoords (x,y) "NNE" = if((x+1)<=8 && (y+2)<=8) then ((x,y):[(x+1,y+2)]) else ([(x,y)])
getCoords (x,y) "NEE" = if((x+2)<=8 && (y+1)<=8) then ((x,y):[(x+2,y+1)]) else ([(x,y)])
getCoords (x,y) "NNW" = if((x-1)>=1 && (y+2)<=8) then ((x,y):[(x-1,y+2)]) else ([(x,y)])
getCoords (x,y) "NWW" = if((x-2)>=1 && (y+1)<=8) then ((x,y):[(x-2,y+1)]) else ([(x,y)])

getCoords (x,y) "SSE" = if((x+1)<=8 && (y-2)>=1) then ((x,y):[(x+1,y-2)]) else ([(x,y)])
getCoords (x,y) "SEE" = if((x+2)<=8 && (y-1)>=1) then ((x,y):[(x+2,y-1)]) else ([(x,y)])
getCoords (x,y) "SSW" = if((x-1)>=1 && (y-2)>=1) then ((x,y):[(x-1,y-2)]) else ([(x,y)])
getCoords (x,y) "SWW" = if((x-2)>=1 && (y-1)>=1) then ((x,y):[(x-2,y-1)]) else ([(x,y)])

getCoords (x,y) _ = ((x,y):[]) -- all other directions are ignored
-- restrictMoves: restricts the moves of each chess piece based on chess rules
-- pawn restrictions
restrictMoves 'p' "E" (x,y) (x2,y2) board = False
restrictMoves 'p' "W" (x,y) (x2,y2) board = False
restrictMoves 'p' "N" (x,y) (x2,y2) board = False
restrictMoves 'p' "S" (x,y) (x2,y2) board = if((isCoordEnemy 'p' (x2,y2) board)==True && ((getPiece x2 y2 board )=='_')==False) then False else True
restrictMoves 'p' (dx:dy:dz:[]) (x,y) (x2,y2) board = False
restrictMoves 'p' ('N':dy:[]) (x,y) (x2,y2) board = False
restrictMoves 'p' ('S':dy:[]) (x,y) (x2,y2) board = if((isCoordEnemy 'p' (x2,y2) board)==True && ((getPiece x2 y2 board )=='_')==False) then True else False

restrictMoves 'P' "E" (x,y) (x2,y2) board = False
restrictMoves 'P' "W" (x,y) (x2,y2) board = False
restrictMoves 'P' "N" (x,y) (x2,y2) board = if((isCoordEnemy 'P' (x2,y2) board)==True && ((getPiece x2 y2 board )=='_')==False) then False else True
restrictMoves 'P' "S" (x,y) (x2,y2) board = False
restrictMoves 'P' (dx:dy:dz:[]) (x,y) (x2,y2) board = False
restrictMoves 'P' ('N':dy:[]) (x,y) (x2,y2) board = if((isCoordEnemy 'P' (x2,y2) board)==True && ((getPiece x2 y2 board )=='_')==False) then True else False
restrictMoves 'P' ('S':dy:[]) (x,y) (x2,y2) board = False

-- rook restrictions
restrictMoves 'r' (dx:dy:[]) (x,y) (x2,y2) board = False
restrictMoves 'r' (dx:dy:dz:[]) (x,y) (x2,y2) board = False
restrictMoves 'r' _ (x,y) (x2,y2) board = True
restrictMoves 'R' (dx:dy:[]) (x,y) (x2,y2) board = False
restrictMoves 'R' (dx:dy:dz:[]) (x,y) (x2,y2) board = False
restrictMoves 'R' _ (x,y) (x2,y2) board = True

-- bishop restrictions
restrictMoves 'b' (dx:dy:[]) (x,y) (x2,y2) board = True
restrictMoves 'b' (dx:dy:dz:[]) (x,y) (x2,y2) board = False
restrictMoves 'b' _ (x,y) (x2,y2) board = False             
restrictMoves 'B' (dx:dy:[]) (x,y) (x2,y2) board = True
restrictMoves 'B' (dx:dy:dz:[]) (x,y) (x2,y2) board = False
restrictMoves 'B' _ (x,y) (x2,y2) board = False

-- knight restrictions
restrictMoves 'n' (dx:dy:dz:[]) (x,y) (x2,y2) board = True
restrictMoves 'n' _ (x,y) (x2,y2) board = False
restrictMoves 'N' (dx:dy:dz:[]) (x,y) (x2,y2) board = True
restrictMoves 'N' _ (x,y) (x2,y2) board = False

-- king restrictions
restrictMoves 'k' (dx:dy:dz:[]) (x,y) (x2,y2) board = False 
restrictMoves 'k' _ (x,y) (x2,y2) board = True
restrictMoves 'K' (dx:dy:dz:[]) (x,y) (x2,y2) board = False
restrictMoves 'K' _ (x,y) (x2,y2) board = True

-- queen restrictions
restrictMoves 'q' (dx:dy:dz:[]) (x,y) (x2,y2) board = False
restrictMoves 'q' _ (x,y) (x2,y2) board = True
restrictMoves 'Q' (dx:dy:dz:[]) (x,y) (x2,y2) board = False
restrictMoves 'Q' _ (x,y) (x2,y2) board = True
--Note: king and queen restriction could have been merged as one due to similar style 
------- of directional movement. But we have left it for visual understanding purpose

restrictMoves _ _ _ _ _ = False -- should never be called, previous restrictMoves will catch it

-- maxMoves: A rule to limit the number of moves a piece can make. (Pawn can only move 1 square)
maxMoves 'p' n = 1
maxMoves 'P' n = 1
maxMoves 'k' n = 1
maxMoves 'K' n = 1
maxMoves 'n' n = 1
maxMoves 'N' n = 1
maxMoves p n = if (n>0) then n else 0

-- isCoordEnemy: Checks if the coordinate is an enemy, this may allow us to kill it.
isCoordEnemy p (x,y) board = if ((isUpper p)==True) then ( if(isUpper(getPiece x y board)==True) then False else True ) else (if(isUpper(getPiece x y board)==True) then True else False) 

-- stringToInt: A helper function to make a string an integer.
stringToInt a = read a :: Int

-- start: Call start to begin the program.
start = main2 initialBoard [0,0,0,0,0,0,0,0,0,0,0,0]
-- main: Read input from stdin and apply moves to the board.
main2 board dead= do
	input <- getLine
	if (length(words(input)) == 3) then 
		let (x:y:z:q) = words(input) in main2 (move x y (stringToInt z) board) (getKilled board) else (if (length(words(input)) == 2) then (let (x:y:z) = words(input) in main2 (move x y 10 board) (getKilled board) )else showBoard(board) (getKilled board))

-- countPiece: Given a piece and a board, this will count how many of those pieces exist in the board.		
countPiece p ((a,b,c):[]) = if (p==a) then 1 else 0
countPiece p ((a,b,c):t) = if (p==a) then (1 + (countPiece p t)) else (0 + (countPiece p t))	

-- getKilled: Given a board, this will count how many pieces are missing in comparison to the initial board.
getKilled board = [(8 - (countPiece 'P' board)),8 - (countPiece 'p' board),2 - (countPiece 'R' board),2 - (countPiece 'r' board),2 - (countPiece 'N' board),2 - (countPiece 'n' board),2 - (countPiece 'B' board),2 - (countPiece 'b' board),1 - (countPiece 'Q' board),1 - (countPiece 'q' board),1 - (countPiece 'K' board),1 - (countPiece 'k' board)]