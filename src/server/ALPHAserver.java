package server;

import java.io.Serializable;

import info.userInfo;
import info.gameInfo;
import info.progressInfo;
import drawing.point;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class ALPHAserver extends Thread
{
// ** VARIABLE **
	// For connection
	ServerSocket ss;
	ArrayList<ServerClient> scList;
	ArrayList<gameInfo> giList;
	ArrayList<String> wordList;
	
	String recentLobbyChat;
	Random rand;
	
	// For timer management
	long startTime;
	long currentTime;
	long elapsedTime;
	
	static final int ROUND_NUM = 7;
	
// **CONSTRUCTOR **
	public ALPHAserver ()
	{
		// Initialize data
		recentLobbyChat = new String (); 
		scList = new ArrayList<ServerClient> ();
		giList = new ArrayList<gameInfo> ();
		
		ActionListener action = new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				timerBroadcast ();
			}
		};
	    try
	    {
	       ss = new ServerSocket(3333);
	       System.out.println("open server!");
	       Timer ALPHAtimer = new Timer (1000, action);
	       ALPHAtimer.setRepeats (true);
	       ALPHAtimer.start ();
	    }
	    catch (Exception e)
	    {
	       e.printStackTrace();
	    }
	}

// ** METHOD **
	public static void main(String[] args)
	{
		new ALPHAserver().start();
	}
	
	@Override
	public void run()
	{
		initWordList ();
		initRandom ();
		while(true)
		{
			try
			{
				Socket s = ss.accept();
				System.out.println ("Client connected!");
				ServerClient sc = new ServerClient(s, this);
				scList.add(sc);
				sc.start();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}	
		}
	}
	
	// INPUT: null
	// OUTPUT: null
	// Objective: Initialize the word data
	private void initWordList ()
	{
		wordList = new ArrayList<String> ();
		wordList.add ("��Ʈ��ũ"); wordList.add ("����ȸ��"); wordList.add ("�л�ȸ��"); wordList.add ("78���"); wordList.add ("��2���а�");
		wordList.add ("�����ٶ��"); wordList.add ("����"); wordList.add ("CC"); wordList.add ("����"); wordList.add ("MT");
		wordList.add ("ź��"); wordList.add ("����"); wordList.add ("���"); wordList.add ("����"); wordList.add ("�̻�ȭź��");
		wordList.add ("��������"); wordList.add ("�Ϲ�ȭ��"); wordList.add ("���α׷��ְ������ذ�"); wordList.add ("�Ϲݻ������"); wordList.add ("�Ϲݹ�������");
		wordList.add ("���б�"); wordList.add ("��©"); wordList.add ("��©"); wordList.add ("�볪��"); wordList.add ("����");
		wordList.add ("�⼮"); wordList.add ("�Ἦ"); wordList.add ("����"); wordList.add ("�߰����"); wordList.add ("�⸻���");
		wordList.add ("�����"); wordList.add ("���Ƹ�"); wordList.add ("ü����"); wordList.add ("RC"); wordList.add ("�����");
		wordList.add ("����ŷ"); wordList.add ("�л��Ĵ�"); wordList.add ("������"); wordList.add ("�������̵�"); wordList.add ("����");
		wordList.add ("���"); wordList.add ("������"); wordList.add ("�ö�ũ"); wordList.add ("����"); wordList.add ("�з�����");
		wordList.add ("Ʃ��"); wordList.add ("���̽���"); wordList.add ("īī����"); wordList.add ("����"); wordList.add ("���İ�");
		wordList.add ("��ī��"); wordList.add ("�ظ����Ѹ���"); wordList.add ("�������м�������"); wordList.add ("�а��"); wordList.add ("���ں�");
		wordList.add ("��Ǯ��"); wordList.add ("�󳲰�"); wordList.add ("Ʈ����"); wordList.add ("����"); wordList.add ("������");
		wordList.add ("��������"); wordList.add ("���п�"); wordList.add ("�߽�"); wordList.add ("�м�"); wordList.add ("��������");
		wordList.add ("��ȯ�л�"); wordList.add ("���׿��극����"); wordList.add ("�˴ٿ���"); wordList.add ("��ħ����"); wordList.add ("���ְ���");
		wordList.add ("����"); wordList.add ("����"); wordList.add ("�������帵ũ"); wordList.add ("�İ���"); wordList.add ("�۽ɻ���");
		wordList.add ("������û"); wordList.add ("��Ƽ��"); wordList.add ("������Ʈ"); wordList.add ("ġŲ"); wordList.add ("����Ʈ");
		wordList.add ("����"); wordList.add ("�Ŷ��"); wordList.add ("���̾�Ʈ"); wordList.add ("LMS"); wordList.add ("���º�");
		wordList.add ("����"); wordList.add ("����Ʈ��"); wordList.add ("��ǳ�Ǿ��"); wordList.add ("�������"); wordList.add ("�븮�⼮");
		wordList.add ("LINQ"); wordList.add ("����ũž"); wordList.add ("��ȭ��"); wordList.add ("�ڵ���������"); wordList.add ("������");
		wordList.add ("�л�ȸ��"); wordList.add ("�����б�"); wordList.add ("��������"); wordList.add ("�ܿ����"); wordList.add ("��ü������");
		wordList.add ("�볪����"); wordList.add ("���帶ī"); wordList.add ("��ȭ�ݷ�����"); wordList.add ("�ι��������Ǽ���"); wordList.add ("������");
		wordList.add ("�޷γ�"); wordList.add ("����"); wordList.add ("�������͸�"); wordList.add ("���ڿ���"); wordList.add ("������");
		wordList.add ("�ѽ��帲"); wordList.add ("�Ե���Ʈ"); wordList.add ("Ȩ�÷���"); wordList.add ("�ÿܹ����͹̳�"); wordList.add ("��ӹ����͹̳�");
		wordList.add ("����Ų���"); wordList.add ("��ȸ"); wordList.add ("���׺Ϻ��ؼ�����"); wordList.add ("����"); wordList.add ("����");
		wordList.add ("����Ʈ��"); wordList.add ("�������"); wordList.add ("����"); wordList.add ("�Ƹ�����Ʈ"); wordList.add ("������");
		wordList.add ("����������"); wordList.add ("�º�"); wordList.add ("������"); wordList.add ("����"); wordList.add ("�л���");
		wordList.add ("�ֱ���"); wordList.add ("�ż���"); wordList.add ("��û��"); wordList.add ("GSR"); wordList.add ("û����Ż");
		wordList.add ("ȿ�ڽ���"); wordList.add ("�������"); wordList.add ("��������"); wordList.add ("������"); wordList.add ("�����÷���");
		wordList.add ("���ڰ��ӱ�"); wordList.add ("��Ʈ�ʱ�"); wordList.add ("�ù�"); wordList.add ("���"); wordList.add ("������");
		wordList.add ("��������"); wordList.add ("��й�ȣ"); wordList.add ("������"); wordList.add ("��ǰ"); wordList.add ("��ũ����");
		wordList.add ("�ҹ��"); wordList.add ("��������"); wordList.add ("ǲ��"); wordList.add ("����"); wordList.add ("����");
		wordList.add ("����"); wordList.add ("��ġ"); wordList.add ("�ֽĽ�"); wordList.add ("�������"); wordList.add ("��");
		wordList.add ("ũ��������"); wordList.add ("���Ƹ�����ȸ"); wordList.add ("�����ؽô�"); wordList.add ("������"); wordList.add ("���9��");
		wordList.add ("��ȭ"); wordList.add ("�ν���ƮĿ��"); wordList.add ("����"); wordList.add ("��õ��뷡��"); wordList.add ("��������");
		wordList.add ("��������"); wordList.add ("����"); wordList.add ("����ε�"); wordList.add ("Ŀư"); wordList.add ("�����±�");
		wordList.add ("�͹̳�����"); wordList.add ("���߱�"); wordList.add ("�¾����Ŀ�"); wordList.add ("�������Ź��"); wordList.add ("â��");
		wordList.add ("���뼱�������"); wordList.add ("�粿ġ��Ī����"); wordList.add ("��Ƽ�ӽ�������"); wordList.add ("���̾��"); wordList.add ("��Ŀ");
		wordList.add ("ĸƾ�Ƹ޸�ī"); wordList.add ("�۾���"); wordList.add ("������"); wordList.add ("������"); wordList.add ("����ī��");
		wordList.add ("���"); wordList.add ("�̴Ͼ�"); wordList.add ("ĳ�����������"); wordList.add ("���Ҽ����º��"); wordList.add ("���");
		wordList.add ("����"); wordList.add ("�ҳ���"); wordList.add ("�尩��"); wordList.add ("��Ÿ����"); wordList.add ("���װ������б�");
	}

	// INPUT: null
	// OUTPUT: null
	// Objective: For all the clients, update chats in lobby panel
	public void lobbyChatUpdateAll ()
	{
		for (ServerClient sc: scList)
		{
			try
			{
				if (sc.userInLobby ())
				{
					progressInfo pi_ack = new progressInfo ();
					pi_ack.set_status (progressInfo.CHAT_LOBBY_UPDATE);
					pi_ack.set_lobbyChat (recentLobbyChat);
					sc.lockedWrite (pi_ack);
				}
			}
			catch (Exception e)
		    {
				e.printStackTrace();
		    }
		}
	}
	
	// INPUT: null
	// OUTPUT: null
	// Objective: For debugging
	public void printUsers ()
	{
		System.out.println ("USERS:");
		for (ServerClient sc: scList)
		{
			System.out.println ("| " + sc.getUserNickname ());
		}
	}
	
	// INPUT: null
	// OUTPUT: null
	// Objective: For debugging
	public void printGames ()
	{
		System.out.println ("GAMES:");
		for (gameInfo gi: giList)
		{
			System.out.println ("| " + gi.get_gameName ());
		}
	}

	// INPUT: nickname of the user trying to enter the lobby
	// OUTPUT: null
	// Objective: Check if the nickname is already in use
	public boolean checkDuplicateUser (String new_nickName)
	{
		for (ServerClient sc: scList)
		{
			if (sc.ui.get_nickName().equals(new_nickName))
				return true;
		}
		return false;
	}
	
	// INPUT: name of the game trying to create
	// OUTPUT: null
	// Objective: Check if the name is already in user
	public boolean checkDuplicateGame (String new_gameName)
	{
		for (gameInfo gi: giList)
		{
			if (gi.get_gameName().equals(new_gameName))
				return true;
		}
		return false;
	}
	
	// INPUT: available name of the newly created game
	// OUTPUT: null
	// Objective: Create data structure for the game
	public void newGame (String roomName)
	{
		giList.add (new gameInfo (gameInfo.WATING, roomName, 1));
	}
	
	// INPUT: null
	// OUTPUT: null
	// Objective: For all the clients, update list of games in lobby panel
	public void lobbyGameAllUpdate ()
	{
		for (ServerClient sc: scList)
		{
			try
			{
				progressInfo pi_ack = new progressInfo ();
				pi_ack.set_status (progressInfo.GAME_LOBBY_UPDATE);
				pi_ack.set_gamesLobby (giListNames ());
				sc.lockedWrite (pi_ack);
			}
			catch (Exception e)
		    {
				e.printStackTrace ();
		    }
		}
	}
	
	// INPUT: null
	// OUTPUT: null
	// Objective: For all the clients, update list of users in lobby panel
	public void lobbyUserAllUpdate ()
	{
		for (ServerClient sc: scList)
		{
			try
			{
				progressInfo pi_ack = new progressInfo ();
				pi_ack.set_status (progressInfo.USER_LOBBY_UPDATE);
				pi_ack.set_usersLobby (userListNames ());
				sc.lockedWrite (pi_ack);
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	
	// INPUT: name of the target game
	// OUTPUT: null
	// Objective: For all the clients in the game, update list of users in game panel
	public void gameUserAllUpdate (String gameName)
	{
		for (ServerClient sc: scList)
		{
			try
			{
				if (sc.getUserInfo ().get_gameName ().equals (gameName))
				{
					progressInfo pi_ack = new progressInfo ();
					pi_ack.set_status (progressInfo.USER_GAME_UPDATE);
					pi_ack.set_usersGame(getUsersGame (gameName));
					sc.lockedWrite (pi_ack);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	
	// INPUT: null
	// OUTPUT: list of game names
	// Objective: Get names of the existing games
	public ArrayList<String> giListNames ()
	{
		int length = giList.size ();
		ArrayList<String> names = new ArrayList<String> ();
		for (int i = 0; i < length; i++)
		{
			names.add (giList.get (i).get_gameName ());
		}
		return names;
	}
	
	// INPUT: null
	// OUTPUT: list of users' nicknames
	// Objective: Get nicknames of connected users
	public ArrayList<String> userListNames ()
	{
		int length = scList.size ();
		ArrayList<String> names = new ArrayList<String> ();
		for (int i = 0; i < length; i++)
		{
			if (scList.get (i).getUserStatus () == userInfo.IN_LOBBY && !(scList.get (i).getUserNickname ().equals ("")))
				names.add (scList.get (i).getUserNickname ());
		}
		return names;
	}
	
	// INPUT: nickname of the joining user, name of the target game
	// OUTPUT: null
	// Objective: Update data structures about input informations.
	public void userJoinGame (String nickName, String gameName)
	{
		for (gameInfo gi: giList)
		{
			try
			{
				if (gi.get_gameName ().equals (gameName))
				{
					gi.inc_participants ();
					System.out.println ("NUM: "+ gi.get_participants ());
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
		
		for (ServerClient sc: scList)
		{
			try
			{
				System.out.println (sc.getUserInfo().get_nickName() + " : " + sc.getUserInfo ().get_gameName ());
				if (sc.getUserInfo ().get_gameName ().equals (gameName))
				{
					System.out.println (sc.getUserInfo().get_nickName());
					progressInfo pi_ack = new progressInfo ();
					pi_ack.set_status (progressInfo.JOIN_GAME_NEW);
					pi_ack.set_usersGame (getUsersGame (gameName));
					sc.lockedWrite (pi_ack);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	
	// INPUT: name of the target game
	// OUTPUT: null
	// Objective: Check if the game is full or already started	
	public boolean checkFull (String gameName)
	{
		for (gameInfo gi: giList)
		{
			try
			{
				if (gi.get_gameName ().equals (gameName))
				{
					if (gi.get_participants () == 6 || gi.get_status() == gameInfo.PLAYING)
						return true;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
		return false;
	}
	
	// INPUT: name of the target game
	// OUTPUT: null
	// Objective: Update data structure of game. Notify and update panels for the left users in that game.
	public void userExitGame (String gameName)
	{
		loop: for (int i = 0; i < giList.size (); i++)
		{
			try
			{
				if (giList.get (i).get_gameName().equals (gameName))
				{
					if (giList.get (i).get_participants () == 1)
					{
						giList.remove(i);
						lobbyGameAllUpdate ();
					}
					else
						giList.get (i).dec_participants ();
					break loop;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	  
	// INPUT: name of the target game
	// OUTPUT: list of users in that game
	// Objective: Get the list of users for given game name
	public ArrayList<userInfo> getUsersGame (String gameName)
	{
		ArrayList<userInfo> ui = new ArrayList<userInfo> ();
		for (ServerClient sc: scList)
		{
			if (sc.getUserInfo ().get_gameName ().equals (gameName))
			{
				ui.add(sc.getUserInfo ());
			}
		}
		return ui;
	}
	
	// INPUT: null
	// OUTPUT: null
	// Objective: For debugging
	public void printGi ()
	{
		for (gameInfo gi: giList)
		{
			System.out.println ("status: " + gi.get_status () + "gameName: " + gi.get_gameName() + "participants: " + gi.get_participants ());
		}
	}
	
	// INPUT: name of the target game
	// OUTPUT: true or false
	// Objective: Check if the game can be started (enough participants)
	public boolean startAvailable (String gameName)
	{
		for (gameInfo gi: giList)
		{
			if (gi.get_gameName ().equals (gameName))
			{
				if (gi.get_participants () >= 2)
					return true;
			}
		}
		return false;
	}
	
	// INPUT: name of the target game
	// OUTPUT: null
	// Objective: For all the clients in the game, notify to start the game
	public void startGameAll (String gameName)
	{
		for (gameInfo gi: giList)
		{
			if (gi.get_gameName ().equals (gameName))
			{
				gi.set_status (gameInfo.PLAYING);
				gi.set_roundNum (gameInfo.ROUND_NUM);
			}
		}
		String questioner = "";
		for (ServerClient sc: scList)
		{
			try
			{
				if (sc.getUserInfo ().get_gameName ().equals (gameName))
				{
					progressInfo pi_ack = new progressInfo ();
					if (sc.getUserInfo ().get_isMaster ())
					{
						questioner = new String (sc.getUserNickname ());
						sc.getUserInfo ().set_status(userInfo.IN_GAME_QUESTIONER);
						pi_ack.set_status (progressInfo.START_APPROVE_QUESTIONER);
						for (gameInfo gi: giList)
						{
							try
							{
								if (gi.get_gameName ().equals (gameName))
								{
									gi.set_roundNum (ROUND_NUM - 1);
									gi.set_roundAnswer (getRandomWord ());
									pi_ack.set_chat (gi.get_roundAnswer ());
								}
							}
							catch (Exception e)
							{
								e.printStackTrace ();
							}
						}
					}
					else
					{
						sc.getUserInfo ().set_status(userInfo.IN_GAME_ANSWERER);
						pi_ack.set_status (progressInfo.START_APPROVE_ANSWERER);
					}
					pi_ack.set_imagePath (questioner);
					sc.lockedWrite (pi_ack);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	
	// INPUT: name of the target game, point list to draw
	// OUTPUT: null
	// Objective: If questioner draws to canvas, broadcast the drawing to the users in the game
	public void drawBroadcast (String gameName, ArrayList<point> pList)
	{
		for (ServerClient sc:scList)
		{
			try
			{
				if (sc.getUserInfo ().get_gameName ().equals (gameName))
				{
					progressInfo pi_broadcast = new progressInfo ();
					pi_broadcast.set_status (progressInfo.DRAW_BROADCAST);
					pi_broadcast.set_pList (pList);
					sc.lockedWrite (pi_broadcast);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	
	// INPUT: name of the target game
	// OUTPUT: null
	// Objective: For all the clients in the game, clear the canvas
	public void clearBroadcast (String gameName)
	{
		for (ServerClient sc:scList)
		{
			try
			{
				if (sc.getUserInfo ().get_gameName ().equals (gameName))
				{
					progressInfo pi_broadcast = new progressInfo ();
					pi_broadcast.set_status (progressInfo.SELECT_CLEAR_BROADCAST);
					sc.lockedWrite (pi_broadcast);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	
	// INPUT: name of the target game
	// OUTPUT: null
	// Objective: For all the clients in the game, set the eraser mode
	public void eraserBroadcast (String gameName)
	{
		for (ServerClient sc:scList)
		{
			try
			{
				if (sc.getUserInfo ().get_gameName ().equals (gameName))
				{
					progressInfo pi_broadcast = new progressInfo ();
					pi_broadcast.set_status (progressInfo.SELECT_ERASER_BROADCAST);
					sc.lockedWrite (pi_broadcast);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	
	// INPUT: name of the target game
	// OUTPUT: null
	// Objective: For all the clients in the game, change the color as questioner selected	
	public void colorBroadcast (String gameName, int drawingColor)
	{
		for (ServerClient sc:scList)
		{
			try
			{
				if (sc.getUserInfo ().get_gameName ().equals (gameName))
				{
					progressInfo pi_broadcast = new progressInfo ();
					pi_broadcast.set_status (progressInfo.SELECT_COLOR_BROADCAST);
					pi_broadcast.set_drawColor (drawingColor);
					sc.lockedWrite (pi_broadcast);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	
	// INPUT: name of the target game, nickname of the most recent questioner 
	// OUTPUT: null
	// Objective:
	// For all the clients in the game, notifies that the current round ended and the next questioner
	// For the new questioner, notify new round answer
	public void timerExpireBroadcast (String gameName, String recentQuestioner)
	{
		int i;
		String nextQuestioner;
		while (true)
		{
			System.out.println ("SERVER: In while: " + scList.size ());
			i = rand.nextInt (scList.size ());
			userInfo selected = scList.get (i).getUserInfo ();
			if (!(selected.get_nickName ().equals (recentQuestioner)) && (selected.get_gameName ().equals (gameName)))
			{
				nextQuestioner = new String (scList.get (i).getUserInfo ().get_nickName ()); 
				break;
			}
		}
		//System.out.println ("SERVER: Out while");
		for (gameInfo gi: giList)
		{
			try
			{
				if (gi.get_gameName ().equals (gameName))
				{
					if (gi.get_roundNum () > 0)
					{
						gi.set_roundNum (gi.get_roundNum () - 1);
						System.out.println ("roundNum : " + gi.get_roundNum () + "in timer expired");
						for (ServerClient sc:scList)
						{
							try
							{
								if (sc.getUserInfo ().get_gameName ().equals (gameName))
								{
									progressInfo pi_ack = new progressInfo ();
									pi_ack.set_imagePath (nextQuestioner);
									if (sc.getUserInfo ().get_nickName ().equals (nextQuestioner))
									{System.out.println ("IN if");
										sc.getUserInfo ().set_status(userInfo.IN_GAME_QUESTIONER);
										pi_ack.set_status (progressInfo.START_APPROVE_QUESTIONER);
										gi.set_roundAnswer (getRandomWord ());
										pi_ack.set_chat (gi.get_roundAnswer ());
									}
									else
									{System.out.println ("IN else");
										sc.getUserInfo ().set_status(userInfo.IN_GAME_ANSWERER);
										pi_ack.set_status (progressInfo.START_APPROVE_ANSWERER);
									}
									System.out.println ("  SEND| chat:" + pi_ack.get_chat() + " iPath:" + pi_ack.get_imagePath());
									System.out.println ("        status:" + pi_ack.get_status ());
									sc.lockedWrite (pi_ack);
									System.out.println ("AFTER lockedWrite - expire broadcast");
								}
							}
							catch (Exception e)
							{
								e.printStackTrace ();
							}
						}
					}
					else // roundNum <= 0
					{
						progressInfo pi_broadcast = new progressInfo ();
						pi_broadcast.set_status (progressInfo.ROUND_TERMINATE);
						pi_broadcast.set_chat (findWinner (gameName));
						for (ServerClient sc: scList)
						{
							if (sc.getUserInfo ().get_gameName ().equals (gameName))
							{
								sc.lockedWrite (pi_broadcast);
								sc.getUserInfo ().set_score (0);
							}
						}
						gi.set_status (gameInfo.WATING);
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
		
	}
	
	// INPUT: name of the target game
	// OUTPUT: null
	// Objective: For all the clients in the game, show the whole game result
	private String findWinner (String gameName)
	{
		String winner = "";
		int scoreMax = 0;
		for (ServerClient sc: scList)
		{
			if (sc.getUserInfo ().get_score () >= scoreMax)
				scoreMax = sc.getUserInfo ().get_score ();
		}
		for (ServerClient sc: scList)
		{
			System.out.println (sc.getUserNickname () + ": " + sc.getUserInfo ().get_score ());
			if (sc.getUserInfo ().get_score () == scoreMax)
				winner += (sc.getUserNickname () + " ");
		}
		winner += ("won this game!");
		System.out.println (winner);
		return winner;
	}
	
	// INPUT: null
	// OUTPUT: null
	// Objective: Initialize random variable that is used to select next questioner and next round answer
	private void initRandom ()
	{
		rand = new Random ();
	}
	
	// INPUT: null
	// OUTPUT: round answer
	// Objective: Get random word from word list
	private String getRandomWord ()
	{
		return wordList.get (rand.nextInt (199));
	}
	
	// INPUT: name of the target game, nickname of chat's owner, contents of chat
	// OUTPUT: null
	// Objective:
	// For newly typed chat, check if it is correct
	// If correct, notify all the users in the game that the chat's owner got correct
	// If not correct, just update the chats in game
	public void checkAnswer (String gameName, String nickName, String chat)
	{
		for (gameInfo gi: giList)
		{
			if (gi.get_gameName ().equals (gameName))
			{
				if (gi.get_roundAnswer ().equals (chat))
				{//System.out.println("CORRECT");
					progressInfo pi_broadcast = new progressInfo ();
					pi_broadcast.set_status (progressInfo.CORRECT_ANSWER);
					pi_broadcast.set_chat (nickName);
					pi_broadcast.set_imagePath (chat);
					for (ServerClient sc: scList)
					{
						if (sc.getUserInfo ().get_gameName ().equals (gameName))
							sc.lockedWrite (pi_broadcast);
						if (sc.getUserInfo ().get_nickName ().equals (nickName))
							sc.getUserInfo ().inc_score ();
					}	
				}
				else
				{
					progressInfo pi_broadcast = new progressInfo ();
					pi_broadcast.set_status (progressInfo.CHAT_GAME_UPDATE);
					pi_broadcast.set_chat (nickName);
					pi_broadcast.set_imagePath (chat);
					for (ServerClient sc: scList)
					{//System.out.println("CHAT");
						if (sc.getUserInfo ().get_gameName ().equals (gameName))
							sc.lockedWrite (pi_broadcast);
					}
				}
			}
		}
	}
	
	// INPUT: socket
	// OUTPUT: null
	// Objective: Close the connection and remove server client
	public void exitUser (Socket s)
	{
		for (ServerClient sc: scList)
		{
			if (sc.getSocket () == s)
			{
				try
				{
					//sc.destroy ();
					sc.exitClient ();
					scList.remove (sc);
					break;
				}
				catch (Exception e)
				{
					e.printStackTrace ();
				}
			}
		}		
	}
	
	// INPUT: null
	// OUTPUT: null
	// Objective: For all the clients, notify that 1 second elapsed
	public void timerBroadcast ()
	{
		progressInfo pi_broadcast = new progressInfo ();
		pi_broadcast.set_status (progressInfo.TIMER_BROADCAST);
		for (ServerClient sc: scList)
		{
			try
			{
				sc.lockedWrite (pi_broadcast);
				//System.out.println ("AFTER lockedWrite - timer broadcast: " + sc.getUserNickname ());
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	
	// Get method
	public String get_RLC () { return recentLobbyChat; }
	
	// Set method
	public void set_RLC (String item) { recentLobbyChat = item; }
}