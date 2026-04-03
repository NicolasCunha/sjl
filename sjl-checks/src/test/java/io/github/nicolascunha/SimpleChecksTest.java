package io.github.nicolascunha;

import io.github.nicolascunha.sjl.SimpleChecks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Tests for {@link SimpleChecks}.
 */
class SimpleChecksTest {

    @DisplayName("Assert check() throws IllegalArgumentException when expression is false.")
    @Test
    void assertCheck() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checks.check(1 < 0, "One is always greater than zero!");
            checks.check(0 > 1, "Zero is always lower than one!");
        });

        Assertions.assertDoesNotThrow(() -> {
            checks.check(0 < 1, "One is always greater than zero!");
            checks.check(1 > 0, "Zero is always lower than one!");
        });
    }

    @DisplayName("Assert check() with Boolean supplier throws IllegalArgumentException when expression is false.")
    @Test
    void assertCheckWithSupplier() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checks.check(() -> 1 < 0, "One is always greater than zero!");
            checks.check(() -> 0 > 1, "Zero is always lower than one!");
        });
        Assertions.assertDoesNotThrow(() -> {
            checks.check(() -> 0 < 1, "One is always greater than zero!");
            checks.check(() -> 1 > 0, "Zero is always lower than one!");
        });
    }

    @DisplayName("Assert isTrue() throws IllegalArgumentException when expression is true")
    @Test
    void assertIsTrue() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final boolean adminPermission = false;
            checks.isTrue(adminPermission, "User has no admin permission!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final boolean adminPermission = true;
            checks.isTrue(adminPermission, "User has no admin permission!");
        });
    }

    @DisplayName("Assert isFalse() throws IllegalArgumentException when expression is false.")
    @Test
    void assertIsFalse() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final boolean userLocked = true;
            checks.isFalse(userLocked, "Locked user cant perform this action!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final boolean userLocked = false;
            checks.isFalse(userLocked, "Locked user cant perform this action!");
        });
    }

    @DisplayName("Assert isNull() throws IllegalArgumentException when object is not null.")
    @Test
    void assertIsNull() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String errorMessage = "null";
            checks.isNull(errorMessage, "Error message must be null!");
        });

        Assertions.assertDoesNotThrow(() -> {
            final String errorMessage = null;
            checks.isNull(errorMessage, "Error message must be null!");
        });
    }

    @DisplayName("Assert isNotNull() throws IllegalArgumentException when object is null.")
    @Test
    void assertIsNotNull() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           final String userName = null;
           checks.isNotNull(userName, "Username cannot be null!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final String userName = "A good username";
            checks.isNotNull(userName, "Username cannot be null!");
        });
    }

    @DisplayName("Assert isEmpty() throws IllegalArgumentException when String is not empty.")
    @Test
    void assertIsEmpty() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String errorMessage = null;
            checks.isEmpty(errorMessage, "Error message is null or has value!");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String errorMessage = "An error occurred!";
            checks.isEmpty(errorMessage, "Error message is null or has value!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final String errorMessage = "";
            checks.isEmpty(errorMessage, "Error message is null or has value!!");
        });
    }

    @DisplayName("Assert isBlank() throws IllegalArgumentException when String is not blank.")
    @Test
    void assertIsBlank() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String nullErrorMessage = null;
            checks.isBlank(nullErrorMessage, "Error message is null or has value!");
            final String errorMessageWithContent =  "An error occurred!";
            checks.isBlank(errorMessageWithContent, "Error message is null or has value!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final String emptyErrorMessage = "";
            checks.isBlank(emptyErrorMessage, "Error message is null or has value!");
            final String blankErrorMessage = "     ";
            checks.isBlank(blankErrorMessage, "Error message is null or has value!");
        });
    }

    @DisplayName("Assert isPositive() throws IllegalArgumentException when number is zero or negative.")
    @Test
    void assertIsPositive() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final int zeroValue = 0;
            checks.isPositive(zeroValue, "Value must be positive!");
            final int negativeValue = -1;
            checks.isPositive(negativeValue, "Value must be positive!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final int positiveValue = 1;
            checks.isPositive(positiveValue, "Value must be positive!");
        });
    }

    @DisplayName("Assert isNegative() throws IllegalArgumentException when number is zero or positive.")
    @Test
    void assertIsNegative() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final int zeroValue = 0;
            checks.isNegative(zeroValue, "Value must be negative!");
            final int positiveValue = 1;
            checks.isNegative(positiveValue, "Value must be negative!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final int negativeValue = -1;
            checks.isNegative(negativeValue, "Value must be negative!");
        });
    }

    @DisplayName("Assert isEmpty() throws IllegalArgumentException when collection is null or not empty.")
    @Test
    void assertCollectionIsEmpty() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final List<String> nullList = null;
            checks.isEmpty(nullList, "Collection must be empty!");
            final List<String> listWithValues = new LinkedList<>();
            listWithValues.add("A nice value.");
            checks.isEmpty(listWithValues, "Collection must be empty!");
        });
        Assertions.assertDoesNotThrow(() -> {
            checks.isEmpty(Collections.emptyList(), "Collection must be empty!");
        });
    }

}