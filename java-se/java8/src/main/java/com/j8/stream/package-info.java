/**
 * j8流处理。
 * <p>
 * stream是什么：a sequence of elements from a source that supports aggregate
 * operations. Streams consume from a source such as collections, arrays, or I/O
 * resources. Streams support the common operations from functional programing
 * languages, such as map, filter, reduce, find, sorted, etc.
 * 
 * <p>
 * The difference between Stream and Collection can be itemized as follows:
 * 
 * <li>A stream provides an interface to a sequenced set of values of a specific
 * element type. However, unlike collections, streams don’t actually store
 * elements. The elements are computed on demand. Streams can be viewed as
 * lazily constructed Collections, whose values are computed when they are
 * needed.
 * <li>Stream operations don't change its source. Instead, they return new
 * streams that store the result.
 * <li>Possibly unbounded. Collections have a finite size, but streams don't.
 * Short-circuiting operations such as limit(n) or findFirst() can allow
 * computations on infinite streams to complete in finite time.
 * <li>Consumable. During the lifetime of a stream, the elements of the stream
 * are only visited once. If you want to revisit the same element in the stream,
 * you will need to regenerate a new stream based on the source.
 * 
 * <p>
 * Frequently used intermediate operations include: filter, map,
 * distinct,sorted, skip, limit, flatMap
 * 
 * <p>
 * Frequently used terminal operations include:
 * <li>1.forEach, toArray, collect, reduce, count, min, max
 * <li>2.findFirst, findAny, anyMatch, allMatch, noneMatch
 * 
 * @author mutou
 *
 */
package com.j8.stream;