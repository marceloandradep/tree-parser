package br.com.pereira.treeparser.model

class ValueNode {

    String value
    String path

    List<RuleEdge> neighbours = []

    static ValueNode fromEntry(TreeEntry entry) {
        [
                value: entry.value,
                path: entry.path
        ] as ValueNode
    }

    boolean isChildOf(ValueNode parent) {
        path.startsWith(parent.path)
    }

    void connectWith(ValueNode node, String condition) {
        RuleEdge edge = [
                condition: condition,
                toNode: node
        ] as RuleEdge

        neighbours << edge
    }

}
