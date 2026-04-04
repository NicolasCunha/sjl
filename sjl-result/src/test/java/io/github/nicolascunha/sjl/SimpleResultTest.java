package io.github.nicolascunha.sjl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Tests for {@link SimpleResult}.
 */
class SimpleResultTest {

    @DisplayName("Assert success() creates successful instances.")
    @Test
    void assertSuccess() {
        Assertions.assertEquals(Status.SUCCESS, SimpleResult.success().getStatus());
        Assertions.assertEquals(Status.SUCCESS, SimpleResult.success("A Value").getStatus());
        Assertions.assertEquals(Status.SUCCESS, SimpleResult.success("A Value", "A Message").getStatus());
        Assertions.assertEquals("A Value", SimpleResult.success("A Value").getValue());
        Assertions.assertEquals("A Message", SimpleResult.success("A Value", "A Message").getMessage());
        Assertions.assertTrue(SimpleResult.success().succeeded());
        Assertions.assertTrue(SimpleResult.success("A Value").succeeded());
        Assertions.assertTrue(SimpleResult.success("A Value", "A Message").succeeded());
    }

    @DisplayName("Assert error() creates error instances.")
    @Test
    void assertError() {
        Assertions.assertEquals(Status.ERROR, SimpleResult.error().getStatus());
        Assertions.assertEquals(Status.ERROR, SimpleResult.error("A Value").getStatus());
        Assertions.assertEquals(Status.ERROR, SimpleResult.error("A Value", "A Message").getStatus());
        Assertions.assertEquals("A Value", SimpleResult.error("A Value").getValue());
        Assertions.assertEquals("A Message", SimpleResult.error("A Value", "A Message").getMessage());
        Assertions.assertTrue(SimpleResult.error().failed());
        Assertions.assertTrue(SimpleResult.error("A Value").failed());
        Assertions.assertTrue(SimpleResult.error("A Value", "A Message").failed());
    }

    @DisplayName("Assert asOptional() creates a proper Optional instance.")
    @Test
    void assertOptional() {
        final SimpleResult<String> emptySuccessfulResult = SimpleResult.success();
        final Optional<String> optionalString = emptySuccessfulResult.asOptional();
        Assertions.assertTrue(optionalString.isEmpty());
        final SimpleResult<String> successfulResult = SimpleResult.success("A Value");
        final Optional<String> optionalStringWithValue = successfulResult.asOptional();
        Assertions.assertTrue(optionalStringWithValue.isPresent());
        Assertions.assertEquals("A Value", optionalStringWithValue.get());
    }

    @DisplayName("Assert copy() creates a new instance with the same properties.")
    @Test
    void assertCopy() {
        final SimpleResult<String> successfulResult = SimpleResult.success("A Value", "A Message");
        final SimpleResult<String> staticCopySuccessfulResult = SimpleResult.copy(successfulResult);
        Assertions.assertEquals(successfulResult.getStatus(), staticCopySuccessfulResult.getStatus());
        Assertions.assertEquals(successfulResult.getValue(), staticCopySuccessfulResult.getValue());
        Assertions.assertEquals(successfulResult.getMessage(), staticCopySuccessfulResult.getMessage());

        final SimpleResult<String> instanceCopySuccessfulResult = successfulResult.copy();
        Assertions.assertEquals(successfulResult.getStatus(), instanceCopySuccessfulResult.getStatus());
        Assertions.assertEquals(successfulResult.getValue(), instanceCopySuccessfulResult.getValue());
        Assertions.assertEquals(successfulResult.getMessage(), instanceCopySuccessfulResult.getMessage());
    }

    @DisplayName("Assert onSuccess() executes when the result is successful.")
    @Test
    void assertOnSuccess() {
        final SimpleResult<String> successfulResult = SimpleResult.success("A Value");
        Assertions.assertEquals(Status.SUCCESS, successfulResult.getStatus());
        successfulResult.onSuccess(value -> Assertions.assertEquals("A Value", value));
        successfulResult.onSuccess(() -> Assertions.assertTrue(true));
    }

    @DisplayName("Assert onError() executtes when the result is error.")
    @Test
    void assertOnError() {
        final SimpleResult<String> successfulResult = SimpleResult.error("A Value");
        Assertions.assertEquals(Status.ERROR, successfulResult.getStatus());
        successfulResult.onError(value -> Assertions.assertEquals("A Value", value));
        successfulResult.onError(() -> Assertions.assertTrue(true));
    }

}