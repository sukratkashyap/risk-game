package com.sukratkashyap.riskgame.game.data;

import game.core.IValidationInput;
import game.core.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description Class to validate the input from the user.
 */
public class Validations {

    public static class RequiredField implements IValidationInput {

        @Override
        public Result isValid(String input) {
            Result result;
            if (input == null || input.length() == 0) {
                result = new Result(false, "String entered cannot be empty!");
            } else {
                result = new Result(true, "");
            }
            return result;
        }
    }

    /**
     * Class to validate the input from the user NOT equal to the input string.
     */
    public static class RequiredAndNotEqualTo implements IValidationInput {

        private List<String> _notEqualTo = new ArrayList<>();

        public RequiredAndNotEqualTo(String notEqualTo) {
            _notEqualTo.add(notEqualTo);
        }

        public RequiredAndNotEqualTo(List<String> notEqualTo) {
            _notEqualTo.addAll(notEqualTo);
        }

        public RequiredAndNotEqualTo(Collection<String> notEqualTo) {
            _notEqualTo.addAll(notEqualTo);
        }

        @Override
        public Result isValid(String input) {
            Result result;
            if (input == null || input.length() == 0) {
                result = new Result(false, "String entered cannot be empty!");
            } else if (_notEqualTo.stream().filter((i) -> i.equalsIgnoreCase(input)).count() > 0) {
                result = new Result(false, "String must not be equal to " + input);
            } else {
                result = new Result(true, "");
            }
            return result;
        }

    }

    /**
     * Class to validate the input from the user equal to the input string.
     */
    public static class RequiredAndEqualTo implements IValidationInput {

        private List<String> _equalTo = new ArrayList<>();

        public RequiredAndEqualTo(String... equalTo) {
            _equalTo.addAll(Arrays.asList(equalTo));
        }

        public RequiredAndEqualTo(List<String> equalTo) {
            _equalTo.addAll(equalTo);
        }

        @Override
        public Result isValid(String input) {
            Result result;
            if (input == null || input.length() == 0) {
                result = new Result(false, "String entered cannot be empty!");
            } else if (_equalTo.stream().filter((i) -> i.equalsIgnoreCase(input)).count() == 0) {
                result = new Result(false, "String must be equal to " + _equalTo);
            } else {
                result = new Result(true, "");
            }
            return result;
        }

    }

    public static class RequiredNumberField implements IValidationInput {

        @Override
        public Result isValid(String input) {
            Result result;
            if (input == null || input.length() == 0) {
                result = new Result(false, "Integer entered cannot be empty!");
            } else {
                try {
                    Integer.parseInt(input);
                    result = new Result(true, "");
                } catch (Exception e) {
                    result = new Result(false, "Input must be of type integer!");
                }
            }
            return result;
        }
    }

    public static class RequiredNumberFieldAndEqualTo implements IValidationInput {

        private int from;
        private int to;

        public RequiredNumberFieldAndEqualTo(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Result isValid(String input) {
            Result result;
            if (input == null || input.length() == 0) {
                result = new Result(false, "Integer entered cannot be empty!");
            } else {
                try {
                    int i = Integer.parseInt(input);
                    if (i >= from && i <= to) {
                        result = new Result(true, "");
                    } else {
                        result = new Result(false, "Number should be between " + from + " and " + to + " (inclusive)!");
                    }
                } catch (Exception e) {
                    result = new Result(false, "Input must be of type integer!");
                }
            }
            return result;
        }
    }
}
