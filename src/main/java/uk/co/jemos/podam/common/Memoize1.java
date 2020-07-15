package uk.co.jemos.podam.common;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * This class is used for memoization of execution of functions taking one argument and returning value.
 * @param <P> type of an argument of memoized function
 * @param <V> return type of memoized function
 *
 * @author zonkeydonkey
 *
 */
abstract public class Memoize1<P, V> {

        private Map<P, V> cache = new HashMap<P, V>();

        public synchronized V get(P p) {
            V value = cache.get(p);
            if (value != null) {
                return value;
            } else {
                V newValue = calculate(p);
                cache.put(p, newValue);
                return newValue;
            }
        }

        protected abstract V calculate(P p);
}
