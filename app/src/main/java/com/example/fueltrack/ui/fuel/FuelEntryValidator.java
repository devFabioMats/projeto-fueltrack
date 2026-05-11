package com.example.fueltrack.ui.fuel;

public class FuelEntryValidator {

    public enum FieldError {
        REQUIRED,
        INVALID_NUMBER,
        NON_POSITIVE
    }

    public static class ValidationResult {
        private final FieldError dateError;
        private final FieldError vehicleError;
        private final FieldError odometerError;
        private final FieldError litersError;
        private final FieldError totalCostError;
        private final Integer odometer;
        private final Double liters;
        private final Double totalCost;

        public ValidationResult(FieldError dateError,
                                FieldError vehicleError,
                                FieldError odometerError,
                                FieldError litersError,
                                FieldError totalCostError,
                                Integer odometer,
                                Double liters,
                                Double totalCost) {
            this.dateError = dateError;
            this.vehicleError = vehicleError;
            this.odometerError = odometerError;
            this.litersError = litersError;
            this.totalCostError = totalCostError;
            this.odometer = odometer;
            this.liters = liters;
            this.totalCost = totalCost;
        }

        public boolean isValid() {
            return dateError == null
                && vehicleError == null
                && odometerError == null
                && litersError == null
                && totalCostError == null;
        }

        public FieldError getDateError() {
            return dateError;
        }

        public FieldError getVehicleError() {
            return vehicleError;
        }

        public FieldError getOdometerError() {
            return odometerError;
        }

        public FieldError getLitersError() {
            return litersError;
        }

        public FieldError getTotalCostError() {
            return totalCostError;
        }

        public Integer getOdometer() {
            return odometer;
        }

        public Double getLiters() {
            return liters;
        }

        public Double getTotalCost() {
            return totalCost;
        }
    }

    public ValidationResult validate(String date,
                                     String vehicle,
                                     String odometerText,
                                     String litersText,
                                     String totalCostText) {
        FieldError dateError = isBlank(date) ? FieldError.REQUIRED : null;
        FieldError vehicleError = isBlank(vehicle) ? FieldError.REQUIRED : null;

        NumberResult<Integer> odometerResult = parseInteger(odometerText);
        NumberResult<Double> litersResult = parseDouble(litersText);
        NumberResult<Double> totalCostResult = parseDouble(totalCostText);

        FieldError odometerError = mapNumberError(odometerResult);
        FieldError litersError = mapNumberError(litersResult);
        FieldError totalCostError = mapNumberError(totalCostResult);

        return new ValidationResult(
            dateError,
            vehicleError,
            odometerError,
            litersError,
            totalCostError,
            odometerResult.value,
            litersResult.value,
            totalCostResult.value
        );
    }

    private FieldError mapNumberError(NumberResult<?> result) {
        if (result.error == NumberError.MISSING) {
            return FieldError.REQUIRED;
        }
        if (result.error == NumberError.INVALID) {
            return FieldError.INVALID_NUMBER;
        }
        if (result.error == NumberError.NON_POSITIVE) {
            return FieldError.NON_POSITIVE;
        }
        return null;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private NumberResult<Integer> parseInteger(String raw) {
        if (isBlank(raw)) {
            return new NumberResult<>(NumberError.MISSING, null);
        }
        try {
            int value = Integer.parseInt(raw.trim());
            if (value <= 0) {
                return new NumberResult<>(NumberError.NON_POSITIVE, null);
            }
            return new NumberResult<>(NumberError.NONE, value);
        } catch (NumberFormatException ex) {
            return new NumberResult<>(NumberError.INVALID, null);
        }
    }

    private NumberResult<Double> parseDouble(String raw) {
        if (isBlank(raw)) {
            return new NumberResult<>(NumberError.MISSING, null);
        }
        String normalized = raw.trim().replace(',', '.');
        try {
            double value = Double.parseDouble(normalized);
            if (value <= 0) {
                return new NumberResult<>(NumberError.NON_POSITIVE, null);
            }
            return new NumberResult<>(NumberError.NONE, value);
        } catch (NumberFormatException ex) {
            return new NumberResult<>(NumberError.INVALID, null);
        }
    }

    private enum NumberError {
        NONE,
        MISSING,
        INVALID,
        NON_POSITIVE
    }

    private static class NumberResult<T> {
        private final NumberError error;
        private final T value;

        private NumberResult(NumberError error, T value) {
            this.error = error;
            this.value = value;
        }
    }
}

