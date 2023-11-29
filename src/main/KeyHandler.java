package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GameLoop gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
    // DEBUG
    public boolean checkDrawTime = false;

    public KeyHandler(GameLoop gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); // Return un int avec le key associé

        // TITLE STATE
        if(gp.gameState == gp.titleState) {

            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }

            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }

            if(code == KeyEvent.VK_ENTER) {
                // New game
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(5);
                }
                // Load game
                if(gp.ui.commandNum == 1) {

                }
                // Exit
                if(gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        // PLAY STATE
        else if (gp.gameState == gp.playState){
            if (code == KeyEvent.VK_Z) {
                upPressed = true;
            }

            if (code == KeyEvent.VK_UP) {
                upPressed = true;
            }

            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }

            if (code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }

            if (code == KeyEvent.VK_Q) {
                leftPressed = true;
            }

            if (code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }

            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
        
            }

            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            if (code == KeyEvent.VK_R) {
                shotKeyPressed = true;
            }
            if (code == KeyEvent.VK_P ){
                gp.gameState = gp.characterState;
            }
            
            //debug 
            if (code == KeyEvent.VK_T) {
                if(checkDrawTime == false) {
                    checkDrawTime = true;
                }
                else if (checkDrawTime == true) {
                    checkDrawTime = false;
                }
            }
            if(code == KeyEvent.VK_Y) {
                switch(gp.currentMap) {
                case 0:
                    gp.tileM.loadMap("/maps/WorldMap.txt",0); break;
                case 1:
                    gp.tileM.loadMap("/maps/WorldMapLvl2.txt",1); break;
                }
                
            }
        }
        // pause state 
        else if (gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_ESCAPE) {
                    gp.gameState = gp.playState;
            
                }

        }
        // dialogue state 
        else if (gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }

        }

        //Game over
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }

        //character state 
        else if (gp.gameState == gp.characterState){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }   
    }

        

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode(); // Return un int avec le key associé

        if (code == KeyEvent.VK_Z) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_Q) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }

        if (code == KeyEvent.VK_R) {
            shotKeyPressed = false;
        }

        // DEBUG
        // if (code == KeyEvent.VK_T) {
        //     if(checkDrawTime == false) {
        //         checkDrawTime = true;
        //     }
        //     else if (checkDrawTime == true) {
        //         checkDrawTime = false;
        //     }
        // }

    }

    public void gameOverState(int code)  {
        if (code == KeyEvent.VK_Z) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        } else if (code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
        } else if (code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
        }


        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

    

    @Override
    public void keyTyped(KeyEvent e) {

    }
    
}
