package com.example.fueltrack.ui.fuel;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FuelEntryValidatorTest {

    @Test
    public void validate_missingFields_returnsErrors() {
        FuelEntryValidator validator = new FuelEntryValidator();

        FuelEntryValidator.ValidationResult result = validator.validate(
            "",
            "",
            "",
            "",
            ""
        );

        assertFalse(result.isValid());
        assertNotNull(result.getDateError());
        assertNotNull(result.getVehicleError());
        assertNotNull(result.getOdometerError());
        assertNotNull(result.getLitersError());
        assertNotNull(result.getTotalCostError());
    }

    @Test
    public void validate_validFields_returnsParsedValues() {
        FuelEntryValidator validator = new FuelEntryValidator();

        FuelEntryValidator.ValidationResult result = validator.validate(
            "10/05/2026",
            "Onix",
            "12345",
            "41.5",
            "256.90"
        );

        assertTrue(result.isValid());
        assertNull(result.getDateError());
        assertNull(result.getVehicleError());
        assertNotNull(result.getOdometer());
        assertNotNull(result.getLiters());
        assertNotNull(result.getTotalCost());
    }
}

