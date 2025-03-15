package org.karane;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Sets;
import com.google.common.base.Optional;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Set;
import java.util.List;
import java.util.concurrent.*;

public class GuavaExample {
    public static void main(String[] args) {
        // Example 1: ListenableFuture
        System.out.println("\nExample 1: ListenableFuture");
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        ListenableFuture<Integer> future = listeningExecutorService.submit(() -> 15);
        
        future.addListener(() -> {
            try {
                System.out.println("\tResult: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, MoreExecutors.directExecutor());

        // Example 2: Immutable List
        System.out.println("\nExample 2: Immutable List");
        ImmutableList<String> list = ImmutableList.of("apple", "banana", "cherry");
        System.out.println("\tImmutable List: " + list);

        // Example 3: Cache
        System.out.println("\nExample 3: Cache");
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
        cache.put("key", "value");
        String value = cache.getIfPresent("key");
        System.out.println("\tCache value: " + value);

        // Example 4: String Manipulation
        System.out.println("\nExample 4: String Manipulation");
        String result = Joiner.on(", ").join("apple", "banana", "cherry");
        System.out.println("\tJoined String: " + result);

        String toSplit = "apple, banana, , cherry, date";
        List<String> splitList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(toSplit);
        System.out.println("\tSplit List: " + splitList);

        // Example 5: Optional
        System.out.println("\nExample 5: Optional");
        Optional<String> optionalString = Optional.of("Hello, Guava!");
        System.out.println("\t" + optionalString.get());

        // Example 6: Predicate
        System.out.println("\nExample 6: Predicate");
        Predicate<String> startsWithA = str -> str.startsWith("A");
        System.out.println("\t" + startsWithA.apply("Apple"));  // Output: true

        // Example 7: FluentIterable
        System.out.println("\nExample 7: FluentIterable");
        List<String> items = FluentIterable.from(ImmutableList.of("apple", "banana", "cherry"))
                .filter(str -> str.contains("a"))
                .toList();
        System.out.println("\t" + items);  // Output: [apple, banana]

        // Example 8: Sets
        System.out.println("\nExample 8: Sets");
        Set<String> set1 = Sets.newHashSet("apple", "banana", "cherry");
        Set<String> set2 = Sets.newHashSet("banana", "cherry", "date");
        Set<String> union = Sets.union(set1, set2);
        System.out.println("\tUnion: " + union);

        // Example 9: Multimap
        System.out.println("\nExample 9: Multimap");
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("fruit", "apple");
        multimap.put("fruit", "banana");
        multimap.put("vegetable", "carrot");
        System.out.println("\tMultimap: " + multimap);
        
        // Example 10: EventBus
        System.out.println("\nExample 10: EventBus");
        EventBus eventBus = new EventBus();
        eventBus.register(new EventListener());
        eventBus.post("Hello, EventBus!");
    }

    static class EventListener {
        @Subscribe
        public void handleEvent(String message) {
            System.out.println("\tListened: " + message);
        }
    }
}
