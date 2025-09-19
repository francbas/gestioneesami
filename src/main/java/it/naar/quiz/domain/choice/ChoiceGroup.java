package it.naar.quiz.domain.choice;

import java.util.List;

public class ChoiceGroup extends AbstractChoice {
    private String label;
    private String description;
    private int groupAuthoringOrderIndex;
    private boolean shuffleEnabled;
    private String scoringMode;
    private boolean isDefault;
    private List<ChoiceLeaf> choices;
    private List<ChoiceLeaf> correctChoices;

    public ChoiceGroup() {
    }

    public ChoiceGroup(String label, String description, int groupAuthoringOrderIndex, boolean shuffleEnabled, String scoringMode, boolean isDefault, List<ChoiceLeaf> choices, List<ChoiceLeaf> correctChoices) {
        this.label = label;
        this.description = description;
        this.groupAuthoringOrderIndex = groupAuthoringOrderIndex;
        this.shuffleEnabled = shuffleEnabled;
        this.scoringMode = scoringMode;
        this.isDefault = isDefault;
        this.choices = choices;
        this.correctChoices = correctChoices;
    }

    public ChoiceGroup(String text, String label, String description, int groupAuthoringOrderIndex, boolean shuffleEnabled, String scoringMode, boolean isDefault, List<ChoiceLeaf> choices, List<ChoiceLeaf> correctChoices) {
        super(text);
        this.label = label;
        this.description = description;
        this.groupAuthoringOrderIndex = groupAuthoringOrderIndex;
        this.shuffleEnabled = shuffleEnabled;
        this.scoringMode = scoringMode;
        this.isDefault = isDefault;
        this.choices = choices;
        this.correctChoices = correctChoices;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGroupAuthoringOrderIndex() {
        return groupAuthoringOrderIndex;
    }

    public void setGroupAuthoringOrderIndex(int groupAuthoringOrderIndex) {
        this.groupAuthoringOrderIndex = groupAuthoringOrderIndex;
    }

    public boolean isShuffleEnabled() {
        return shuffleEnabled;
    }

    public void setShuffleEnabled(boolean shuffleEnabled) {
        this.shuffleEnabled = shuffleEnabled;
    }

    public String getScoringMode() {
        return scoringMode;
    }

    public void setScoringMode(String scoringMode) {
        this.scoringMode = scoringMode;
    }

    public List<ChoiceLeaf> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceLeaf> choices) {
        this.choices = choices;
    }

    public List<ChoiceLeaf> getCorrectChoices() {
        return correctChoices;
    }

    public void setCorrectChoices(List<ChoiceLeaf> correctChoices) {
        this.correctChoices = correctChoices;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public void addChoice(ChoiceLeaf choice) {
        if (choice.getQuestion().equals (this.getQuestion())) {
            this.choices.add(choice);
        }
    }
}
