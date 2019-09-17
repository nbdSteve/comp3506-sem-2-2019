import java.util.*;

/**
 * Class that implements the social media feed searches
 */
public class FeedAnalyser {
    private List<FeedItem> feedItems;
    private Stack<FeedItem> itemsByUpvotes;

    /**
     * Loads social media feed data from a file
     *
     * @param filename the file to load from
     */
    public FeedAnalyser(String filename) {
        Iterator<FeedItem> iter = new Util.FileIterator(filename);
        feedItems = new ArrayList<>();
        while (iter.hasNext()) {
            FeedItem item = iter.next();
            feedItems.add(item);
        }
        itemsByUpvotes = new Stack<>();
        itemsByUpvotes.addAll(feedItems);
    }

    /**
     * Return all feed items posted by the given username between startDate and endDate (inclusive)
     * If startDate is null, items from the beginning of the history should be included
     * If endDate is null, items until the end of the history should be included
     * The resulting list should be ordered by the date of each FeedItem
     * If no items that meet the criteria can be found, the empty list should be returned
     *
     * @param username  the user to search the posts of
     * @param startDate the date to start searching from
     * @param endDate   the date to stop searching at
     * @return a list of FeedItems made by username between startDate and endDate
     * @require username != null
     * @ensure result != null
     */
    public List<FeedItem> getPostsBetweenDates(String username, Date startDate, Date endDate) {
        if (username == null) return new ArrayList<>();
        List<FeedItem> result = new ArrayList<>();
        for (FeedItem item : feedItems) {
            if (!item.getUsername().equalsIgnoreCase(username)) continue;
            if (item.getDate().after(startDate) && item.getDate().before(endDate)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Return the first feed item posted by the given username at or after searchDate
     * That is, the feed item closest to searchDate that is greater than or equal to searchDate
     * If no items that meet the criteria can be found, null should be returned
     *
     * @param username   the user to search the posts of
     * @param searchDate the date to start searching from
     * @return the first FeedItem made by username at or after searchDate
     * @require username != null && searchDate != null
     */
    public FeedItem getPostAfterDate(String username, Date searchDate) {
        if (username == null || searchDate == null) return null;
        List<FeedItem> validItems = new ArrayList<>();
        for (FeedItem item : feedItems) {
            if (!item.getUsername().equals(username)) continue;
            if (item.getDate().equals(searchDate) || item.getDate().after(searchDate)) {
                validItems.add(item);
            }
        }
        if (validItems.isEmpty()) return null;
        validItems.sort(Comparator.comparing(FeedItem::getDate));
        return validItems.get(0);
    }

    /**
     * Return the feed item with the highest upvote
     * Subsequent calls should return the next highest item
     * i.e. the nth call to this method should return the item with the nth highest upvote
     * Posts with equal upvote counts can be returned in any order
     *
     * @return the feed item with the nth highest upvote value,
     * where n is the number of calls to this method
     * @throws NoSuchElementException if all items in the feed have already been returned
     *                                by this method
     */
    public FeedItem getHighestUpvote() throws NoSuchElementException {
        if (itemsByUpvotes.isEmpty()) throw new NoSuchElementException();
        itemsByUpvotes.sort(Comparator.comparing(FeedItem::getUpvotes));
        return itemsByUpvotes.pop();
    }


    /**
     * Return all feed items containing the specific pattern in the content field
     * Case should not be ignored, eg. the pattern "hi" should not be matched in the text "Hi there"
     * The resulting list should be ordered by FeedItem ID
     * If the pattern cannot be matched in any content fields the empty list should be returned
     *
     * @param pattern the substring pattern to search for
     * @return all feed items containing the pattern string
     * @require pattern != null && pattern.length() > 0
     * @ensure result != null
     */
    public List<FeedItem> getPostsWithText(String pattern) {
        List<FeedItem> validItems = new ArrayList<>();
        if (pattern == null || pattern.length() <= 0) return validItems;
        for (FeedItem item : feedItems) {
            if (item.getContent().contains(pattern)) validItems.add(item);
        }
        return validItems;
    }
}
