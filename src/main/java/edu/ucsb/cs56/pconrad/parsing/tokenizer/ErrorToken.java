package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class ErrorToken extends Token {

    private String value;

    public ErrorToken(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ErrorToken(" + value + ")";
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj)    {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ErrorToken other = (ErrorToken) obj;
        return other.value.equals(this.value);
    }
}
