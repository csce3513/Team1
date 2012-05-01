package BattleChess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class BattleChess extends Applet implements MouseMotionListener
{
        class piece
	{
                //variables to store all info about pieces
		int IDNum;
		int row;
		int column;
		int xposition;
		int yposition;
		Image mypic;
		boolean visible;
		boolean firstmove;
		String color;
		String name;
	};

        //assigns every piece a number
	public static piece [] pieces=new piece[32];

	//global variables
	public static int ChessBoard[][] = new int [8][8];
	static int counter = 0;
	boolean ok = false; //boolean variable to test movement validity
	int pieceChosen = 0; //variable to identify which piece is moving
	int numclicks = 0;
	int oldx;
	int oldy;
	int squares[][] = new int [12][12];
	boolean checked; //display "check" message
	int whichKing; //identify black or white king
	int whoseTurn; //identify players' turns
	boolean promotion; //pawn promotion

	public void GetFileAndName(int rowIndex, int colIndex)
	{
		String fileName;
		String blackName = "brbnbbbqbk";
		String whiteName = "wrwnwbwqwk";
		String tempName = "";
		String fileExt = ".gif";

		if (rowIndex == 1)
			tempName = blackName;
		else if (rowIndex == 8)
			tempName = whiteName;
		if (rowIndex == 2 || rowIndex == 7)
		{
			pieces[counter].name = "Pawn";
			if (rowIndex == 2)
				pieces[counter].mypic = getImage(getCodeBase(), "bp.gif");
			else
				pieces[counter].mypic = getImage(getCodeBase(), "wp.gif");
		}
		else
		{
			switch(colIndex)
			{
				case 1:
				case 8:
				{
					fileName = tempName.substring(0,2) + fileExt;
					pieces[counter].mypic = getImage(getCodeBase(), fileName);
					pieces[counter].name = "Rook";
					break;
				}
				case 2:
				case 7:
				{
					fileName = tempName.substring(2,4) + fileExt;
					pieces[counter].mypic = getImage(getCodeBase(), fileName);
					pieces[counter].name = "Knight";
					break;
				}
				case 3:
				case 6:
				{
					fileName = tempName.substring(4,6) + fileExt;
					pieces[counter].mypic = getImage(getCodeBase(), fileName);
					pieces[counter].name = "Bishop";
					break;
				}
				case 4:
				{
					fileName = tempName.substring(6,8) + fileExt;
					pieces[counter].mypic = getImage(getCodeBase(), fileName);
					pieces[counter].name = "Queen";
					break;
				}
				case 5:
				{
					fileName = tempName.substring(8,10) + fileExt;
					pieces[counter].mypic = getImage(getCodeBase(), fileName);
					pieces[counter].name = "King";
					break;
				}
			}
		}
	}

	public void init()
	{
          setLayout(new FlowLayout());
          newGame = new Button("New Game");
          this.add(newGame);
          newGame.addMouseListener(new MouseClickListener());
        }
        
        class MouseClickListener extends MouseAdapter 
        {
            public void mousePressed(java.awt.event.MouseEvent e)
            {
             counter = 0;
             start();
             counter = 0;
             start();
             repaint();
            }
        }
         public void start()
         {
		for (int i=0; i< 32; i++)
		{
			pieces[i]=new piece();
		}
		int rowArray[] = {1,2,7,8};
		for (int k = 0; k < 4; k++)
		{
			for (int l = 0; l < 8; l++)
			{
				pieces[counter].IDNum = counter;
				pieces[counter].row = rowArray[k];
				pieces[counter].column = l + 1;
				pieces[counter].xposition = 0;
				pieces[counter].yposition = 0;
				pieces[counter].visible = true;
				pieces[counter].firstmove = true;
				if (rowArray[k] < 3)
					pieces[counter].color = "black";
				else
					pieces[counter].color = "white";
				GetFileAndName(rowArray[k],l+1);
				counter++;
			}
		}
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				squares[i][j] = 0;
		addMouseListener(new MousePressListener());
		addMouseMotionListener(this);
		whoseTurn = 0;	// used to alternate turns
		checked = false;
		promotion = false;
	}

	public int rowToX(int r)
	{
		int myx;
		int iHeight = getHeight();
		myx=r*iHeight/8 - 50;
		return myx;
	}
	public int colToY(int c)
	{
		int myy;
		int iWidth = getWidth();
		myy = c*iWidth/8 - 50;
		return myy;
	}

        Button newGame; 
        
	public void paint(Graphics g)
	{
		setSize(400,400);

		int iWidth = getWidth();
		int iHeight = getHeight();
		for (int i=0; i<8; i=i+2)
		{
			for (int j=0; j<8; j=j+2)
			{
				g.setColor(Color.GRAY);
				g.fillRect(j*iWidth/8,(1+i)*iWidth/8,iWidth/8,iHeight/8);
				g.fillRect((1+j)*iWidth/8,i*iWidth/8,iWidth/8,iHeight/8);
			}
		}
		for (int i = 0; i < 32; i++)
		{
			pieces[i].xposition=rowToX(pieces[i].column);
			pieces[i].yposition=colToY(pieces[i].row);
			if (pieces[i].visible)
				g.drawImage(pieces[i].mypic,pieces[i].xposition,pieces[i].yposition,iWidth/8,iHeight/8,this);
		}
		if (ok)
		{
			g.setColor(Color.RED.darker());
			g.drawRect(pieces[pieceChosen].xposition, pieces[pieceChosen].yposition,50,50);
		        g.setColor(Color.RED.brighter().brighter());
			g.drawRect(pieces[pieceChosen].xposition+1, pieces[pieceChosen].yposition+1,48,48);
			g.setColor(Color.RED.darker());
			g.drawRect(pieces[pieceChosen].xposition+2, pieces[pieceChosen].yposition+2,46,46);
		}
 	}

	public void mouseMoved(MouseEvent event)
	{
	}
	public void mouseDragged(MouseEvent event)
	{
		int x = event.getX();
		int y = event.getY();

		oldx=x;
		oldy=y;
	}

	class MousePressListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent event)
		{
			int x = event.getX();
			int y = event.getY();

			numclicks = (numclicks+1) % 2;
			//when ok = true, the piece is moved

			if (ok)
				MovePiece(x, y);
			else	//when ok = false, the piece is not moved, and we look for another piece
				BoardID(x, y);
			repaint();
		}

		public void BoardID(int x, int y)
		{
			for (int i = 0; i < 32; i++)
			{
				if (((y / 50) + 1 == pieces[i].row) && ((x / 50) + 1 == pieces[i].column))
				{
					pieceChosen = i; //identifies which piece has been selected
					if (whoseTurn == 0 && pieces[pieceChosen].color.equals("white"))
						ok = true; //says we found a white piece
					if (whoseTurn == 1 && pieces[pieceChosen].color.equals("black"))
						ok = true; //says we found a black piece
				}
			}
		}

		public boolean KingRules(int y, int x, boolean piecefound, int pieceNum)
		{
			String color1, color2;
			boolean canmove = false;

			if (pieces[pieceChosen].name.equals("King"))
			{
				y = y / 50 + 1;
				x = x / 50 + 1;
				if (pieces[pieceChosen].column == y - 1 && pieces[pieceChosen].row == x - 1)
					canmove = true;
				else if (pieces[pieceChosen].column == y && pieces[pieceChosen].row == x - 1)
					canmove = true;
				else if (pieces[pieceChosen].column == y + 1 && pieces[pieceChosen].row == x - 1)
					canmove = true;
				else if (pieces[pieceChosen].column == y - 1 && pieces[pieceChosen].row == x)
					canmove = true;
				else if (pieces[pieceChosen].column == y + 1 && pieces[pieceChosen].row == x)
					canmove = true;
				else if (pieces[pieceChosen].column == y - 1 && pieces[pieceChosen].row == x + 1)
					canmove = true;
				else if (pieces[pieceChosen].column == y  && pieces[pieceChosen].row == x + 1)
					canmove = true;
				else if (pieces[pieceChosen].column == y + 1 && pieces[pieceChosen].row == x + 1)
					canmove = true;
                                
				//conditions for king-side castle
				if (pieces[pieceChosen].column == y - 2 && pieces[pieceChosen].row == x)
				{
					int numRook = 7, temprow = 1;
					canmove = true;
					if (pieces[pieceChosen].color.equals("white"))
					{
						numRook = 31;
						temprow = 8;
					}
					if (pieces[pieceChosen].firstmove && pieces[numRook].firstmove)
					{
						for (int i = 0; i < 32; i++)
						{
							if (pieces[i].row == temprow && pieces[i].column == 6)
								canmove = false;
							if (pieces[i].row == temprow && pieces[i].column == 7)
								canmove = false;
						}
					}
					else
						canmove = false;
					if (canmove)
					{
						pieces[numRook].column = 6;
						pieces[numRook].firstmove = true;
					}
				}
                                
                                //conditions for queen-side castle
				if (pieces[pieceChosen].column == y + 2 && pieces[pieceChosen].row == x)
				{
					System.out.println("over 2");
					int numRook = 0, temprow = 1;
					canmove = true;
					if (pieces[pieceChosen].color.equals("white"))
					{
						numRook = 24;
						temprow = 8;
					}
					if (pieces[pieceChosen].firstmove && pieces[numRook].firstmove)
					{
						for (int i = 0; i < 32; i++)
						{
							if (pieces[i].row == temprow && pieces[i].column == 2)
								canmove = false;
							if (pieces[i].row == temprow && pieces[i].column == 3)
								canmove = false;
							if (pieces[i].row == temprow && pieces[i].column == 4)
								canmove = false;
						}
					}
					else
						canmove = false;
					if (canmove)
					{
						pieces[numRook].column = 4;
						pieces[numRook].firstmove = false;
					}
				}
				if (canmove)
				{
					if (piecefound)
					{
						color1 = pieces[pieceChosen].color;
						color2 = pieces[pieceNum].color;
						if (color1.equals(color2))
							canmove = false;
					}
				}
			}
			if (canmove)
				pieces[pieceChosen].firstmove = false;
			return canmove;
		}

		public boolean KingCoor(int tempcoor[], int movecase)
		{
			boolean moveok = false;
			switch (movecase)
			{
				case 0:
					tempcoor[0]--;
					tempcoor[1]--;
					break;
				case 1:
					tempcoor[0]--;
					break;
				case 2:
					tempcoor[0]++;
					tempcoor[1]--;
					break;
				case 3:
					tempcoor[1]--;
					break;
				case 4:
					tempcoor[1]++;
					break;
				case 5:
					tempcoor[0]--;
					tempcoor[1]++;
					break;
				case 6:
					tempcoor[0]++;
					break;
				case 7:
					tempcoor[0]++;
					tempcoor[1]++;
					break;
			}
			if (tempcoor[0] >= 0 && tempcoor[0] < 9)
				if (tempcoor[1] >= 0 && tempcoor[1] < 9)
					moveok = true;
			return moveok;
		}

		public boolean KnightRules(int y, int x, boolean piecefound, int pieceNum)
		{
			boolean canmove = false;
			String color1, color2;

			if (pieces[pieceChosen].name.equals("Knight"))
			{
				y = y / 50 + 1;
				x = x / 50 + 1;
				if (((pieces[pieceChosen].row == x - 1) || (pieces[pieceChosen].row == x + 1)) && ((pieces[pieceChosen].column == y + 2) || (pieces[pieceChosen].column == y - 2)))
					canmove = true;
				if ((pieces[pieceChosen].row == x - 2 || pieces[pieceChosen].row == x + 2) && ((pieces[pieceChosen].column == y + 1) || (pieces[pieceChosen].column == y - 1)))
					canmove = true;
				if (canmove)
				{
					if (piecefound)
					{
						color1 = pieces[pieceChosen].color;
						color2 = pieces[pieceNum].color;
						if (color1.equals(color2))
							canmove = false;
					}
				}
			}
			if (pieceNum != 40)
				if (pieces[pieceNum].name.equals("King"))
					canmove = false;
			return canmove;
		}

		public void KnightCoor(int tempcoor[], int movecase)
		{
			switch(movecase)
			{
				case 0:
					tempcoor[0] -= 2;
					tempcoor[1] += 1;
					break;
				case 1:
					tempcoor[0] -= 1;
					tempcoor[1] += 2;
					break;
				case 2:
					tempcoor[0] += 1;
					tempcoor[1] += 2;
					break;
				case 3:
					tempcoor[0] += 2;
					tempcoor[1] += 1;
					break;
				case 4:
					tempcoor[0] += 2;
					tempcoor[1] -= 1;
					break;
				case 5:
					tempcoor[0] += 1;
					tempcoor[1] -= 2;
					break;
				case 6:
					tempcoor[0] -= 1;
					tempcoor[1] -= 2;
					break;
				case 7:
					tempcoor[0] -= 2;
					tempcoor[1] -= 1;
					break;
			}
		}

		public boolean BishopRules(int y, int x, boolean piecefound, int pieceNum)
		{
			boolean canmove = false;
			if (pieces[pieceChosen].name.equals("Bishop"))
				canmove = DiagonalMove(x,y,piecefound,pieceNum);
			return canmove;
		}

		public boolean DiagonalMove(int x, int y, boolean piecefound, int pieceNum)
		{
			int movecase = 0, length = 0;
			int tempcoor [] = new int[2];
			String color1, color2;
			boolean canmove = false;
			y = y / 50 + 1;
			x = x / 50 + 1;
			if (pieces[pieceChosen].row - x == pieces[pieceChosen].column - y)
			{
				if (pieces[pieceChosen].row - x > 0 && pieces[pieceChosen].column - y > 0)
				{
					length = pieces[pieceChosen].row - x;
					movecase = 0;
					canmove = true;
				}
			}
			if (pieces[pieceChosen].row - x == y - pieces[pieceChosen].column)
			{
				if (pieces[pieceChosen].row - x > 0 && y - pieces[pieceChosen].column > 0)
				{
					length = pieces[pieceChosen].row - x;
					movecase = 1;
					canmove = true;
				}
			}
			if (x - pieces[pieceChosen].row == y - pieces[pieceChosen].column)
			{
				if (x - pieces[pieceChosen].row > 0 && y - pieces[pieceChosen].column > 0)
				{
					length = x - pieces[pieceChosen].row;
					movecase = 2;
					canmove = true;
				}
			}
			if (x - pieces[pieceChosen].row == pieces[pieceChosen].column - y)
			{
				if (x - pieces[pieceChosen].row > 0 && pieces[pieceChosen].column - y > 0)
				{
					length = x - pieces[pieceChosen].row;
					movecase = 3;
					canmove = true;
				}
			}
			if (canmove)
			{
				tempcoor[0] = pieces[pieceChosen].row;
				tempcoor[1] = pieces[pieceChosen].column;
				for (int i = 0; i < length - 1; i++)
				{
					BishopCoor(tempcoor,movecase);
					for (int j = 0; j < 32; j++)
						if (tempcoor[0] == pieces[j].row && tempcoor[1] == pieces[j].column)
							canmove = false;
				}
			}
			if (canmove)
			{
				if (piecefound)
				{
					color1 = pieces[pieceChosen].color;
					color2 = pieces[pieceNum].color;
					if (color1.equals(color2))
						canmove = false;
				}
			}
			System.out.println("canmove = " + canmove);
			System.out.println("movecase = " + movecase);
			if (pieceNum != 40)
				if (pieces[pieceNum].name.equals("King"))
					canmove = false;
			return canmove;
		}

		public boolean BishopCoor(int coor[], int movecase)
		{
			boolean moveok = false;
			switch (movecase)
			{
				case 0:
					coor[0]--;
					coor[1]--;
					break;
				case 1:
					coor[0]--;
					coor[1]++;
					break;
				case 2:
					coor[0]++;
					coor[1]++;
					break;
				case 3:
					coor[0]++;
					coor[1]--;
					break;
			}
			if (coor[0] >= 0 && coor[0] < 9)
				if (coor[1] >= 0 && coor[1] < 9)
					moveok = true;
			return moveok;
		}

		public boolean RookRules(int y, int x, boolean piecefound, int pieceNum)
		{
			boolean canmove = false;
			if (pieces[pieceChosen].name.equals("Rook"))
				canmove = SideMove(x,y,piecefound,pieceNum);
			if (canmove)
				pieces[pieceChosen].firstmove = false;
			return canmove;
		}

		public boolean SideMove(int x, int y, boolean piecefound, int pieceNum)
		{
			int movecase = 0, length = 0;
			int tempcoor[] = new int[2];
			boolean canmove = false;
			String color1, color2;
			y = y / 50 + 1;
			x = x / 50 + 1;
			if (pieces[pieceChosen].column == y)
			{
				if (pieces[pieceChosen].row - x > 0)
				{
					length = pieces[pieceChosen].row - x;
					movecase = 0;
					canmove = true;
				}
				if (x - pieces[pieceChosen].row > 0)
				{
					length = x - pieces[pieceChosen].row;
					movecase = 2;
					canmove = true;
				}
			}
			if (pieces[pieceChosen].row == x)
			{
				if (y - pieces[pieceChosen].column > 0)
				{
					length = y - pieces[pieceChosen].column;
					movecase = 1;
					canmove = true;
				}
				if (pieces[pieceChosen].column - y > 0)
				{
					length = pieces[pieceChosen].column - y;
					movecase = 3;
					canmove = true;
				}
			}
			if (canmove)
			{
				tempcoor[0] = pieces[pieceChosen].row;
				tempcoor[1] = pieces[pieceChosen].column;
				for (int i = 0; i < length - 1; i++)
				{
					RookCoor(tempcoor, movecase);
					for (int j = 0; j < 32; j++)
						if (tempcoor[0] == pieces[j].row && tempcoor[1] == pieces[j].column)
							canmove = false;
				}
			}
			if (canmove)
			{
				if (piecefound)
				{
					color1 = pieces[pieceChosen].color;
					color2 = pieces[pieceNum].color;
					if (color1.equals(color2))
						canmove = false;
				}
			}
			if (pieceNum != 40)
				if (pieces[pieceNum].name.equals("King"))
					canmove = false;
			return canmove;
		}

		public boolean RookCoor(int tempcoor[], int movecase)
		{
			boolean moveok = false;
			switch (movecase)
			{
				case 0:
					tempcoor[0]--;
					break;
				case 1:
					tempcoor[1]++;
					break;
				case 2:
					tempcoor[0]++;
					break;
				case 3:
					tempcoor[1]--;
					break;
			}
			if (tempcoor[0] >= 0 && tempcoor[0] < 9)
				if (tempcoor[1] >= 0 && tempcoor[1] < 9)
					moveok = true;
			return moveok;
		}

		public boolean QueenRules(int y, int x, boolean piecefound, int pieceNum)
		{
			boolean canmove = false;
			if (pieces[pieceChosen].name.equals("Queen"))
				canmove = SideMove(x,y,piecefound,pieceNum) ^ DiagonalMove(x,y,piecefound,pieceNum);
			return canmove;
		}

		public boolean PawnRules(int y, int x, boolean piecefound, int pieceNum)
		{
			boolean canmove = false;

			if (pieces[pieceChosen].name.equals("Pawn"))
			{
				y = y / 50 + 1;
				x = x / 50 + 1;
				if (pieces[pieceChosen].color.equals("white"))
				{
					if (pieces[pieceChosen].column == y)
					{
						if (pieces[pieceChosen].row == x + 1)
						{
							if (!piecefound)
							{
								pieces[pieceChosen].firstmove = false;
								canmove = true;
							}
						}
						if (pieces[pieceChosen].row == x + 2 && pieces[pieceChosen].firstmove)
						{
							if (!piecefound)
							{
								pieces[pieceChosen].firstmove = false;
								canmove = true;
							}
						}
					}
					else if (pieces[pieceChosen].column == y + 1 || pieces[pieceChosen].column == y - 1)
					{
						if (pieces[pieceChosen].row == x + 1)
						{
							if (pieceNum < 16)
							{
								if (piecefound)
								{
									pieces[pieceChosen].firstmove = false;
									canmove = true;
								}
							}
						}
					}
					if (canmove && x == 1)
						promotion = true;
				}
				if (pieces[pieceChosen].color.equals("black"))
				{
					if (pieces[pieceChosen].column == y)
					{
						if (pieces[pieceChosen].row == x - 1)
						{
							if (!piecefound)
							{
								pieces[pieceChosen].firstmove = false;
								canmove = true;
							}
						}
						if (pieces[pieceChosen].row == x - 2 && pieces[pieceChosen].firstmove)
						{
							if (!piecefound)
							{
								pieces[pieceChosen].firstmove = false;
								canmove = true;
							}
						}
					}
					else if (pieces[pieceChosen].column == y + 1 || pieces[pieceChosen].column == y - 1)
					{
						if (pieces[pieceChosen].row == x - 1)
						{
							if (pieceNum < 32 && pieceNum > 15)
							{
								if (piecefound)
								{
									pieces[pieceChosen].firstmove = false;
									canmove = true;
								}
							}
						}
					}
					if (canmove && x == 8)
						promotion = true;
				}
			}

			if (pieceNum != 40)
				if (pieces[pieceNum].name.equals("King"))
					canmove = false;
			return canmove;
		}

		public boolean CheckMethod()
		{
			checked = false;
			for (int i = 0; i < 32; i++)
			{
				HandlePieceType(pieces[i].color, pieces[i].name, i);
				if (checked)
					break;
			}
			return checked;
		}

		public void HandlePieceType(String mycolor, String myname, int index)
		{
			if (myname.equals("Pawn") && mycolor.equals("white"))
			{
				if (pieces[4].row == pieces[index].row - 1 && pieces[4].column == pieces[index].column + 1)
				{
					checked = true;
					whichKing = 4;
				}
				if (pieces[4].row == pieces[index].row - 1 && pieces[4].column == pieces[index].column - 1)
				{
					checked = true;
					whichKing = 4;
				}
			}
			if (myname.equals("Pawn") && mycolor.equals("black"))
			{
				if (pieces[28].row == pieces[index].row + 1 && pieces[28].column == pieces[index].column + 1)
				{
					checked = true;
					whichKing = 28;
				}
				if (pieces[28].row == pieces[index].row + 1 && pieces[28].column == pieces[index].column - 1)
				{
					checked = true;
					whichKing = 28;
				}
			}
			if (myname.equals("Knight") && mycolor.equals("white"))
			{
				if ((pieces[4].row == pieces[index].row - 1 || pieces[4].row == pieces[index].row + 1)  && (pieces[4].column == pieces[index].column - 2 || pieces[4].column == pieces[index].column + 2))
				{
					checked = true;
					whichKing = 4;
				}
				if ((pieces[4].row == pieces[index].row - 2 || pieces[4].row == pieces[index].row + 2)  && (pieces[4].column == pieces[index].column - 1 || pieces[4].column == pieces[index].column + 1))
				{
					checked = true;
					whichKing = 4;
				}
			}
			if (myname.equals("Knight") && mycolor.equals("black"))
			{
				if ((pieces[28].row == pieces[index].row - 1 || pieces[28].row == pieces[index].row + 1)  && (pieces[28].column == pieces[index].column - 2 || pieces[28].column == pieces[index].column + 2))
				{
					checked = true;
					whichKing = 28;
				}
				if ((pieces[28].row == pieces[index].row - 2 || pieces[28].row == pieces[index].row + 2)  && (pieces[28].column == pieces[index].column - 1 || pieces[28].column == pieces[index].column + 1))
				{
					checked = true;
					whichKing = 28;
				}
			}
			if (myname.equals("Bishop") && mycolor.equals("white") && pieces[index].visible)
			{
				if (pieces[4].row - pieces[index].row == pieces[4].column - pieces[index].column)
					if (pieces[4].row - pieces[index].row > 0 && pieces[4].column - pieces[index].column > 0)
						BishopCheck(0,index,4);
				if (pieces[4].row - pieces[index].row == pieces[index].column - pieces[4].column)
					if (pieces[4].row - pieces[index].row > 0 && pieces[index].column - pieces[4].column > 0)
						BishopCheck(1,index,4);
				if (pieces[index].row - pieces[4].row == pieces[index].column - pieces[4].column)
					if (pieces[index].row - pieces[4].row > 0 && pieces[index].column - pieces[4].column > 0)
						BishopCheck(2,index,4);
				if (pieces[index].row - pieces[4].row == pieces[4].column - pieces[index].column)
					if (pieces[index].row - pieces[4].row > 0 && pieces[4].column - pieces[index].column > 0)
						BishopCheck(3,index,4);
			}
			if (myname.equals("Bishop") && mycolor.equals("black") && pieces[index].visible)
			{
				if (pieces[28].row - pieces[index].row == pieces[28].column - pieces[index].column)
					if (pieces[28].row - pieces[index].row > 0 && pieces[28].column - pieces[index].column > 0)
						BishopCheck(0,index,28);
				if (pieces[28].row - pieces[index].row == pieces[index].column - pieces[28].column)
					if (pieces[28].row - pieces[index].row > 0 && pieces[index].column - pieces[28].column > 0)
						BishopCheck(1,index,28);
				if (pieces[index].row - pieces[28].row == pieces[index].column - pieces[28].column)
					if (pieces[index].row - pieces[28].row > 0 && pieces[index].column - pieces[28].column > 0)
						BishopCheck(2,index,28);
				if (pieces[index].row - pieces[28].row == pieces[28].column - pieces[index].column)
					if (pieces[index].row - pieces[28].row > 0 && pieces[28].column - pieces[index].column > 0)
						BishopCheck(3,index,28);
			}
			if (myname.equals("Rook") && mycolor.equals("white"))
			{
				if (pieces[index].column == pieces[4].column)
				{
					if (pieces[index].row - pieces[4].row > 0)
						RookCheck(0,index,4);
					if (pieces[4].row - pieces[index].row > 0)
						RookCheck(2,index,4);
				}
				if (pieces[index].row == pieces[4].row)
				{
					if (pieces[4].column - pieces[index].column > 0)
						RookCheck(1,index,4);
					if (pieces[index].column - pieces[4].column > 0)
						RookCheck(3,index,4);
				}
			}
			if (myname.equals("Rook") && mycolor.equals("black"))
			{
				if (pieces[index].column == pieces[28].column)
				{
					if (pieces[index].row - pieces[28].row > 0)
						RookCheck(0,index,28);
					if (pieces[28].row - pieces[index].row > 0)
						RookCheck(2,index,28);
				}
				if (pieces[index].row == pieces[28].row)
				{
					if (pieces[28].column - pieces[index].column > 0)
						RookCheck(1,index,28);
					if (pieces[index].column - pieces[28].column > 0)
						RookCheck(3,index,28);
				}
			}
			if (myname.equals("Queen") && mycolor.equals("white") && pieces[index].visible)
			{
				if (pieces[4].row - pieces[index].row == pieces[4].column - pieces[index].column)
					if (pieces[4].row - pieces[index].row > 0 && pieces[4].column - pieces[index].column > 0)
						BishopCheck(0,index,4);
				if (pieces[4].row - pieces[index].row == pieces[index].column - pieces[4].column)
					if (pieces[4].row - pieces[index].row > 0 && pieces[index].column - pieces[4].column > 0)
						BishopCheck(1,index,4);
				if (pieces[index].row - pieces[4].row == pieces[index].column - pieces[4].column)
					if (pieces[index].row - pieces[4].row > 0 && pieces[index].column - pieces[4].column > 0)
						BishopCheck(2,index,4);
				if (pieces[index].row - pieces[4].row == pieces[4].column - pieces[index].column)
					if (pieces[index].row - pieces[4].row > 0 && pieces[4].column - pieces[index].column > 0)
						BishopCheck(3,index,4);
				if (pieces[index].column == pieces[4].column)
				{
					if (pieces[index].row - pieces[4].row > 0)
						RookCheck(0,index,4);
					if (pieces[4].row - pieces[index].row > 0)
						RookCheck(2,index,4);
				}
				if (pieces[index].row == pieces[4].row)
				{
					if (pieces[4].column - pieces[index].column > 0)
						RookCheck(1,index,4);
					if (pieces[index].column - pieces[4].column > 0)
						RookCheck(3,index,4);
				}
			}
			if (myname.equals("Queen") && mycolor.equals("black") && pieces[index].visible)
			{
				if (pieces[28].row - pieces[index].row == pieces[28].column - pieces[index].column)
					if (pieces[28].row - pieces[index].row > 0 && pieces[28].column - pieces[index].column > 0)
						BishopCheck(0,index,28);
				if (pieces[28].row - pieces[index].row == pieces[index].column - pieces[28].column)
					if (pieces[28].row - pieces[index].row > 0 && pieces[index].column - pieces[28].column > 0)
						BishopCheck(1,index,28);
				if (pieces[index].row - pieces[28].row == pieces[index].column - pieces[28].column)
					if (pieces[index].row - pieces[28].row > 0 && pieces[index].column - pieces[28].column > 0)
						BishopCheck(2,index,28);
				if (pieces[index].row - pieces[28].row == pieces[28].column - pieces[index].column)
					if (pieces[index].row - pieces[28].row > 0 && pieces[28].column - pieces[index].column > 0)
						BishopCheck(3,index,28);
				if (pieces[index].column == pieces[28].column)
				{
					if (pieces[index].row - pieces[28].row > 0)
						RookCheck(0,index,28);
					if (pieces[28].row - pieces[index].row > 0)
						RookCheck(2,index,28);
				}
				if (pieces[index].row == pieces[28].row)
				{
					if (pieces[28].column - pieces[index].column > 0)
						RookCheck(1,index,28);
					if (pieces[index].column - pieces[28].column > 0)
						RookCheck(3,index,28);
				}
			}
		}

		public void BishopCheck(int control, int index, int kingnum)
		{
			int length;
			int tempcoor[] = new int[2];
			boolean check;

			length = Math.abs(pieces[kingnum].row - pieces[index].row);
			tempcoor[0] = pieces[kingnum].row;
			tempcoor[1] = pieces[kingnum].column;
			check = true;
			for (int i = 0; i < length - 1; i++)
			{
				BishopCoor(tempcoor, control);
				for (int j = 0; j < 32; j++)
					if (tempcoor[0] == pieces[j].row && tempcoor[1] == pieces[j].column)
						check = false;
			}
			if (check)
			{
				checked = true;
				whichKing = kingnum;
			}
		}

		public void RookCheck(int control, int index, int kingnum)
		{
			int length = 0;
			int tempcoor[] = new int[2];
			boolean check = true;

			if (control == 0 || control == 2)
				length = Math.abs(pieces[kingnum].row - pieces[index].row);
			if (control == 1 || control == 3)
				length = Math.abs(pieces[kingnum].column - pieces[index].column);
			tempcoor[0] = pieces[index].row;
			tempcoor[1] = pieces[index].column;
			for (int i = 0; i < length - 1; i++)
			{
				RookCoor(tempcoor, control);
				for (int j = 0; j < 32; j++)
					if (tempcoor[0] == pieces[j].row && tempcoor[1] == pieces[j].column)
						check = false;
			}
			if (check)
			{
				checked = true;
				whichKing = kingnum;
			}
		}

		public void PawnPromotion()
		{
			String newPiece;
			boolean finished = false;
			while (!finished)
			{
				newPiece = JOptionPane.showInputDialog("Choose a new piece Q / R / B / K");
				counter = pieceChosen;
				if (newPiece.equalsIgnoreCase("Q"))
				{
					if (pieces[pieceChosen].color.equals("white"))
						GetFileAndName(8,4);
					if (pieces[pieceChosen].color.equals("black"))
						GetFileAndName(1,4);
					finished = true;
				}
				if (newPiece.equalsIgnoreCase("R"))
				{
					if (pieces[pieceChosen].color.equals("white"))
						GetFileAndName(8,1);
					if (pieces[pieceChosen].color.equals("black"))
						GetFileAndName(1,1);
					finished = true;
				}
				if (newPiece.equalsIgnoreCase("B"))
				{
					if (pieces[pieceChosen].color.equals("white"))
						GetFileAndName(8,3);
					if (pieces[pieceChosen].color.equals("black"))
						GetFileAndName(1,3);
					finished = true;
				}
				if (newPiece.equalsIgnoreCase("K"))
				{
					if (pieces[pieceChosen].color.equals("white"))
						GetFileAndName(8,2);
					if (pieces[pieceChosen].color.equals("black"))
						GetFileAndName(1,2);
					finished = true;
				}
			}
			promotion = false;
		}

		//allows for pieces to move; x,y are the coordinates for the square you're moving to
		public void MovePiece(int x, int y)
		{
			ok = false; //releases the current piece if you can't move it
			boolean foundpiece = false; //detects if a piece is found in the new square
			boolean function[] = new boolean[6];
			int pieceNum = 40;
			int pieceTaken = 40;
			int ptxcoor = 0;
			int ptycoor = 0;
			oldx = y/50+1;
			oldy = x/50+1;
			for (int i = 0; i < 32; i++)
			{
				if (pieceChosen != i)
				{
					if (oldx == pieces[i].row && oldy == pieces[i].column)
					{
						foundpiece = true;
						pieceNum = i;
					}
				}
			}
			function[0] = PawnRules(x,y,foundpiece,pieceNum);
			function[1] = KingRules(x,y,foundpiece,pieceNum);
			function[2] = KnightRules(x,y,foundpiece,pieceNum);
			function[3] = BishopRules(x,y,foundpiece,pieceNum);
			function[4] = RookRules(x,y,foundpiece,pieceNum);
			function[5] = QueenRules(x,y,foundpiece,pieceNum);
			if (function[0] ^ function[1] ^ function[2] ^ function[3] ^ function[4] ^ function[5])
			{
				if (promotion)
					PawnPromotion();
				oldx = pieces[pieceChosen].row;
				oldy = pieces[pieceChosen].column;
				pieces[pieceChosen].row = y/50+1;
				pieces[pieceChosen].column = x/50+1;

				for (int i = 0; i < 32; i++)
				{
					if (pieceChosen != i)
					{
						if (pieces[pieceChosen].row == pieces[i].row && pieces[pieceChosen].column == pieces[i].column && !pieces[i].name.equals("King"))
						{
							pieceTaken = i;
							ptxcoor = pieces[i].row;
							ptycoor = pieces[i].column;
							pieces[i].row = 11;
							pieces[i].column = 11;
							pieces[i].visible = false;
						}
					}
				}
				if (CheckMethod())
				{
					if (pieces[pieceChosen].color.equals(pieces[whichKing].color))
					{
						pieces[pieceChosen].row = oldx;
						pieces[pieceChosen].column = oldy;
						whoseTurn = (whoseTurn + 1) % 2;
					}
				}
				if (checked)
				{
					if (CheckMethod())
					{
						if (pieces[pieceChosen].color.equals(pieces[whichKing].color))
						{
							if (pieceTaken != 40)
							{
								pieces[pieceTaken].row = ptxcoor;
								pieces[pieceTaken].column = ptycoor;
								pieces[pieceTaken].visible = true;
								pieces[pieceChosen].row = oldx;
								pieces[pieceChosen].column = oldy;
								whoseTurn = (whoseTurn + 1) % 2;
								repaint();
								return;
							}
						}
					}
				}
                                
                                //alternating player turns
				whoseTurn = (whoseTurn + 1) % 2;
				repaint();
				if (checked)
					JOptionPane.showMessageDialog(null, "check");
			}
			else
				repaint();
		}
	}
}