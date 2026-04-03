package io.github.nicolascunha.sjl;

import java.util.Collection;

/**
 * SimpleChecks provides a collection of methods to perform basic checks on various data types, such as null checks,
 * range checks, and type checks. These methods can be used to validate input parameters, ensure data integrity,
 * and prevent common programming errors.
 *
 * <p>The class is designed to be easy to use and can be integrated into any Java project to
 * enhance code robustness and maintainability.</p>
 *
 * <p>Sample usages:</p>
 * {@snippet lang="java" :
 *     final SimpleChecks sc = new SimpleChecks();
 *     sc.isNotNull("Alice", "User name is required!");
 *     sc.isBlank("  ", "Display name cannot be blank!");
 * }
 *
 * {@snippet lang="java" :
 *     final int quantity = 3;
 *     final SimpleChecks sc = new SimpleChecks();
 *     sc.isPositive(quantity, "Quantity must be positive!");
 * }
 */
public class SimpleChecks {

    public SimpleChecks() {
        // noop
    }

    /**
     * Ensures that a given boolean expression. If the expression is valid, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final int userAge = 18;
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.check(userAge > 18, "User must be an adult!");
     * }
     *
     * @param expression {@link Boolean} expression to be checked.
     * @param message {@link String} exception message.
     */
    public void check(final Boolean expression, final String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Ensures that a given boolean expression is true. If it is false, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final boolean accountLocked = false;
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isTrue(accountLocked, "Account is locked!");
     * }
     *
     * @param expression {@link Boolean} expression to be checked.
     * @param message {@link String} exception message.
     */
    public void isTrue(final Boolean expression, final String message) {
        check(!expression, message);
    }

    /**
     * Ensures that a given boolean expression is false. If it is true, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final boolean hasPermission = true;
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isFalse(hasPermission, "Permission must not be granted!");
     * }
     *
     * @param expression {@link Boolean} expression to be checked.
     * @param message {@link String} exception message.
     */
    public void isFalse(final Boolean expression, final String message) {
        check(expression, message);
    }

    /**
     * Ensures that a given object is null. If it is, throws an instance of {@link IllegalArgumentException}
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
     */
    public void isNull(final Object obj, final String message) {
        check(obj == null, message);
    }

    /**
     * Ensures that a given object is not null. If it is not null, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final String sessionToken = "abc123";
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isNotNull(sessionToken, "Session token must be null!");
     * }
     *
     * @param obj {@link Object} instance to be checked.
     * @param message {@link String} exception message.
     */
    public void isNotNull(final Object obj, final String message) {
        check(obj != null, message);
    }

    /**
     * Ensures that a given string is empty. If it is empty or null, throws an instance of
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
     */
    public void isEmpty(final String str, final String message) {
        check(str == null || str.isEmpty(), message);
    }

    /**
     * Ensures that a given collection is empty. If it is null or empty, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final Collection<String> roles = new LinkedList();
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isEmpty(roles, "Roles cannot be empty!");
     * }
     *
     * @param collection {@link Collection} to be checked.
     * @param message {@link String} exception message.
     */
    public void isEmpty(final Collection<?> collection, final String message) {
        check(collection == null || collection.isEmpty(), message);
    }

    /**
     * Ensures that a given string is blank. If it is blank or null, throws an instance of
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
     */
    public void isBlank(final String str, final String message) {
        check(str == null || str.isBlank(), message);
    }

    /**
     * Ensures that a given number is positive. If it is zero or negative, throws an instance of
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
     */
    public void isPositive(final int number, final String message) {
        check(number <= 0, message);
    }

    /**
     * Ensures that a given number is negative. If it is zero or positive, throws an instance of
     * {@link IllegalArgumentException} with the given message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final int debt = 5;
     *     final SimpleChecks sc = new SimpleChecks();
     *     sc.isNegative(debt, "Debt value must be negative!");
     * }
     *
     * @param number {@code int} value to be checked.
     * @param message {@link String} exception message.
     */
    public void isNegative(final int number, final String message) {
        check(number >= 0, message);
    }

}
