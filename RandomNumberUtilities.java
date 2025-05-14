import java.util.Random;

/**
 * A class of methods that returns random numbers in a natural way (both ends are included) rather than
 * the unnatural way the built-in utilities work.
 */
public class RandomNumberUtilities {

    private static final Random rand = new Random();


    /**
     * Return a random number of type double in the range between the specified minimum (inclusive)
     * and maximum (inclusive).
     *
     * @param min - the minimum value in the range to include in the eligible values to return.  ** INCLUSIVE **
     * @param max - the maximum value in the range  to include in the eligible values to return.  ** INCLUSIVE **
     * @return - a double-precision number.
     * @apiNote - The nature of how Java's built-in random number generator works
     * for type double means that, without some intervention, the values returned would almost never
     * include either the minimum or maximum value even though the minimum
     * value is eligible to be returned.  In this implementation, we introduce logic to ensure
     * both the minimum and maximum will be eligible to be returned.
     */
    public static double getRandomDoubleInRange(double min, double max) {

        if (min >= max) throw new IllegalArgumentException("Min must be less than max");

        final double tolerance = (max - min) / 10_000;
        return getRandomDoubleInRangeWithTolerance(min, max, tolerance);
    }

    /**
     * @param min       - the minimum value in the range to include in the eligible values to return.
     * @param max       - the maximum value in the range  to include in the eligible values to return.
     * @param inclusive - true if the max value should be included in the eligible values generated.
     * @return - a double-precision number.
     */
    public static double getRandomDoubleInRange(double min, double max, boolean inclusive) {
        if (inclusive) {
            return getRandomDoubleInRange(min, max);
        }
        return getRandomDoubleInRange(min, max - max / 10_000.0);
    }


    /**
     * Return a random number of type double precision in the range between the specified minimum and maximum,
     * inclusive of both the minimum and maximum, using the specified positive, non-zero
     * tolerance.
     *
     * @param min       - the minimum value in the range ** INCLUSIVE **
     * @param max       - the maximum value in the range ** INCLUSIVE **
     * @param tolerance - the tolerance to use when generating the random number.
     * @return - a double-precision random number.
     * @apiNote - The tolerance is subtracted from the minimum and added to the maximum
     * in order to ensure that both the minimum and maximum are included in the range
     * of eligible random values when indicated.
     */
    public static double getRandomDoubleInRangeWithTolerance(double min, double max, double tolerance) {

        if (tolerance <= 0) throw new IllegalArgumentException("Tolerance must be greater than zero");

        final double minWithTolerance = min - tolerance;
        final double maxWithTolerance = max + tolerance;
        final double range = maxWithTolerance - minWithTolerance;

        double randomNumber = rand.nextDouble() * range + minWithTolerance;
        if (randomNumber >= min && randomNumber <= max) {
            return randomNumber;
        } else if (randomNumber < min) {
            return min;
        } else
            return max;
    }

    /**
     * Return a random number of type double precision in the range between the specified minimum and maximum,
     * that is either inclusive or exclusive of the minimum and maximum, depending on the value of inclusive,
     * using the specified positive, non-zero tolerance.
     *
     * @param min       - the minimum value in the range
     * @param max       - the maximum value in the range
     * @param tolerance - the tolerance to use when generating the random number.
     * @param inclusive - true if the end points are to be included in the allowable
     *                  values returned; otherwise the maximum is not included and the
     *                  probability of the minimum being included is very low
     * @return - a double-precision random number.
     */
    public static double getRandomDoubleInRangeWithTolerance(double min, double max, double tolerance, boolean inclusive) {
        if (inclusive) {
            return getRandomDoubleInRangeWithTolerance(min, max, tolerance);
        }
        return getRandomDoubleInRangeWithTolerance(min, max, 0);
    }

    /**
     * Returns a random whole number between the specified minimum and maximum
     *
     * @param min - The minimum value in the range  ** INCLUSIVE **
     * @param max - The maximum value in the range  ** INCLUSIVE **
     * @return A random whole number in the range (max - min) + min.
     * @apiNote Since the built-in random number generator does not by design
     * include max in the eligible range, we have to add 1 to max in order to
     * make sure it is eligible to be returned.
     */
    public static int getRandomIntInRange(int min, int max) {
        return getRandomIntInRange(min, max, true);
    }

    /**
     * Returns a random whole number between the specified minimum and maximum
     *
     * @param min       - The minimum value in the range
     * @param max       - The maximum value in the range
     * @param inclusive - true if max is included in the range of returned values; otherwise max is not included.
     * @return A random whole number in the range (max - min) + min or (max - min + 1)  + min depending on inclusive.
     * @apiNote Since the built-in random number generator does not by design
     * include max in the eligible range, when inclusive is true, we have to add 1 to max in order to
     * make sure it is eligible to be returned.
     */
    public static int getRandomIntInRange(int min, int max, boolean inclusive) {
        if (inclusive) return rand.nextInt(min, max + 1);
        return rand.nextInt(max - min) + min;
    }

}
