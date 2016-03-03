package game.data;

import game.core.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description 
 */
public class Validations {

    public static class RequiredFieldValidation implements IValidationInput {

        @Override
        public Result isValid(String input) {
            Result result;
            if (input == null || input.trim().length() == 0) {
                result = new Result(false, "String entered cannot be empty!");
            } else {
                result = new Result(true, "");
            }
            return result;
        }
    }

    public static class RequiredAndNotBeEqualTo implements IValidationInput {

        private List<String> _notEqualTo = new ArrayList<>();

        public RequiredAndNotBeEqualTo(String notEqualTo) {
            _notEqualTo.add(notEqualTo);
        }

        public RequiredAndNotBeEqualTo(List<String> notEqualTo) {
            _notEqualTo.addAll(notEqualTo);
        }

        public RequiredAndNotBeEqualTo(Collection<String> notEqualTo) {
            _notEqualTo.addAll(notEqualTo);
        }

        @Override
        public Result isValid(String input) {
            Result result;
            if (input == null || input.trim().length() == 0) {
                result = new Result(false, "String entered cannot be empty!");
            } else if (_notEqualTo.stream().filter((i) -> i.equals(input)).count() > 0) {
                result = new Result(false, "String must not be equal to " + input);
            } else {
                result = new Result(true, "");
            }
            return result;
        }

    }

    public static class RequiredAndEqualTo implements IValidationInput {

        private List<String> _equalTo = new ArrayList<>();

        public RequiredAndEqualTo(String equalTo) {
            _equalTo.add(equalTo);
        }

        public RequiredAndEqualTo(List<String> equalTo) {
            _equalTo.addAll(equalTo);
        }

        public RequiredAndEqualTo(Collection<String> equalTo) {
            _equalTo.addAll(equalTo);
        }

        @Override
        public Result isValid(String input) {
            Result result;
            if (input == null || input.trim().length() == 0) {
                result = new Result(false, "String entered cannot be empty!");
            } else if (_equalTo.stream().filter((i) -> i.equals(input)).count() == 0) {
                result = new Result(false, "String must be equal to " + _equalTo);
            } else {
                result = new Result(true, "");
            }
            return result;
        }

    }
}
