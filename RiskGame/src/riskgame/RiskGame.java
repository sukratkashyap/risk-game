package riskgame;

import game.graphic.GUI;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description
 */
public class RiskGame {

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new RiskGame();
//        });
        RiskGame riskGame = new RiskGame();
    }

    protected RiskGame() {
        GamePlay gamePlay = new GamePlay(new GUI());
        gamePlay.getPlayerNameFromUser();
        gamePlay.assignTerritoryCard();
        gamePlay.rollDiceAndSetInforcements();
    }
}
