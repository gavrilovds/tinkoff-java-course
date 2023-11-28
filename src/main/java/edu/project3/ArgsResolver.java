package edu.project3;

import edu.project3.model.Argument;
import edu.project3.model.ArgumentType;
import java.util.ArrayList;
import java.util.List;

public class ArgsResolver {

    private int currentArgsPosition;
    private ArgumentType currentArg;

    public ArgsResolver() {
        currentArgsPosition = 0;
        currentArg = ArgumentType.PATH;
    }

    public List<Argument> resolve(String[] args) {
        if (args.length < 2 || !args[0].equals(ArgumentType.PATH.getArgument())) {
            throw new IllegalArgumentException("args should firstly contain --path parameter");
        }
        List<Argument> resolvedArgs = new ArrayList<>();
        resolvedArgs.add(new Argument(ArgumentType.PATH, resolvePath(args)));
        do {
            switch (currentArg) {
                case FORMAT -> {
                    resolvedArgs.add(new Argument(ArgumentType.FORMAT, resolveArg(args)));
                }
                case TO -> {
                    resolvedArgs.add(new Argument(ArgumentType.TO, resolveArg(args)));
                }
                case FROM -> {
                    resolvedArgs.add(new Argument(ArgumentType.FROM, resolveArg(args)));
                }
                default -> {
                }
            }
            if (currentArgsPosition + 1 <= args.length - 1) {
                if (isArg(args[++currentArgsPosition])) {
                    continue;
                } else {
                    break;
                }
            }
        } while (currentArgsPosition != args.length - 1);

        return resolvedArgs;
    }

    private String resolvePath(String[] args) {
        StringBuilder allPaths = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (!isArg(args[i])) {
                allPaths.append(args[i]).append(" ");
                continue;
            }
            currentArgsPosition = i;
            break;
        }
        return allPaths.toString().trim();
    }

    private String resolveArg(String[] args) {
        if (currentArgsPosition + 1 >= args.length) {
            throw new IllegalArgumentException("empty argument for %s".formatted(currentArg.getArgument()));
        }
        return args[++currentArgsPosition];
    }

    private boolean isArg(String arg) {
        try {
            currentArg = ArgumentType.findByArgument(arg);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
