package io.github.nicolascunha.sjl;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * SimpleChecks provides utility guard methods that validate values and throw
 * {@link IllegalArgumentException} when a condition is not satisfied.
 *
 * <p>Each method delegates to {@link #check(Boolean, String)}, so the exception is thrown
 * only when the evaluated condition is {@code false}.</p>
 *
 * <p>Sample usages:</p>
 * {@snippet lang="java" :
 *     final SimpleChecks sc = new SimpleChecks();
 *     sc.isNotNull("Alice", "User name is required!");
 *     sc.isBlank("   ", "Display name must be blank or null!");
 * }
 *
 * {@snippet lang="java" :
 *     final int quantity = 0;
 *     final SimpleChecks sc = new SimpleChecks();
 *     sc.isPositive(quantity, "Quantity must be non-negative!");
 * }
 */
public class SimpleChecks {

    public SimpleChecks() {
        // noop
    }

    /**
     * Ensures the truth of the given expression. Otherwise, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final int userAge = 21;
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.check(userAge > 18, "User must be an adult!");
     * }
     *
     * @param expression {@link Boolean} expression to be checked.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code expression} is {@code false}.
     */
    public void check(final Boolean expression, final String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Ensures the truth of a given boolean supplier. Otherwise, throws an
     * instance of {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.check(() -> 2 + 2 == 4, "Math is broken!");
     * }
     *
     * @param expression {@link Supplier} of {@link Boolean}.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code expression.get()} is {@code false}.
     */
    public void check(final Supplier<Boolean> expression, final String message) {
        check(expression.get(), message);
    }

    /**
     * Ensures that a given boolean expression is true. If it is false, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final boolean accountIsActive = true;
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isTrue(accountIsActive, "Account must be active!");
     * }
     *
     * @param expression {@link Boolean} expression to be checked.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code expression} is {@code false}.
     */
    public void isTrue(final Boolean expression, final String message) {
        check(expression, message);
    }

    /**
     * Ensures that a given boolean expression is false. If it is true, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final boolean hasPermission = false;
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isFalse(hasPermission, "Permission must not be granted!");
     * }
     *
     * @param expression {@link Boolean} expression to be checked.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code expression} is {@code true}.
     */
    public void isFalse(final Boolean expression, final String message) {
        check(!expression, message);
    }

    /**
     * Ensures that a given object is {@code null}. If it is not {@code null}, throws an instance of {@link IllegalArgumentException}
     * with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final String userName = null;
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isNull(userName, "User name cannot be null!");
     * }
     * @param obj {@link Object} instance to be checked.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code obj} is not {@code null}.
     */
    public void isNull(final Object obj, final String message) {
        check(obj == null, message);
    }

    /**
     * Ensures that a given object is not {@code null}. If it is {@code null}, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final String sessionToken = "abc123";
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isNotNull(sessionToken, "Session token is required!");
     * }
     *
     * @param obj {@link Object} instance to be checked.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code obj} is {@code null}.
     */
    public void isNotNull(final Object obj, final String message) {
        check(obj != null, message);
    }

    /**
     * Ensures that a given string is empty and not {@code null}. If it is {@code null} or not empty,
     * throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final String title = "";
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isEmpty(title, "Title cannot be empty!");
     * }
     *
     * @param str {@link String} instance to be checked.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code str} is {@code null} or not empty.
     */
    public void isEmpty(final String str, final String message) {
        check(str != null && str.isEmpty(), message);
    }

    /**
     * Ensures that a given collection is not {@code null} and empty. If it contains at least one element,
     * throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final Collection<String> roles = java.util.List.of();
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isEmpty(roles, "Roles must be empty!");
     * }
     *
     * @param collection {@link Collection} to be checked.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code collection} is not {@code null} and not empty.
     */
    public void isEmpty(final Collection<?> collection, final String message) {
        check(collection != null && collection.isEmpty(), message);
    }

    /**
     * Ensures that a given string is blank or {@code null}. If it is non-blank,
     * throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final String description = "   ";
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isBlank(description, "Description cannot be blank!");
     * }
     *
     * @param str {@link String} instance to be checked.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code str} is non-blank.
     */
    public void isBlank(final String str, final String message) {
        check(str == null || str.isBlank(), message);
    }

    /**
     * Ensures that a given number is non-negative. If it is negative, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final int amount = 0;
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isPositive(amount, "Amount must be positive!");
     * }
     *
     * @param number {@code int} value to be checked.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code number} is negative.
     */
    public void isPositive(final int number, final String message) {
        check(number >= 0, message);
    }

    /**
     * Ensures that a given number is non-positive. If it is positive, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final int debt = -5;
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isNegative(debt, "Debt value must be non-positive!");
     * }
     *
     * @param number {@code int} value to be checked.
     * @param message {@link String} exception message.
     * @throws IllegalArgumentException if {@code number} is positive.
     */
    public void isNegative(final int number, final String message) {
        check(number <= 0, message);
    }

}
