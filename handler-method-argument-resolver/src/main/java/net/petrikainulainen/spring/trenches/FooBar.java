package net.petrikainulainen.spring.trenches;

/**
 * @author Petri Kainulainen
 */
public class FooBar {

    private final String bar;
    private final String foo;

    FooBar(String bar, String foo) {
        this.bar = bar;
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }

    public String getFoo() {
        return foo;
    }

    @Override
    public String toString() {
        return "FooBar{" +
                "bar='" + bar + '\'' +
                ", foo='" + foo + '\'' +
                '}';
    }
}
