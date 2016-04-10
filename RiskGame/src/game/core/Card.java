package game.core;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description class for storing the card and its related data
 */
public class Card {

    private String _countryName;
    private CardType _cardType;
    private boolean _withPlayer;
    private CountryIndex _countryIndex;

    /**
     *
     * @param countryName name of the country
     * @param cardType type of the card
     */
    public Card(String countryName, CardType cardType) {
        this._countryName = countryName;
        this._cardType = cardType;
        _withPlayer = false;
    }

    /**
     *
     * @return get the country name
     */
    public String getCountryName() {
        return _countryName;
    }

    /**
     *
     * @return gets the type of the card
     */
    public CardType getCardType() {
        return _cardType;
    }

    /**
     *
     * @return whether the card is with a player or not
     */
    public boolean isWithPlayer() {
        return _withPlayer;
    }

    /**
     *
     * @param isWithPlayer true if with the player else false
     */
    public void setWithPlayer(boolean isWithPlayer) {
        _withPlayer = isWithPlayer;
    }

    @Override
    public String toString() {
        return _countryName + " (" + (_cardType != CardType.Wild ? "T" : "W") + ")";
    }

}
