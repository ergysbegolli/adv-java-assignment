package com.company.app;

import java.util.List;

class StatVariables {
    private String filepath;
    private String stat;
    private int limit;
    private String by;
    private String display;

    StatVariables(List<String> args) {
        this.filepath = args.get(0);
        this.stat = args.get(1);
        this.limit = Integer.parseInt(args.get(2));
        this.by = args.get(3);
        this.display = args.get(4);
    }

    public String getFilepath() {
        return filepath;
    }

    public String getStat() {
        return stat;
    }

    public int getLimit() {
        return limit;
    }

    public String getBy() {
        return by;
    }

    public String getDisplay() {
        return display;
    }
}
