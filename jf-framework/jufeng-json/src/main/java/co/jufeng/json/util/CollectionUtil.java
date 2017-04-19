package co.jufeng.json.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class CollectionUtil {

	private CollectionUtil() {
	}

	public static <T> String join(Iterable<T> collection, String conjunction) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (T item : collection) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(conjunction);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	public static <T> String join(T[] array, String conjunction) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (T item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(conjunction);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	public static List<Entry<Long, Long>> sortEntrySetToList(Set<Entry<Long, Long>> set) {
		List<Entry<Long, Long>> list = new LinkedList<Map.Entry<Long, Long>>(set);
		Collections.sort(list, new Comparator<Entry<Long, Long>>(){

			@Override
			public int compare(Entry<Long, Long> o1, Entry<Long, Long> o2) {
				if (o1.getValue() > o2.getValue()) {
					return 1;
				}
				if (o1.getValue() < o2.getValue()) {
					return -1;
				}
				return 0;
			}
		});
		return list;
	}

	public static <T> List<T> popPart(Stack<T> surplusAlaDatas, int partSize) {
		if (surplusAlaDatas == null || surplusAlaDatas.size() <= 0) {
			return null;
		}

		final List<T> currentAlaDatas = new ArrayList<T>();
		int size = surplusAlaDatas.size();
		if (size > partSize) {
			for (int i = 0; i < partSize; i++) {
				currentAlaDatas.add(surplusAlaDatas.pop());
			}
		} else {
			for (int i = 0; i < size; i++) {
				currentAlaDatas.add(surplusAlaDatas.pop());
			}
		}
		return currentAlaDatas;
	}

	public static <T> List<T> popPart(Deque<T> surplusAlaDatas, int partSize) {
		if (surplusAlaDatas == null || surplusAlaDatas.size() <= 0) {
			return null;
		}

		final List<T> currentAlaDatas = new ArrayList<T>();
		int size = surplusAlaDatas.size();
		if (size > partSize) {
			for (int i = 0; i < partSize; i++) {
				currentAlaDatas.add(surplusAlaDatas.pop());
			}
		} else {
			for (int i = 0; i < size; i++) {
				currentAlaDatas.add(surplusAlaDatas.pop());
			}
		}
		return currentAlaDatas;
	}

	public static <T, K> HashMap<T, K> newHashMap() {
		return new HashMap<T, K>();
	}

	public static <T, K> HashMap<T, K> newHashMap(int size) {
		return new HashMap<T, K>((int) (size / 0.75));
	}

	public static <T> HashSet<T> newHashSet() {
		return new HashSet<T>();
	}

	@SafeVarargs
	public static <T> HashSet<T> newHashSet(T... ts) {
		HashSet<T> set = new HashSet<T>();
		for (T t : ts) {
			set.add(t);
		}
		return set;
	}

	public static <T> ArrayList<T> newArrayList() {
		return new ArrayList<T>();
	}

	@SafeVarargs
	public static <T> ArrayList<T> newArrayList(T... values) {
		return new ArrayList<T>(Arrays.asList(values));
	}

	public static <T> T[] append(T[] buffer, T newElement) {
		T[] t = resize(buffer, buffer.length + 1, newElement.getClass());
		t[buffer.length] = newElement;
		return t;
	}

	public static <T> T[] resize(T[] buffer, int newSize, Class<?> componentType) {
		T[] newArray = newArray(componentType, newSize);
		System.arraycopy(buffer, 0, newArray, 0, buffer.length >= newSize ? newSize : buffer.length);
		return newArray;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] newArray(Class<?> componentType, int newSize) {
		return (T[]) Array.newInstance(componentType, newSize);
	}

	public static <T> T[] resize(T[] buffer, int newSize) {
		return resize(buffer, newSize, buffer.getClass().getComponentType());
	}

	@SafeVarargs
	public static <T> T[] addAll(T[]... arrays) {
		if (arrays.length == 1) {
			return arrays[0];
		}

		int length = 0;
		for (T[] array : arrays) {
			if (array == null) {
				continue;
			}
			length += array.length;
		}
		T[] result = newArray(arrays.getClass().getComponentType().getComponentType(), length);

		length = 0;
		for (T[] array : arrays) {
			if (array == null) {
				continue;
			}
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}

	public static <T> T[] clone(T[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	public static int[] range(int excludedEnd) {
		return range(0, excludedEnd, 1);
	}

	public static int[] range(int includedStart, int excludedEnd) {
		return range(includedStart, excludedEnd, 1);
	}

	public static int[] range(int includedStart, int excludedEnd, int step) {
		if (includedStart > excludedEnd) {
			int tmp = includedStart;
			includedStart = excludedEnd;
			excludedEnd = tmp;
		}

		if (step <= 0) {
			step = 1;
		}

		int deviation = excludedEnd - includedStart;
		int length = deviation / step;
		if (deviation % step != 0) {
			length += 1;
		}
		int[] range = new int[length];
		for (int i = 0; i < length; i++) {
			range[i] = includedStart;
			includedStart += step;
		}
		return range;
	}

	public static <T> List<T> sub(List<T> list, int start, int end) {
		if (list == null || list.isEmpty()) {
			return null;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		if (start > end) {
			int tmp = start;
			start = end;
			end = tmp;
		}

		final int size = list.size();
		if (end > size) {
			if (start >= size) {
				return null;
			}
			end = size;
		}

		return list.subList(start, end);
	}

	public static <T> List<T> sub(Collection<T> list, int start, int end) {
		if (list == null || list.isEmpty()) {
			return null;
		}

		return sub(new ArrayList<T>(list), start, end);
	}

	public static <T> boolean isEmpty(T[] array) {
		return array == null || array.length <= 0;
	}

	public static <T> boolean isNotEmpty(T[] array) {
		return false == isEmpty(array);
	}

	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNotEmpty(Collection<?> collection) {
		return false == isEmpty(collection);
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static <T> boolean isNotEmpty(Map<?, ?> map) {
		return false == isEmpty(map);
	}

	public static <T, K> Map<T, K> zip(T[] keys, K[] values) {
		if (isEmpty(keys) || isEmpty(values)) {
			return null;
		}

		final int size = Math.min(keys.length, values.length);
		final Map<T, K> map = new HashMap<T, K>((int) (size / 0.75));
		for (int i = 0; i < size; i++) {
			map.put(keys[i], values[i]);
		}

		return map;
	}

	public static Map<String, String> zip(String keys, String values, String delimiter) {
		return zip(StringUtil.split(keys, delimiter), StringUtil.split(values, delimiter));
	}

	public static <T, K> Map<T, K> zip(Collection<T> keys, Collection<K> values) {
		if (isEmpty(keys) || isEmpty(values)) {
			return null;
		}

		final List<T> keyList = new ArrayList<T>(keys);
		final List<K> valueList = new ArrayList<K>(values);

		final int size = Math.min(keys.size(), values.size());
		final Map<T, K> map = new HashMap<T, K>((int) (size / 0.75));
		for (int i = 0; i < size; i++) {
			map.put(keyList.get(i), valueList.get(i));
		}

		return map;
	}

	public static <T> boolean contains(T[] array, T value) {
		final Class<?> componetType = array.getClass().getComponentType();
		boolean isPrimitive = false;
		if (null != componetType) {
			isPrimitive = componetType.isPrimitive();
		}
		for (T t : array) {
			if (t == value) {
				return true;
			} else if (false == isPrimitive && null != value && value.equals(t)) {
				return true;
			}
		}
		return false;
	}

	public static <T, K> HashMap<T, K> toMap(Collection<Entry<T, K>> entryCollection) {
		HashMap<T, K> map = new HashMap<T, K>();
		for (Entry<T, K> entry : entryCollection) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

	public static <T> TreeSet<T> toTreeSet(Collection<T> collection, Comparator<T> comparator) {
		final TreeSet<T> treeSet = new TreeSet<T>(comparator);
		for (T t : collection) {
			treeSet.add(t);
		}
		return treeSet;
	}

	public static <T> List<T> sort(Collection<T> collection, Comparator<T> comparator) {
		List<T> list = new ArrayList<T>(collection);
		Collections.sort(list, comparator);
		return list;
	}

	public static Integer[] wrap(int... values) {
		final int length = values.length;
		Integer[] array = new Integer[length];
		for (int i = 0; i < length; i++) {
			array[i] = values[i];
		}
		return array;
	}

	public static Long[] wrap(long... values) {
		final int length = values.length;
		Long[] array = new Long[length];
		for (int i = 0; i < length; i++) {
			array[i] = values[i];
		}
		return array;
	}

	public static Character[] wrap(char... values) {
		final int length = values.length;
		Character[] array = new Character[length];
		for (int i = 0; i < length; i++) {
			array[i] = values[i];
		}
		return array;
	}

	public static Byte[] wrap(byte... values) {
		final int length = values.length;
		Byte[] array = new Byte[length];
		for (int i = 0; i < length; i++) {
			array[i] = values[i];
		}
		return array;
	}

	public static Short[] wrap(short... values) {
		final int length = values.length;
		Short[] array = new Short[length];
		for (int i = 0; i < length; i++) {
			array[i] = values[i];
		}
		return array;
	}

	public static Float[] wrap(float... values) {
		final int length = values.length;
		Float[] array = new Float[length];
		for (int i = 0; i < length; i++) {
			array[i] = values[i];
		}
		return array;
	}

	public static Double[] wrap(double... values) {
		final int length = values.length;
		Double[] array = new Double[length];
		for (int i = 0; i < length; i++) {
			array[i] = values[i];
		}
		return array;
	}

	public static Boolean[] wrap(boolean... values) {
		final int length = values.length;
		Boolean[] array = new Boolean[length];
		for (int i = 0; i < length; i++) {
			array[i] = values[i];
		}
		return array;
	}

	public static boolean isArray(Object obj) {
		return obj.getClass().isArray();
	}
	
	public static <E> Enumeration<E> asEnumeration(final Iterator<E> iter) {
		return new Enumeration<E>() {
			@Override
			public boolean hasMoreElements() {
				return iter.hasNext();
			}

			@Override
			public E nextElement() {
				return iter.next();
			}
		};
	}

	public static <E> Iterator<E> asIterator(final Enumeration<E> e) {
		return new Iterator<E>(){
			@Override
			public boolean hasNext() {
				return e.hasMoreElements();
			}
			
			@Override
			public E next() {
				return e.nextElement();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
