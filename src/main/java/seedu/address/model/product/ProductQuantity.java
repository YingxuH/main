package seedu.address.model.product;

import seedu.address.model.util.Quantity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ProductQuantity implements Quantity {

    public static final int MIN_VALUE = 0;

    public static final String MESSAGE_CONSTRAINTS_VALUE = "The numeric value of Product Quantity must be "
            + "larger than or equal to " + MIN_VALUE
            + "and smaller than " + MAX_VALUE;

    public final int value;

    /**
     * Constructs an {@code Quantity}.
     *
     * @param quantity A valid quantity in string type.
     */
    public ProductQuantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidFormat(quantity), MESSAGE_CONSTRAINTS_FORMAT);
        int numericValue = Integer.parseInt(quantity);
        checkArgument(isValidValue(numericValue), MESSAGE_CONSTRAINTS_VALUE);
        value = numericValue;
    }

    public ProductQuantity(int q) {
        requireNonNull(q);
        checkArgument(isValidValue(q), MESSAGE_CONSTRAINTS_VALUE);
        value = q;
    }

    public int getValue() {
        return value;
    }

    public static boolean isValidFormat(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            try {
                int value = Integer.parseInt(test);
                return value <= MAX_VALUE;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean isValidValue(int test) {
        return test >= MIN_VALUE && test <= MAX_VALUE;
    }

    /**
     * Returns a new quantity whose value is the difference between this value and the
     * other's value.
     * @param q other quantity.
     * @return new quantity.
     */
    public Quantity minus(Quantity q) {
        requireNonNull(q);
        int newValue = value - q.getValue();
        return new ProductQuantity(newValue);
    }

    /**
     * Returns a new quantity whose value is the summation between this value and the
     * other's value.
     * @param q other quantity.
     * @return new quantity.
     */
    public Quantity plus(Quantity q) {
        requireNonNull(q);
        int newValue = value + q.getValue();
        return new ProductQuantity(newValue);
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && value == ((Quantity) other).getValue()); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public int compareTo(Quantity q) {
        return value - q.getValue();
    }


}