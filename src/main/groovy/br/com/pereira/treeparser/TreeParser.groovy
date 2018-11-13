package br.com.pereira.treeparser

import br.com.pereira.treeparser.model.TreeEntry
import br.com.pereira.treeparser.model.ValueNode

class TreeParser {

    ValueNode parseTree(List<TreeEntry> entries) {
        if (entries && entries.size()) {
            sortEntryList(entries)
            return parse(entries)
        }

        return null
    }

    private static ValueNode parse(List<TreeEntry> entries) {
        List<ValueNode> nodes = []

        entries.each { TreeEntry entry ->
            if (nodes.isEmpty()) {
                nodes.push(ValueNode.fromEntry(entry))
            } else {
                ValueNode parent = nodes.last()
                ValueNode current = ValueNode.fromEntry(entry)

                while(!current.isChildOf(parent) && !nodes.isEmpty()) {
                    nodes.pop()
                    parent = nodes.last()
                }

                parent.connectWith(current, entry.condition)
                nodes.push(current)
            }
        }

        nodes.first()
    }

    private static void sortEntryList(List<TreeEntry> entries) {
        entries.sort({ TreeEntry t1, TreeEntry t2 ->
            t1.path <=> t2.path
        })
    }

}
