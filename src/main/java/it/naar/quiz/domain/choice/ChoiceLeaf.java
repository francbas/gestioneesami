package it.naar.quiz.domain.choice;

public class ChoiceLeaf extends AbstractChoice {
    private int leafAuthoringOrderIndex;

    public ChoiceLeaf(int leafAuthoringOrderIndex) {
        this.leafAuthoringOrderIndex = leafAuthoringOrderIndex;
    }

    public ChoiceLeaf(String text, int leafAuthoringOrderIndex) {
        super(text);
        this.leafAuthoringOrderIndex = leafAuthoringOrderIndex;
    }

    public int getLeafAuthoringOrderIndex() {
        return leafAuthoringOrderIndex;
    }
    public void setLeafAuthoringOrderIndex(int leafAuthoringOrderIndex) {
        this.leafAuthoringOrderIndex = leafAuthoringOrderIndex;
    }
}
