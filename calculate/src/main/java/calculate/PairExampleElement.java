package calculate;

import java.util.List;

public class PairExampleElement {
    PairExampleElement pairExampleElement;
    List<Double> numberList;
    List<Character> signList;

    PairExampleElement(List<Double> numberList, List<Character> signList) {
        this.numberList = numberList;
        this.signList = signList;
    }

    public List<Double> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<Double> numberList) {
        this.numberList = numberList;
    }

    public List<Character> getSignList() {
        return signList;
    }

    public void setSignList(List<Character> signList) {
        this.signList = signList;
    }

    public void remove(final int index) {
        numberList.remove(index);
        signList.remove(index);
    }

    public void addNumber(final Double number) {
        numberList.add(number);
    }

    public void addSign(final Character sign) {
        signList.add(sign);
    }

    public void setNumber(final int index, final Double number) {
        numberList.set(index, number);
    }

    public void setSign(final int index, final Character sign) {
        signList.set(index, sign);
    }

    public void set(final List<Double> numberList, final List<Character> signList) {
        this.numberList = numberList;
        this.signList = signList;
    }

    public void removeNumber(final int index) {
        numberList.remove(index);
    }

    public void removeSign(final int index) {
        signList.remove(index);
    }

    public int size() {
        return signList.size();
    }





}
