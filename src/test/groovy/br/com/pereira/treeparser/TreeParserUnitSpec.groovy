package br.com.pereira.treeparser

import br.com.pereira.treeparser.model.TreeEntry
import br.com.pereira.treeparser.model.ValueNode
import spock.lang.Specification

class TreeParserUnitSpec extends Specification {

    TreeParser parser = new TreeParser()

    def 'Single node'() {
        given:
        List<TreeEntry> entries = [
                [
                        path: '1',
                        value: '1'
                ] as TreeEntry
        ]

        when:
        ValueNode node = parser.parseTree(entries)

        then:
        node.value == entries[0].value
    }

    def 'Single branch'() {
        given:
        List<TreeEntry> entries = [
                [
                        path: '1',
                        value: '1'
                ] as TreeEntry,
                [
                        path: '1.1',
                        condition: 'a',
                        value: '2'
                ] as TreeEntry,
                [
                        path: '1.1.1',
                        condition: 'b',
                        value: '3'
                ] as TreeEntry
        ]

        when:
        ValueNode node = parser.parseTree(entries)

        then:
        node.value == entries[0].value
        node.neighbours[0].toNode.value == entries[1].value
        node.neighbours[0].toNode.neighbours[0].toNode.value == entries[2].value

    }

    def 'Two branches'() {

        given:
        List<TreeEntry> entries = [
                [
                        path: '1',
                        value: '1'
                ] as TreeEntry,
                [
                        path: '1.1',
                        condition: 'a',
                        value: '2'
                ] as TreeEntry,
                [
                        path: '1.2',
                        condition: 'b',
                        value: '3'
                ] as TreeEntry
        ]

        when:
        ValueNode node = parser.parseTree(entries)

        then:
        node.value == entries[0].value
        node.neighbours[0].toNode.value == entries[1].value
        node.neighbours[1].toNode.value == entries[2].value

    }

    def 'Deep branches'() {

        given:
        List<TreeEntry> entries = [
                [
                        path: '1',
                        value: '1'
                ] as TreeEntry,
                [
                        path: '1.1',
                        condition: 'a',
                        value: '2'
                ] as TreeEntry,
                [
                        path: '1.1.1',
                        condition: 'b',
                        value: '3'
                ] as TreeEntry,
                [
                        path: '1.2',
                        condition: 'c',
                        value: '4'
                ] as TreeEntry,
        ]

        when:
        ValueNode node = parser.parseTree(entries)

        then:
        node.value == entries[0].value
        node.neighbours[0].toNode.value == entries[1].value
        node.neighbours[0].toNode.neighbours[0].toNode.value == entries[2].value
        node.neighbours[1].toNode.value == entries[3].value

    }
}
