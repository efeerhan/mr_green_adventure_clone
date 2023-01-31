package com.group4.app;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

class Board extends JLayeredPane implements ActionListener {

    /**
     * Board that holds all the elements of the game. Each Board has its own layout on a 12x7 grid
     * that determines the locations of all the objects on screen, except for the player since it can
     * be between grid positions. Moving Enemy is included since it needs a starting point, so that on
     * initialization we don't crowd the same spot. Each Board has its own timer to check collisions between
     * its elements and the player/Moving Enemy, as well as to remove its bonus and to move the Moving Enemy
     * around. Boards are also responsible for playing audio from collision events.
     */

    protected Board up, down, left, right;
    protected BufferedImage BaseBoard;
    protected AppWindow window;
    protected int bonusTicker = 0;
    protected int bonusX, bonusY;
    protected character player;
    protected  MovingEnemy mEnemy;
    protected int contact = 0;
    protected final baseElement[][] grid = new baseElement[12][7];
    protected Clip rewardClip;
    protected Clip enemyClip;
    protected AudioInputStream rewardStream;
    protected AudioInputStream enemyStream;
    protected final soundPlayer tempSound;
    /*
     * INDICES:
     * [0] = TOP SLOT
     * [1] = BOTTOM SLOT
     * [2] = RIGHT SLOT
     * [3] = LEFT SLOT
     * */
    protected final ArrayList<slotComponent> slots = new ArrayList<>(4);
    protected final Timer t;

    protected final collisionBox[] baseCollisionBoxes = new collisionBox[8];

    public void setWallBounds(){
        baseCollisionBoxes[0] = new collisionBox(577,177,320,128);  //right top hor
        baseCollisionBoxes[1] = new collisionBox(902,305,128,160);  //right top vert
        baseCollisionBoxes[2] = new collisionBox(577,753,320,128);  //right bottom hor
        baseCollisionBoxes[3] = new collisionBox(896,592,128,160);  //right bottom vert
        baseCollisionBoxes[4] = new collisionBox(129,177,320,128);  //left top hor
        baseCollisionBoxes[5] = new collisionBox(1,305,128,160);    //left top vert
        baseCollisionBoxes[6] = new collisionBox(129,753,320,128);  //left bottom hor
        baseCollisionBoxes[7] = new collisionBox(1,593,128,160);    //left bottom vert
    }

    public Board(AppWindow setWindow){
        window = setWindow;
        try {
            BaseBoard = ImageIO.read((getClass().getResourceAsStream("/BaseBoardFloor.png")));
        } catch(IOException e){ System.out.println("Walls/floor Sprite source not found"); }
        setSize(1024,880);
        setWallBounds();
        setDoubleBuffered(true);
        setOpaque(false);
        setFocusable(false);
        t = new Timer(1, this);
        try {
            rewardStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/rupee.wav")));
            enemyStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/enemy.wav")));

            AudioFormat rewardFormat = rewardStream.getFormat();
            AudioFormat enemyFormat = enemyStream.getFormat();

            DataLine.Info rewardInfo = new DataLine.Info(Clip.class, rewardFormat);
            DataLine.Info enemyInfo = new DataLine.Info(Clip.class, enemyFormat);

            rewardClip = (Clip)AudioSystem.getLine(rewardInfo);
            enemyClip = (Clip)AudioSystem.getLine(enemyInfo);
        }   catch ( UnsupportedAudioFileException e ) { System.out.println("unsupported audio file"); }
            catch ( IOException e ) { System.out.println("IOException"); }
            catch ( LineUnavailableException e ) { System.out.println("line unavailable"); }
        tempSound = new soundPlayer();
    }

    public void setUp(Board up){
        this.up = up;
    }
    public void setDown(Board down){
        this.down = down;
    }
    public void setRight(Board right){
        this.right = right;
    }
    public void setLeft(Board left){
        this.left = left;
    }

    public void addPlayer(character target){
        add(target,0);
        player = target;
    }

    public void stopTimer(){
        t.stop();
    }

    public void startTimer(){
        t.start();
    }

    public void addSlotComponent(String comp, String pos){
        if ( slots.size() < 4 ) {
            slotComponent component = new slotComponent(comp, pos);
            add(component, 2);
            slots.add(component);
            repaint();
        }
    }

    public boolean checkGridSpot(int x, int y){
        return grid[x][y] == null;
    }

    public void addBarrier(int x, int y){
        if ( checkGridSpot(x,y) ) {
            grid[x][y] = new Barrier((x * 64) + 128, (y * 64) + 304);
            add(grid[x][y]);
            grid[x][y].repaint();
        }
        else System.out.println("Grid spot already occupied");
    }

    public void addRegularRewards(int x, int y){
        if ( checkGridSpot(x,y) ) {
            grid[x][y] = new RegularRewards((x * 64) + 128, (y * 64) + 304);
            add(grid[x][y]);
            grid[x][y].repaint();
        }
        else System.out.println("Grid spot already occupied");
    }

    public void addBonus(int x, int y) {
        if ( checkGridSpot(x,y) ) {
            grid[x][y] = new Bonus((x * 64) + 128, (y * 64) + 304);
            add(grid[x][y]);
            grid[x][y].repaint();
            bonusX = x;
            bonusY = y;
        }
        else System.out.println("Grid spot already occupied");
    }

    public void addNonAnimatedEnemy(int x, int y){
        if ( checkGridSpot(x,y) ) {
            grid[x][y] = new NonAnimatedEnemy((x*64)+128,(y*64)+304);
            add(grid[x][y]);
            grid[x][y].repaint();
        }
        else System.out.println("Grid spot already occupied");
    }

    public void addMovingEnemy(int x, int y){
        if ( checkGridSpot(x,y) ) {
            grid[x][y] = new MovingEnemy((x*64)+128,(y*64)+304);
            mEnemy = (MovingEnemy)grid[x][y];
            add(grid[x][y]);
            grid[x][y].repaint();
        }
        else System.out.println("Grid spot already occupied");
    }

    protected void removePlayer(){
        remove(player);
        player = null;
    }

    private class soundPlayer{
        soundPlayer(){
            try {
                rewardClip.open(rewardStream);
                enemyClip.open(enemyStream);
            } catch (LineUnavailableException | IOException e) { e.printStackTrace(); }
        }
        void play(String identifier){
            if ( identifier.equals("NAE") ){
                if ( enemyClip.isActive() )
                    enemyClip.stop();
                enemyClip.setFramePosition(0);
                enemyClip.start();
            }
            if ( identifier.equals("RR") ) {
                rewardClip.setFramePosition(0);
                if ( rewardClip.isActive() )
                    rewardClip.stop();
                rewardClip.start();
            }
        }
    }

    protected void collectPoint(int i, int j, String identifier){
        if ( identifier.equals("RR") ) {
            int score = ((RegularRewards)grid[i][j]).getRRewardsScore();
            window.updateTotalScoreTracker(score);
            window.updateTotalRRewardsTracker(score);
            remove(grid[i][j]);
            grid[i][j] = null;
            tempSound.play("RR");
        }
        if ( identifier.equals("BR") ) {
            int score = ((Bonus)grid[i][j]).getBonusScore();
            window.updateTotalScoreTracker(score);
            remove(grid[i][j]);
            grid[i][j] = null;
            tempSound.play("RR");
        }
        if ( identifier.equals("NAE") ){
            window.updateTotalScoreTracker(-1);
            remove(grid[i][j]);
            grid[i][j] = null;
            tempSound.play("NAE");
        }
        if ( identifier.equals("ME") ){
            if ( ++contact == 100 ){
                tempSound.play("NAA");
                window.changeBoard(window.getGameOverBoard(),-1000000,-10000000);
                window.gameOver();
            }
        }
        repaint();
    }

    private void removeBonus(){
        if ( grid[bonusX][bonusY] != null ) {
            remove(grid[bonusX][bonusY]);
            grid[bonusX][bonusY] = null;
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BaseBoard, 0, 0, this);
    }

    protected int checkMEnemyCollision(){
        collisionBox enemyProjectedBox = new collisionBox(mEnemy.getX()+16,mEnemy.getY()+16,32,32);
        int enemyX = (int)enemyProjectedBox.getX();
        int enemyY = (int)enemyProjectedBox.getY();

        if (mEnemy.getDirection() == 180)
            enemyProjectedBox.setLocation(enemyX - 10, enemyY);
        if (mEnemy.getDirection() == 0)
            enemyProjectedBox.setLocation(enemyX + 10, enemyY);
        if (mEnemy.getDirection() == 270)
            enemyProjectedBox.setLocation(enemyX, enemyY + 10);
        if (mEnemy.getDirection() == 90)
            enemyProjectedBox.setLocation(enemyX, enemyY - 10);

        for ( int i = 0; i<8; i++ ) {
            if (enemyProjectedBox.intersects(baseCollisionBoxes[i])) {
                return i;
            }
        }
        for (int i = 0; i < 4; i++)
            if ( slots.size() > 0 && enemyProjectedBox.intersects(slots.get(i).getCollision()) )
                return (i + 1) * 10;
        return -1;
    }

    protected int checkPlayerCollision(){
        /*What this does is return an integer value that's unique to the type of
         * check that is being performed, to be used in ActionPerformed */
        Collectible test1;
        collisionBox playerProjectedBox = new collisionBox(player.getX(),player.getY(),64,64);

        int playerX = (int)playerProjectedBox.getX();
        int playerY = (int)playerProjectedBox.getY();

        if (player.getDirection() == 180)
            playerProjectedBox.setLocation(playerX - 10, playerY);
        if (player.getDirection() == 0)
            playerProjectedBox.setLocation(playerX + 10, playerY);
        if (player.getDirection() == 270 || player.getDirection() == -1)
            playerProjectedBox.setLocation(playerX, playerY + 10);
        if (player.getDirection() == 90)
            playerProjectedBox.setLocation(playerX, playerY - 10);

        for ( int i = 0; i<8; i++ ) {
            if (playerProjectedBox.intersects(baseCollisionBoxes[i])) {
                return i;
            }
        }
        for (int i = 0; i < 4; i++) {
            if ( slots.size() > 0 ) {
                if (playerProjectedBox.intersects(slots.get(i).getCollision())) {
                    if (slots.get(i).getType().equals("door")) {
                        t.stop();
                        if (slots.get(i).getPosition().equals("top"))
                            window.changeBoard(up, 490, 700);
                        if (slots.get(i).getPosition().equals("bottom"))
                            window.changeBoard(down, 490, 285);
                        if (slots.get(i).getPosition().equals("right"))
                            window.changeBoard(right, 100, 495);
                        if (slots.get(i).getPosition().equals("left"))
                            window.changeBoard(left, 855, 495);
                    } else
                        return (i + 1) * 10;
                }
            }
        }
        for ( int i = 0; i < 12; i++ ){
            for ( int j = 0; j < 7; j++ ){
                if ( grid[i][j] != null ) {
                    if (playerProjectedBox.intersects(grid[i][j].getCollision()))
                        try {
                            test1 = (Collectible)grid[i][j];
                            collectPoint(i,j,test1.getIdentifier());
                        }catch(ClassCastException e1){ return player.getDirection() + 15; }
                }
            }
        }
        return -1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( window.getsTracker().getTotalRRewards() == 17 ){
            window.changeBoard(window.getWinBoard(),-1000000,-10000000);
            window.gameOver();
        }
        if ( window.getsTracker().getTotalScore() < 0 ){
            window.changeBoard(window.getGameOverBoard(),-1000000,-10000000);
            window.gameOver();
        }
        bonusTicker++;
        if ( bonusTicker >= 15000 ){
            removeBonus();
        }
        if ( player != null ) {
            int colDir = checkPlayerCollision();
            if (colDir != -1) {
                if (colDir == 0) {
                    if (player.getY() >= 304)
                        player.setNoMove(90);
                    else
                        player.setNoMove(0);
                }
                if (colDir == 4) {
                    if (player.getY() >= 304)
                        player.setNoMove(90);
                    else
                        player.setNoMove(180);
                }
                if (colDir == 2) {
                    if (player.getY() >= 699) {
                        player.setNoMove(0);
                    }
                    else {
                        player.setNoMove(270);
                    }
                }
                if (colDir == 6) {
                    if (player.getY() >= 699)
                        player.setNoMove(180);
                    else
                        player.setNoMove(270);
                }
                if (colDir == 5) {
                    if (player.getX() <= 128)
                        player.setNoMove(90);
                    else
                        player.setNoMove(180);
                }
                if (colDir == 7) {
                    if (player.getX() <= 128)
                        player.setNoMove(270);
                    else
                        player.setNoMove(180);
                }
                if (colDir == 1) {
                    if (player.getX() >= 850) {
                        player.setNoMove(90);
                    }
                    else {
                        player.setNoMove(0);
                    }
                }
                if (colDir == 3) {
                    if (player.getX() >= 850)
                        player.setNoMove(270);
                    else {
                        player.setNoMove(0);
                    }
                }
                if ( colDir == 10 || colDir == 105 ) {
                    player.setNoMove(90);
                }
                if ( colDir == 20 || colDir == 285) {
                    player.setNoMove(270);
                }
                if ( colDir == 30 || colDir == 15) {
                    player.setNoMove(0);
                }
                if ( colDir == 40 || colDir == 195 ){
                    player.setNoMove(180);
                }
            } else
                if ( player != null ) player.setNoMove(-1);
        }
        if ( mEnemy != null ) {
            int colDir = checkMEnemyCollision();
            if (colDir != -1) {
                if (colDir == 0) {
                    if (mEnemy.getY() >= 304)
                        mEnemy.setNoMove(90);
                    else
                        mEnemy.setNoMove(0);
                }
                if (colDir == 4) {
                    if (mEnemy.getY() >= 304)
                        mEnemy.setNoMove(90);
                    else
                        mEnemy.setNoMove(180);
                }
                if (colDir == 2) {
                    if (mEnemy.getY() >= 699) {
                        mEnemy.setNoMove(0);
                    }
                    else {
                        mEnemy.setNoMove(270);
                    }
                }
                if (colDir == 6) {
                    if (mEnemy.getY() >= 699)
                        mEnemy.setNoMove(180);
                    else
                        mEnemy.setNoMove(270);
                }
                if (colDir == 5) {
                    if (mEnemy.getX() <= 128)
                        mEnemy.setNoMove(90);
                    else
                        mEnemy.setNoMove(180);
                }
                if (colDir == 7) {
                    if (mEnemy.getX() <= 128)
                        mEnemy.setNoMove(270);
                    else
                        mEnemy.setNoMove(180);
                }
                if (colDir == 1) {
                    if (mEnemy.getX() >= 850) {
                        mEnemy.setNoMove(90);
                    }
                    else {
                        mEnemy.setNoMove(0);
                    }
                }
                if (colDir == 3) {
                    if (mEnemy.getX() >= 850)
                        mEnemy.setNoMove(270);
                    else {
                        mEnemy.setNoMove(0);
                    }
                }
                if ( colDir == 10 || colDir == 105 ) {
                    mEnemy.setNoMove(90);
                }
                if ( colDir == 20 || colDir == 285) {
                    mEnemy.setNoMove(270);
                }
                if ( colDir == 30 || colDir == 15) {
                    mEnemy.setNoMove(0);
                }
                if ( colDir == 40 || colDir == 195 ){
                    mEnemy.setNoMove(180);
                }
            } else
                mEnemy.setNoMove(-1);
        }
    }
}
