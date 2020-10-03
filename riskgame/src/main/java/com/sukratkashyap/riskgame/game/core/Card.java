package com.sukratkashyap.riskgame.game.core;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description class for storing the card and its related data
 */
public class Card {

    private final CardType _cardType;
    private boolean _withPlayer;
    private final CountryId _countryId;
    private final String _countryName;

    /**
     *
     * @param countryId id of the country
     * @param countryName
     * @param cardType type of the card
     */
    public Card(CountryId countryId, String countryName, CardType cardType) {
        this._countryId = countryId;
        this._cardType = cardType;
        this._countryName = countryName;
        _withPlayer = false;
    }

    /**
     *
     * @return get the country id
     */
    public CountryId getCountryId() {
        return _countryId;
    }

    /**
     *
     * @return
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

    private String getStringCardType() {
        String output = "";
        if (_cardType != null) {
            switch (_cardType) {
                case Artillery:
                    output = "A";
                    break;
                case Cavalry:
                    output = "C";
                    break;
                case Infantry:
                    output = "I";
                    break;
                default:
                    output = "W";
            }
        }
        return output;
    }

    @Override
    public String toString() {
        return getStringCardType();
    }
}
