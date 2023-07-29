package GUI;

public class ComboBoxItem {
    private String displayValue;
    private Object hiddenValue;

    public ComboBoxItem(String displayValue, Object hiddenValue) {
        this.displayValue = displayValue;
        this.hiddenValue = hiddenValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public Object getHiddenValue() {
        return hiddenValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
