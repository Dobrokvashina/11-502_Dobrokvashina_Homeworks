package ru.itis.inform;

class Entry {
    private String key;
    private String value;
    private int hash;
    private Entry next;

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
        this.hash = hashCode();
    }

    @Override
    public int hashCode() {
        char[] chars = key.toCharArray();

        int code = 0;

        for(int i = 0; i < chars.length; i++) {
            code += chars[i];
        }

        return code;
    }

    public void  setValue(String value) {
        this.value = value;
    }

    public String getKey(){
        return key;
    }

    public String getValue() {
        return value;
    }

    public Entry getNext() {
        return next;
    }

    public void setNext(Entry next) {
        this.next = next;
    }

    public int getHash() {
        return hash;
    }
}
