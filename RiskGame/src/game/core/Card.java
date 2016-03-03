package game.core;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description 
 */
public class Card {

    private String _countryName;
    private CardType _cardType;
    private boolean _withPlayer;

    public Card(String countryName, CardType cardType) {
        this._countryName = countryName;
        this._cardType = cardType;
        _withPlayer = false;
    }

    public String getCountryName() {
        return _countryName;
    }

    public CardType getCardType() {
        return _cardType;
    }

    public boolean getWithPlayer() {
        return _withPlayer;
    }

    public void setWithPlayer(boolean isWithPlayer) {
        _withPlayer = isWithPlayer;
    }
}
