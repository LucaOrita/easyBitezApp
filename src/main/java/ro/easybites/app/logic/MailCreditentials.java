package ro.easybites.app.logic;

public enum MailCreditentials {
    EMAIL("oceasyfoods@gmail.com"),
    PASS("pnjxpjnzvdnkrtks");

    private final String value;

    MailCreditentials(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
