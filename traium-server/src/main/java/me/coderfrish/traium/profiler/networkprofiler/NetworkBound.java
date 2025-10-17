package me.coderfrish.traium.profiler.networkprofiler;

public enum NetworkBound {
    C2S("c -> s"),
    S2C("s -> c");

    private final String keyword;

    NetworkBound(String keyword) {
        this.keyword = keyword;
    }

    public final String getKeyword() {
        return this.keyword;
    }
}
