package com.group4.app;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;

class AppWindow extends JFrame{

    /**
     * Class that sets the display and swaps in between different level_s, via <i>currentBoard</i>
     * and <i>setBoard</i>. It also plays the background music.
     */

    protected Board currentBoard;
    private Board gameOverBoard;
    private Board winBoard;
    private Clip music;
    private final ScoreTrackerView sTracker;
    private final TimeTrackerView tTracker;

    protected void changeBoard(Board target, int x, int y){
        if ( currentBoard.mEnemy != null )
            currentBoard. mEnemy.stopTimer();
        if ( target.mEnemy != null )
            target.mEnemy.startTimer();
        target.addPlayer(currentBoard.player);
        currentBoard.removePlayer();
        addBoard(target);
        remove(currentBoard);
        setBoard(target);
        target.player.setLocation(x,y);
        target.player.refresh();
        target.repaint();
        repaint();
    }

    public void setBoard(Board target){
        if ( currentBoard != null ) {
            currentBoard.stopTimer();
        }
        currentBoard = target;
        currentBoard.startTimer();
    }

    public void addBoard(Board target){
        add(target);
    }

    public void updateTotalScoreTracker(int i){
        sTracker.addTotalScore(i);
    }

    public void updateTotalRRewardsTracker(int i){
        sTracker.addTotalRRewards(i);
    }

    public ScoreTrackerView getsTracker() {
        return sTracker;
    }

    public TimeTrackerView gettTracker() {
        return tTracker;
    }

    public Board getGameOverBoard(){
        return gameOverBoard;
    }

    public Board getWinBoard() {
        return winBoard;
    }

    public void gameOver(){
        tTracker.stopTimer();
    }

    public void setGameOverBoard(Board gameOverBoard) {
        this.gameOverBoard = gameOverBoard;
    }

    public void setWinBoard(Board winBoard) {
        this.winBoard = winBoard;
    }

    public void startMusic(){
        music.start();
    }

    public AppWindow() {
        JPanel content = new JPanel();
        setContentPane(content);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1024, 880);
        setVisible(true);
        sTracker = new ScoreTrackerView();
        add(sTracker.getHolder());
        tTracker = new TimeTrackerView();
        add(tTracker.getHolder());
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/music.wav")));
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            music = (Clip) AudioSystem.getLine(info);
            music.open(audioStream);
        }   catch ( UnsupportedAudioFileException e ){ System.out.println("unsupported audio file for background music"); }
            catch ( IOException e ) { System.out.println("IOException"); }
            catch ( LineUnavailableException e ) { System.out.println("line unavailable"); }
        content.setDoubleBuffered(true);
    }

    public static void main(String[] args) {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, AppWindow.class.getResourceAsStream("/font.ttf")).deriveFont(128f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) { System.out.println("Could not find specified font"); }

        AppWindow window = new AppWindow();
        window.startMusic();

        character Player = new character();

        Board level_1 = new Board(window);
        level_1.addPlayer(Player);
        level_1.addMovingEnemy(6,4);
        level_1.mEnemy.startTimer();

        level_1.addSlotComponent("door","top");
        level_1.addSlotComponent("wall","bottom");
        level_1.addSlotComponent("door","right");
        level_1.addSlotComponent("wall","left");
        level_1.addBarrier(0,2);
        level_1.addBarrier(1,2);
        level_1.addBarrier(2,5);
        level_1.addBarrier(2,6);
        level_1.addBarrier(3,0);
        level_1.addBarrier(3,1);
        level_1.addBarrier(5,3);
        level_1.addBarrier(5,4);
        level_1.addBarrier(6,3);
        level_1.addBarrier(7,2);
        level_1.addBarrier(7,3);
        level_1.addBarrier(8,3);
        level_1.addBarrier(8,5);
        level_1.addBarrier(8,6);
        level_1.addBarrier(10,0);
        level_1.addBarrier(10,1);
        level_1.addBarrier(10,5);
        level_1.addBonus(11,0);
        level_1.addRegularRewards(3,3);
        level_1.addRegularRewards(10,3);
        level_1.addRegularRewards(6,0);
        level_1.addNonAnimatedEnemy(0,0);
        level_1.addNonAnimatedEnemy(8,4);

        Board level_2 = new Board(window);
        level_2.setDown(level_1);

        level_1.setUp(level_2);
        level_2.addMovingEnemy(7,2);

        level_2.addSlotComponent("wall","top");
        level_2.addSlotComponent("door","bottom");
        level_2.addSlotComponent("wall","right");
        level_2.addSlotComponent("wall","left");
        level_2.addBarrier(1,0);
        level_2.addBarrier(1,1);
        level_2.addBarrier(1,5);
        level_2.addBarrier(1,6);
        level_2.addBarrier(2,3);
        level_2.addBarrier(3,0);
        level_2.addBarrier(3,1);
        level_2.addBarrier(3,4);
        level_2.addBarrier(4,3);
        level_2.addBarrier(4,6);
        level_2.addBarrier(5,2);
        level_2.addBarrier(6,0);
        level_2.addBarrier(6,3);
        level_2.addBarrier(6,4);
        level_2.addBarrier(7,3);
        level_2.addBarrier(8,1);
        level_2.addBarrier(9,4);
        level_2.addBarrier(10,2);
        level_2.addBarrier(10,5);
        level_2.addBarrier(11,1);
        level_2.addBarrier(11,6);
        level_2.addRegularRewards(0,4);
        level_2.addRegularRewards(3,2);
        level_2.addRegularRewards(8,2);
        level_2.addRegularRewards(11,5);
        level_2.addNonAnimatedEnemy(1,4);
        level_2.addNonAnimatedEnemy(5,0);

        Board level_3 = new Board(window);
        level_3.setLeft(level_1);

        level_1.setRight(level_3);
        level_3.addMovingEnemy(8,4);

        level_3.addSlotComponent("wall","top");
        level_3.addSlotComponent("door","bottom");
        level_3.addSlotComponent("wall","right");
        level_3.addSlotComponent("door","left");
        level_3.addBarrier(0,5);
        level_3.addBarrier(1,1);
        level_3.addBarrier(1,4);
        level_3.addBarrier(2,3);
        level_3.addBarrier(3,1);
        level_3.addBarrier(4,3);
        level_3.addBarrier(4,4);
        level_3.addBarrier(4,5);
        level_3.addBarrier(5,1);
        level_3.addBarrier(5,5);
        level_3.addBarrier(6,3);
        level_3.addBarrier(6,4);
        level_3.addBarrier(6,5);
        level_3.addBarrier(7,1);
        level_3.addBarrier(8,1);
        level_3.addBarrier(8,2);
        level_3.addBarrier(8,3);
        level_3.addBarrier(8,5);
        level_3.addBarrier(8,6);
        level_3.addBarrier(9,5);
        level_3.addBarrier(10,1);
        level_3.addBarrier(10,2);
        level_3.addBarrier(10,3);
        level_3.addBarrier(10,5);
        level_3.addRegularRewards(1,5);
        level_3.addRegularRewards(2,4);
        level_3.addRegularRewards(5,4);
        level_3.addRegularRewards(5,6);
        level_3.addRegularRewards(11,3);
        level_3.addNonAnimatedEnemy(3,6);
        level_3.addNonAnimatedEnemy(4,2);
        level_3.addNonAnimatedEnemy(10,0);

        Board level_4 = new Board(window);
        level_4.setUp(level_3);

        level_3.setDown(level_4);
        level_4.addMovingEnemy(8,3);

        level_4.addSlotComponent("door","top");
        level_4.addSlotComponent("wall","bottom");
        level_4.addSlotComponent("wall","right");
        level_4.addSlotComponent("wall","left");
        level_4.addBarrier(0,3);
        level_4.addBarrier(1,5);
        level_4.addBarrier(2,1);
        level_4.addBarrier(2,3);
        level_4.addBarrier(2,5);
        level_4.addBarrier(2,6);
        level_4.addBarrier(3,5);
        level_4.addBarrier(4,0);
        level_4.addBarrier(4,3);
        level_4.addBarrier(4,4);
        level_4.addBarrier(5,2);
        level_4.addBarrier(5,3);
        level_4.addBarrier(5,6);
        level_4.addBarrier(6,5);
        level_4.addBarrier(7,1);
        level_4.addBarrier(7,3);
        level_4.addBarrier(7,5);
        level_4.addBarrier(9,0);
        level_4.addBarrier(9,1);
        level_4.addBarrier(9,2);
        level_4.addBarrier(9,5);
        level_4.addBarrier(10,2);
        level_4.addRegularRewards(0,1);
        level_4.addRegularRewards(0,6);
        level_4.addRegularRewards(6,6);
        level_4.addRegularRewards(8,6);
        level_4.addRegularRewards(10,0);
        level_4.addNonAnimatedEnemy(3,4);
        level_4.addNonAnimatedEnemy(7,2);
        level_4.addNonAnimatedEnemy(8,5);
        level_4.addNonAnimatedEnemy(10,5);

        //--------------------------------------GAME OVER BOARD--------------------------------------//
        Board level_END = new Board(window);
        level_END.removeAll();
        JLabel GameOver = new JLabel("GAME OVER", SwingConstants.CENTER);
        JPanel GOHolder;
        GOHolder = new JPanel();
        GameOver.setForeground(Color.white);
        GameOver.setFont(customFont);
        GameOver.setSize(1024,880);
        GOHolder.setSize(1024,880);
        GOHolder.setBackground(Color.BLACK);
        GOHolder.add(GameOver);
        level_END.add(GOHolder);
        level_END.repaint();
        window.setGameOverBoard(level_END);
        //----------------------------------------WIN BOARD----------------------------------------//
        Board level_WIN = new Board(window);
        level_WIN.removeAll();
        JLabel win = new JLabel("YOU WIN!", SwingConstants.CENTER);
        JPanel winHolder;
        winHolder = new JPanel();
        win.setForeground(Color.white);
        win.setFont(customFont);
        win.setSize(1024,880);
        winHolder.setSize(1024,880);
        winHolder.setBackground(Color.BLACK);
        winHolder.add(win);
        level_WIN.add(winHolder);
        level_WIN.repaint();
        window.setWinBoard(level_WIN);

        window.addBoard(level_1);
        window.repaint();
        window.setBoard(level_1);

    }
}