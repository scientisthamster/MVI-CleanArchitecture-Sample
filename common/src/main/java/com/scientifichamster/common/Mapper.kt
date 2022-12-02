package com.scientifichamster.common

/**
 * Mapper interface.
 */
interface Mapper<I, O> {

    fun from(input: I?): O

    fun to(output: O?): I

    fun fromList(list: List<I>?): List<O> {
        return list?.mapNotNull { from(it) } ?: emptyList()
    }

    fun toList(list: List<O>?): List<I> {
        return list?.mapNotNull { to(it) } ?: emptyList()
    }
}