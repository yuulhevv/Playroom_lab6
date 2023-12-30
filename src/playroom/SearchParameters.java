package playroom;

/**
 * SearchParameters
 *
 * @param minSize
 * @param maxSize
 * @param minPrice
 * @param maxPrice
 * @param category
 */
public record SearchParameters(int minSize, int maxSize, double minPrice, double maxPrice, String category) {
}
