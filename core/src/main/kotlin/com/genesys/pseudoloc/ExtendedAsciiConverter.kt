package com.genesys.pseudoloc

class ExtendedAsciiConverter {

    internal val lookup = mapOf(
            'a' to arrayOf('å', 'â', 'á', 'à')
    )

    fun convert(input: String) : String {
        val iterators = assembleIterators()
        // convert
        var output = StringBuilder()
        for (c in input.toCharArray()) {
            if (lookup.containsKey(c)) {
                val iterator = selectIterator(iterators, c);
                output.append(iterator.next())
            } else {
                output.append(c)
            }
        }
        return output.toString()
    }

    private fun assembleIterators() : MutableMap<Char, Iterator<Char>> {
        val iterators : MutableMap<Char, Iterator<Char>> = mutableMapOf()
        for (k in lookup.keys) {
            iterators[k] = lookup[k]!!.iterator()
        }
        return iterators
    }

    private fun selectIterator(iterators: MutableMap<Char, Iterator<Char>>, c : Char) : Iterator<Char> {
        var iterator = iterators[c]
        iterator = if (iterator!!.hasNext()) {
            // use the same iterator if it hasn't reached its end
            iterator
        } else {
            // use a fresh iterator (rolling back to the first substitution for the char)
            iterators[c] = lookup[c]!!.iterator()
            iterator
        }
        return iterator
    }
}