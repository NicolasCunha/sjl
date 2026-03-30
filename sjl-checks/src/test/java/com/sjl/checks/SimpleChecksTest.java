package com.sjl.checks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link SimpleChecks}.
 */
class SimpleChecksTest {

    @DisplayName("Assert check() throws IllegalArgumentException when expression is true")
    @Test
    void assertCheck() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checks.check(1 > 0, "Expression is true!");
            checks.check(1 < 0, "Expression is true!");
        });

        Assertions.assertDoesNotThrow(() -> {
            checks.check(1 < 0, "Expression is false!");
            checks.check(0 > 1, "Expression is false!");
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

    @DisplayName("Assert isNull() throws IllegalArgumentException when object is null.")
    @Test
    void assertIsNull() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String userName = null;
            checks.isNull(userName, "User name cannot be null!");
        });

        Assertions.assertDoesNotThrow(() -> {
            final String userName = "";
            checks.isNull(userName, "User name cannot be null!");
        });
    }

    @DisplayName("Assert isNotNull() throws IllegalArgumentException when object is not null.")
    @Test
    void assertIsNotNull() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           final String userName = "AnAlreadyExistingUsername";
           checks.isNotNull(userName, "Username is already present!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final String userName = null;
            checks.isNotNull(userName, "Username is already present!");
        });
    }

    @DisplayName("Assert isEmpty() throws IllegalArgumentException when String is empty.")
    @Test
    void assertIsEmpty() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String userName = null;
            checks.isEmpty(userName, "Username is null or empty!");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String userName = "";
            checks.isEmpty(userName, "Username is null or empty!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final String userName = "AGreatUsername";
            checks.isEmpty(userName, "Username is empty!");
        });
    }

    @DisplayName("Assert isBlank() throws IllegalArgumentException when String is blank.")
    @Test
    void assertIsBlank() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String nullUsername = null;
            checks.isBlank(nullUsername, "Username is null or blank!");
            final String emptyUsername = "";
            checks.isBlank(emptyUsername, "Username is null or blank!");
            final String blankUsername = "   ";
            checks.isBlank(blankUsername, "Username is null or blank!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final String userName = "AmazingUsername";
            checks.isBlank(userName, "Username is null blank!");
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

    @DisplayName("Assert isEmpty() throws IllegalArgumentException when collection is null or empty.")
    @Test
    void assertCollectionIsEmpty() {
        final SimpleChecks checks = new SimpleChecks();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final List<String> nullList = null;
            checks.isEmpty(nullList, "Collection cannot be null or empty!");
            final List<String> emptyList = java.util.Collections.emptyList();
            checks.isEmpty(emptyList, "Collection cannot be null or empty!");
        });
        Assertions.assertDoesNotThrow(() -> {
            final List<String> listWithValues = new LinkedList<>();
            listWithValues.add("A nice value.");
            checks.isEmpty(listWithValues, "Collection cannot be null or empty!");
        });
    }

}