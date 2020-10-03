package com.sukratkashyap.riskgame;

import game.core.Player;
import game.graphic.GUI;
import java.util.List;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description
 */
public class RiskGame implements Runnable {

    public static void main(String[] args) {
        new Thread(new RiskGame()).start();
    }

    protected GUI _gui;
    private GamePlay _gamePlay;

    protected RiskGame() {
        _gui = new GUI();
        _gamePlay = new GamePlay(_gui);
    }

    @Override
    public void run() {
        _gamePlay.getPlayerNameFromUser();
        _gamePlay.assignTerritoryCard();
        _gamePlay.rollDice();
        _gamePlay.setInforcements();
        List<Player> orderedPlayerList = _gamePlay.rollDice();
        boolean isGameOver = false;
        while (!isGameOver) {
            for (Player player : orderedPlayerList) {
                _gamePlay.giveReInforcements(player);
                _gamePlay.exchangeCards(player);
                _gamePlay.setInforcements(player);
                boolean didPlayerOccupyTerritory = _gamePlay.attackOrNot(player);
                isGameOver = _gamePlay.isGameOver();
                if (isGameOver) {
                    break;
                }
                _gamePlay.fortifyOrNot(player);
                if (didPlayerOccupyTerritory) {
                    _gamePlay.drawCardFromDeck(player);
                }
            }
        }
        _gamePlay.printWinner();
        _gamePlay.close();
    }
}
