/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.src.twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even
 * exist as a key in the map; this is true even if A is followed by other people
 * in the network. Twitter usernames are not case sensitive, so "ernie" is the
 * same as "ERNie". A username should appear at most once as a key in the map or
 * in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

	/**
	 * Guess who might follow whom, from evidence found in tweets.
	 * 
	 * @param tweets a list of tweets providing the evidence, not modified by this
	 *               method.
	 * @return a social network (as defined above) in which Ernie follows Bert if
	 *         and only if there is evidence for it in the given list of tweets. One
	 *         kind of evidence that Ernie follows Bert is if Ernie
	 * @-mentions Bert in a tweet. This must be implemented. Other kinds of evidence
	 *            may be used at the implementor's discretion. All the Twitter
	 *            usernames in the returned social network must be either authors
	 *            or @-mentions in the list of tweets.
	 */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
		// throw new RuntimeException("not implemented");
		Map<String, Set<String>> followMap = new HashMap<String, Set<String>>();
		for (Tweet x : tweets) {
			if (followMap.containsKey(x.getAuthor())) {
				Set<String> xxSet = new HashSet<String>();
				xxSet.addAll(Extract.getMentionedUsers(Arrays.asList(x)));
				xxSet.addAll(followMap.get(x.getAuthor()));
				followMap.put(x.getAuthor(), xxSet);
			} else {
				followMap.put(x.getAuthor(), Extract.getMentionedUsers(Arrays.asList(x)));
			}
		}

		Pattern p = Pattern.compile("([^a-z0-9A-Z_-]+)#([a-zA-Z0-9_-]+)");
		Tweet abc[] = new Tweet[tweets.size()];
		tweets.toArray(abc);
		for (int i = 0; i < tweets.size(); i++) {
			// Matcher m = p.matcher(.getText().toLowerCase());
			for (int j = 0; j < tweets.size(); j++) {
				if (i != j) {
					Matcher mi = p.matcher(abc[i].getText().toLowerCase());
					Matcher mj = p.matcher(abc[j].getText().toLowerCase());
					if (mi.find() && mj.find()) {
						if (mi.group(2).equals(mj.group(2))) {
							Set<String> xxSet_i = new HashSet<String>();
							xxSet_i.addAll(followMap.get(abc[i].getAuthor()));
							xxSet_i.add(abc[j].getAuthor().toLowerCase());
							followMap.put(abc[i].getAuthor(), xxSet_i);
							Set<String> xxSet_j = new HashSet<String>();
							xxSet_j.addAll(followMap.get(abc[j].getAuthor()));
							xxSet_j.add(abc[i].getAuthor().toLowerCase());
							followMap.put(abc[j].getAuthor(), xxSet_j);
						}
					}
				}

			}
		}
		return followMap;
	}

	/**
	 * Find the people in a social network who have the greatest influence, in the
	 * sense that they have the most followers.
	 * 
	 * @param followsGraph a social network (as defined above)
	 * @return a list of all distinct Twitter usernames in followsGraph, in
	 *         descending order of follower count.
	 */
	public static List<String> influencers(Map<String, Set<String>> followsGraph) {
		// throw new RuntimeException("not implemented");
		Map<String, Integer> frenquentMap = new HashMap<String, Integer>();
		List<String> influenceList = new ArrayList<String>();
		for (String x : followsGraph.keySet()) {
			for (String y : followsGraph.get(x)) {
				if (!frenquentMap.containsKey(y)) {
					frenquentMap.put(y, 0);
				}
				if (frenquentMap.containsKey(y)) {
					int t = frenquentMap.get(y) + 1;
					frenquentMap.put(y, t);
				}
			}
		}
		for (String x : frenquentMap.keySet()) {
			System.out.println(x);
			System.out.println(frenquentMap.get(x));

		}
		while (!frenquentMap.isEmpty()) {
			int max = 0;
			String tmpString = null;
			for (String x : frenquentMap.keySet()) {
				if (frenquentMap.get(x) > max) {
					max = frenquentMap.get(x);
					tmpString = x;
				}
			}
			influenceList.add(tmpString);
			frenquentMap.remove(tmpString);
		}
		return influenceList;
	}

}
