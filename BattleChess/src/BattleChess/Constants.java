package BattleChess;

import javax.swing.JPanel;

interface Constants {
    int[] windowSize = {816,638};
    int[] screenSize = {800,600};

    String imageFolder = "images/";

    String titleBG = "bg_title.jpg";
    String boardBG = "bg_board.jpg";
    String registrationBG = "bg_registration.jpg";
    String loginBG = "bg_login.jpg";
    String creditsBG = "bg_credits.jpg";
    String rankBG = "bg_rank.jpg";

    String loginButton = "Login";
    String registerButton = "Register";
    String skipButton = "Skip";
    String creditsButton = "Credits";
    String rankButton = "Rankings";
    String backButton = "<";
    
    String pieces[][] = new String[8][8];
        
    int hi = -1;
    int hx = -1;
    int hy = -1;
}
