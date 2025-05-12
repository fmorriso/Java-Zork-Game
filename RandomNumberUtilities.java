import java.util.Random;

/**
 * A class of methods that returns random numbers in a natural way (both ends are included) rather than
 * the unnatural way the built-in utilities work.
 */
public class RandomNumberUtilities {

    private static Random rand = new Random();


    /**
     * Return a random number of type double in the range between the specified minimum (inclusive)
     * and maximum (inclusive).
     *
     * @param min       - the minimum value in the range to include in the eligible values to return.  ** INCLUSIVE **
     * @param max       - the maximum value in the range  to include in the eligible values to return.  ** INCLUSIVE **
     * @apiNote - The nature of how Java's built-in random number generator works
     *            for type double means that, without some intervention, the values returned would almost never
     *            include either the minimum or maximum value even though the minimum
     *            value is eligible to be returned.  In this implementation, we introduce logic to ensure
     *            both the minimum and maximum will be eligible to be returned.
     */
    public static double getRandomDoubleInRange(double min, double max) {

        if(min >= max) throw new IllegalArgumentException("Min must be less than max");

        final double tolerance = (max - min) / 10_000;
        return getRandomDoubleInRangeWithTolerance(min, max, tolerance);
    }

    /**
     * Return a random number of type double precision in the range between the specified minimum and maximum,
     * inclusive of both the minimum and maximum, using the specified positive, non-zero
     * tolerance.
     *
     * @param min       - the minimum value in the range ** INCLUSIVE **
     * @param max       - the maximum value in the range ** INCLUSIVE **
     * @param tolerance - the tolerance to use when generating the random number.
     * @apiNote - The tolerance is subtracted from the minimum and added to the maximum
     * in order to ensure that both the minimum and maximum are included in the range
     * of eligible random values.
     */
    public static double getRandomDoubleInRangeWithTolerance(double min, double max, double tolerance) {

        if(tolerance <= 0) throw new IllegalArgumentException("Tolerance must be greater than zero");

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
     * Returns a random whole number between the specified minimum and maximum
     * @param min - The minimum value in the range  ** INCLUSIVE **
     * @param max - The maximum value in the range  ** INCLUSIVE **
     * @return A random whole number in the range (max - min) + min.
     * @apiNote Since the built-in random number generator does not by design
     *          include max in the eligible range, we have to add 1 to max in order to
     *          make sure it is eligible to be returned.
     */
    public static int getRandomIntInRange(int min, int max) {
        return rand.nextInt(min, max + 1);
    }

}
