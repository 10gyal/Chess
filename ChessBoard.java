import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}

//Name: Tashi Tengyal
//StudentID#:  2020-15441
public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard(){
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}
	
	public final void initBoardStatus(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
		}
	}
	
	public final void initPieceImages(){
		pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		
		pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}
	
	public ImageIcon getImageIcon(Piece piece){
		if(piece.color.equals(PlayerColor.black)){
			if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
			else return pieceImage_b[6];
		}
		else if(piece.color.equals(PlayerColor.white)){
			if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
			else return pieceImage_w[6];
		}
		else return pieceImage_w[6];
	}

	public final void initializeGui(){
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
	    JToolBar tools = new JToolBar();
	    tools.setFloatable(false);
	    gui.add(tools, BorderLayout.PAGE_START);
	    JButton startButton = new JButton("Reset");
	    startButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		initiateBoard();
	    	}
	    });
	    
	    tools.add(startButton);
	    tools.addSeparator();
	    tools.add(message);

	    chessBoard = new JPanel(new GridLayout(0, 8));
	    chessBoard.setBorder(new LineBorder(Color.BLACK));
	    gui.add(chessBoard);
	    ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	    Insets buttonMargin = new Insets(0,0,0,0);
	    for(int i=0; i<chessBoardSquares.length; i++) {
	        for (int j = 0; j < chessBoardSquares[i].length; j++) {
	        	JButton b = new JButton();
	        	b.addActionListener(new ButtonListener(i, j));
	            b.setMargin(buttonMargin);
	            b.setIcon(defaultIcon);
	            if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
	            else b.setBackground(Color.gray);
	            b.setOpaque(true);
	            b.setBorderPainted(false);
	            chessBoardSquares[j][i] = b;
	        }
	    }

	    for (int i=0; i < 8; i++) {
	        for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);
	        
	    }
	}

	public final JComponent getGui() {
	    return gui;
	}
	
	public static void main(String[] args) {
	    Runnable r = new Runnable() {
	        @Override
	        public void run() {
	        	ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
	}
		
			//================================Utilize these functions========================================//	
	
	class Piece{
		PlayerColor color;
		PieceType type;
		
		Piece(){
			color = PlayerColor.none;
			type = PieceType.none;
		}
		Piece(PlayerColor color, PieceType type){
			this.color = color;
			this.type = type;
		}
	}
	
	public void setIcon(int x, int y, Piece piece){
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}
	
	public Piece getIcon(int x, int y){
		return chessBoardStatus[y][x];
	}
	
	public void markPosition(int x, int y){
		chessBoardSquares[y][x].setBackground(Color.pink);
	}
	
	public void unmarkPosition(int x, int y){
		if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
		else chessBoardSquares[y][x].setBackground(Color.gray);
	}
	
	public void setStatus(String inpt){
		message.setText(inpt);
	}
	
	public void initiateBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for(int i=0;i<8;i++){
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) unmarkPosition(i, j);
		}
		onInitiateBoard();
	}
//======================================================Don't modify above==============================================================//	


//======================================================Implement below=================================================================//		
	enum MagicType {MARK, CHECK, CHECKMATE};
	private int selX, selY;
	private boolean check, checkmate;
	private boolean notEnd = true;
	PlayerColor turn = PlayerColor.black;
	String checkMsg="";
	
	class ButtonListener implements ActionListener{
		int x;
		int y;
		ButtonListener(int x, int y){
			this.x = x;
			this.y = y;
		}
		public void actionPerformed(ActionEvent e) {
			// Only modify here
			if(notEnd){
				if(getIcon(x,y).color == turn){
					unmarking();
					possibleMoves(x, y, getIcon(x, y));
				}
				//mark
				else if(chessBoardSquares[y][x].getBackground() == Color.pink){
					if(getIcon(x, y).type == PieceType.king){
						notEnd=false;
					}
					setIcon(x, y, getIcon(selX, selY));
					setIcon(selX, selY, new Piece());
					unmarking();
					if(!notEnd){
						setStatus(turn + "Won!");
						return;
					}
					if(turn == PlayerColor.black){
						turn = PlayerColor.white;
					} else{
						turn = PlayerColor.black;
					}
					checkMsg = "";
					//if game ended, who won? condition
					//CheckMate status
					if(checkStatus(turn)){
						checkMsg = "/CHECK";
						if(checkmateStatus(turn)){
							checkMsg = "/ CHECKMATE!";
							notEnd = false;
						}
					}
					//setstatus
					setStatus(turn + "'s TURN" + checkMsg);
				} else {
					unmarking();
				}
				selX = x;
				selY = y;
			}
		}

	}

	public void possibleMoves(int x, int y, Piece piece){
		unmarking();
		////PAWN
		if(piece.type == PieceType.pawn){
			pawnMoves(x, y, piece);
		}
		////KNIGHT
		else if(piece.type == PieceType.knight){
			knightMoves(x, y, piece);
		}
		////KING
		else if(piece.type == PieceType.king){
			kingMoves(x, y, piece);
		}
		////BISHOP
		else if(piece.type == PieceType.bishop){
			bishopMoves(x, y, piece);
		}
		////ROOK
		else if(piece.type == PieceType.rook){
			rookMoves(x, y, piece);
		}
		////QUEEN
		else if(piece.type == PieceType.queen){
			//since a queen mimics the movement of both bishop and rook
			bishopMoves(x, y, piece);
			rookMoves(x, y, piece);
		}

	}

	public void pawnMoves(int x, int y, Piece piece){
		if(piece.color== PlayerColor.white){
			if(legalMoves(x-1, 1)){			//dont care about y value
				if(getIcon(x-1, y).type == PieceType.none) {
					markPosition(x - 1, y);
					if(x == 6 && getIcon(x-2, y).type == PieceType.none){
						markPosition(x-2, y); // 2 step move for white pawn
					}
				}
				if(legalMoves(x-1, y-1)){
					if(getIcon(x-1, y-1).color == PlayerColor.black){
						markPosition(x-1, y-1); //can capture left
					}
				}
				if(legalMoves(x-1, y+1)){
					if(getIcon(x+-1, y+1).color == PlayerColor.black){
						markPosition(x-1, y+1); //can capture right
					}
				}
			}
		} else{
			if(legalMoves(x+1, 1)){	//dont care about y value
				if(getIcon(x+1, y).type == PieceType.none){
					markPosition(x+1, y);
					if(x == 1 && getIcon(x+2, y).type == PieceType.none){
						markPosition(x+2, y);
					}
				}
			}
			//can capture left
			if(legalMoves(x+1, y-1)){
				if(getIcon(x+1, y-1).color == PlayerColor.white){
					markPosition(x+1, y-1);
				}
			}
			//can capture right
			if(legalMoves(x+1, y+1)){
				if(getIcon(x+1, y+1).color == PlayerColor.white){
					markPosition(x+1, y+1);
				}
			}

		}
	}

	public void knightMoves(int x, int y, Piece piece){
		int[] a = {1, -1};
		int[] b = {2, -2};
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if(legalMoves(x+a[i], y+b[j])){
					if (getIcon(x + a[i], y + b[j]).color != piece.color)
						markPosition(x + a[i], y + b[j]);
				}
				if(legalMoves(x+b[i], y+a[j])){
					if (getIcon(x + b[i], y + a[j]).color != piece.color)
						markPosition(x + b[i], y + a[j]);
				}
			}
		}
	}

	public void kingMoves(int x, int y, Piece piece){
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int new_x = x+i-1;
				int new_y = y+j-1;
				if (legalMoves(new_x, new_y)){
					if (getIcon(new_x, new_y).type != PieceType.king && getIcon(new_x, new_y).color != piece.color)
						markPosition(new_x, new_y);
				}
			}
		}
		unmarkPosition(x, y);
	}

	public void bishopMoves(int x, int y, Piece piece){
		ObstacleChecker(x, y, 1, 1, piece.color);
		ObstacleChecker(x, y, 1, -1, piece.color);
		ObstacleChecker(x, y, -1, 1, piece.color);
		ObstacleChecker(x, y, -1, -1, piece.color);
	}

	public void rookMoves(int x, int y, Piece piece){
		ObstacleChecker(x, y, 1, 0, piece.color);
		ObstacleChecker(x, y, 0, 1, piece.color);
		ObstacleChecker(x, y, -1, 0, piece.color);
		ObstacleChecker(x, y, 0, -1, piece.color);
	}

	public void turnSwap(PlayerColor p1, PlayerColor p2){
		if(p1 == PlayerColor.white){
			p2 = PlayerColor.black;
		} else if(p1 == PlayerColor.black){
			p2 = PlayerColor.white;
		}
	}

	public void ObstacleChecker(int x, int y, int x_dir, int y_dir, PlayerColor c){
			int unit_x = x_dir;
			int unit_y = y_dir;
			PlayerColor opponent = PlayerColor.white;
			turnSwap(c, opponent);
			while (legalMoves(x+x_dir, y+y_dir)){
				int new_x = x+x_dir;
				int new_y = y+y_dir;
				if(getIcon(new_x, new_y).color == opponent) {
					markPosition(new_x, new_y);
					break;
				} else if(getIcon(new_x, new_y).color == c){
					break;
				}
				markPosition(new_x, new_y);
				x_dir += unit_x;
				y_dir += unit_y;
			}
	}

	public boolean checkStatus(PlayerColor color){
			unmarking();
			int x = 0;
			int y = 0;
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {
					if (chessBoardStatus[j][i].type == PieceType.king && chessBoardStatus[j][i].color == color) {
						x = i;
						y = j;
					}
				}
			}
			PlayerColor opponent;
			if(turn == PlayerColor.black){
				opponent = PlayerColor.white;
			} else{
				opponent = PlayerColor.black;
			}
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {
					if (chessBoardStatus[j][i].color == opponent) {
						possibleMoves(i, j, getIcon(i, j));
						if(chessBoardSquares[y][x].getBackground()==Color.pink){
							unmarking();
							return true;
						}
					}
				}
			}
			unmarking();
			return false;

		}

	public boolean checkmateStatus(PlayerColor color){
			for(int i = 0; i <= 7; i++){
				for(int j = 0; j <= 7; j++){
					if(chessBoardStatus[j][i].color == color){
						Piece old = getIcon(i, j);
						possibleMoves(i, j, old);
						for(int k = 0; k<= 7; k++){
							for(int l = 0; l <=7; l++){
								if(chessBoardSquares[l][k].getBackground() == Color.pink){
									Piece opponent = getIcon(k, l);
									setIcon(k, l, old);
									setIcon(i, j, new Piece());

									if(!checkStatus(color)){
										setIcon(i, j, old);
										setIcon(k, l, opponent);
										return false;
									}
									setIcon(i, j, old);
									setIcon(k, l, opponent);
									possibleMoves(i, j, old);
								}
							}
						}
						unmarking();
					}
				}
			}
			return true;
		}

	public void unmarking(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) {
				unmarkPosition(i, j);
			}

		}
	}

	public boolean legalMoves(int x, int y){
			if(x>7 || x<0 || y>7 || y<0){
				return false;
			}
			return true;
	}

	void onInitiateBoard(){
		notEnd = true;
		turn = PlayerColor.black;
		setStatus("BLACK's TURN");
	}
}