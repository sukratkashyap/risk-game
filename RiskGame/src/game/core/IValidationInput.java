package game.core;

import game.data.*;
import game.core.Result;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description interface for validating the inputs
 */
public interface IValidationInput {
    Result isValid(String input);
}
