package job4j.file.searcher;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;

import javax.xml.bind.ValidationException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static job4j.file.searcher.FileFinder.FileSearchParams.*;

public class FileFinder {
    /**
     * Ключи
     * -d - директория, в которой начинать поиск.
     * -n - имя файла, маска, либо регулярное выражение.
     * -t - тип поиска: [name, mask, regexp] mask искать по маске, name по полному совпадение имени, regexp по регулярному выражению.
     * -o - результат записать в файл.
     */
    @SneakyThrows
    public static void main(String[] args) {
        var fileSearchParams = FileSearchParams.of(args)
                .validateThrowIfFail(REQUIRED_COMMANDS, COMMAND_LINE_ARGS_VALIDATION_DEFAULT);
        
        val allFilesPathsJoined = new FileSearcher().findAllFilesAsStringAbsolutePath(
                fileSearchParams.getParamValue(DIRECTORY),
                fileSearchParams.getParamValue(SEARCH_TYPE),
                fileSearchParams.getParamValue(FILE_NAME_MASK_PATTERN_VALUE)
        ).stream().collect(Collectors.joining(System.lineSeparator()));
        
        Files.writeString(
                Path.of(fileSearchParams.getParamValue(RESULT_OUTPUT)),
                allFilesPathsJoined);
    }
    
    
    @RequiredArgsConstructor
    static class FileSearchParams {
        public static final String
                MISSING_REQUIRED_COMMAND = " -- missing required command!",
                MISSING_REQUIRED_COMMAND_VALUE = " -- missing required command value!",
                DIRECTORY = "-d",
                FILE_NAME_MASK_PATTERN_VALUE = "-n",
                SEARCH_TYPE = "-t",
                RESULT_OUTPUT = "-o";
        
        /**
         * key - command name
         * value - validation function (could be replace by new specific Functional Interface but not need it)
         * accept - command argument
         * return - if validation failed = fail validation message
         * - if validation pass = null
         */
        public static final Map<String, Function<String, String>> COMMAND_LINE_ARGS_VALIDATION_DEFAULT = Map.of(
                DIRECTORY, arg -> (arg != null) ? null : "command -d" + MISSING_REQUIRED_COMMAND_VALUE,
                FILE_NAME_MASK_PATTERN_VALUE, arg -> (arg != null) ? null : "command -n" + MISSING_REQUIRED_COMMAND_VALUE,
                SEARCH_TYPE, arg -> (arg != null) ? null : "command -t" + MISSING_REQUIRED_COMMAND_VALUE,
                RESULT_OUTPUT, arg -> (arg != null) ? null : "command -o" + MISSING_REQUIRED_COMMAND_VALUE
        );
        public static final List<String> REQUIRED_COMMANDS = List.of(
                DIRECTORY,
                FILE_NAME_MASK_PATTERN_VALUE,
                SEARCH_TYPE,
                RESULT_OUTPUT
        );
        
        private final Map<String, String> params;
        
        public static FileSearchParams of(String[] args) {
            return new FileSearchParams(mapCommandLineArgsToMap(args));
        }
        
        private static Map<String, String> mapCommandLineArgsToMap(String[] args) {
            boolean keySplitter = true;
            val rsl = new HashMap<String, String>(args.length / 2);
            String key = null;
            for (val arg : args) {
                if (keySplitter) {
                    keySplitter = false;
                    key = arg;
                } else {
                    keySplitter = true;
                    rsl.put(key, arg);
                }
            }
            return rsl;
        }
        
        @SneakyThrows
        public FileSearchParams validateThrowIfFail(List<String> requiredCommands,
                                                    Map<String, Function<String, String>> commandValueValidations) {
            String resultErrorMessage = requiredCommands.stream()
                    .map(requiredCommand -> (params.get(requiredCommand) != null)
                            ? null : requiredCommand + MISSING_REQUIRED_COMMAND)
                    .filter(Objects::nonNull) // валидация нам вернул null == ошибок нету
                    .collect(Collectors.joining(System.lineSeparator()));
            if (!resultErrorMessage.isEmpty()) throw new ValidationException(resultErrorMessage);
            
            
            resultErrorMessage = commandValueValidations.entrySet().stream()
                    .map(validation -> validation.getValue().apply(params.get(validation.getKey())))
                    .filter(Objects::nonNull) // валидация нам вернул null == ошибок нету
                    .collect(Collectors.joining(System.lineSeparator()));
            if (!resultErrorMessage.isEmpty()) throw new ValidationException(resultErrorMessage);
            return this;
        }
        
        public String getParamValue(String paramName) {
            return this.params.get(paramName);
        }
    }
    
    static class FileSearcher extends SimpleFileVisitor<Path> {
        private List<String> lastSearchResults;
        private Predicate<String> lastSearchPredicate;
        
        @SneakyThrows
        public List<String> findAllFilesAsStringAbsolutePath(String startDirectory, String searchType, String searchValue) {
            this.lastSearchResults = new ArrayList<>();
            this.lastSearchPredicate = this.makeFileSearchPredicate(searchType, searchValue);
            Files.walkFileTree(Path.of(startDirectory), this);
            return lastSearchResults;
        }
        
        /**
         * name = "test_copy_file.txt"
         * mask = "^test_copy.*$"
         * regexp = ".*txt"
         *
         * @return function -
         * accept - absolut file path
         * return - collect this file path or not
         */
        public Predicate<String> makeFileSearchPredicate(String searchType, String searchValue) {
            if (searchType.equals("name")) return path -> path.equals(searchValue);
            if (searchType.equals("mask")) {
                var tempSearchValue = searchValue;
                if (tempSearchValue.startsWith("*")) {
                    tempSearchValue = "." + tempSearchValue;
                }
                if (tempSearchValue.endsWith("*")) {
                    tempSearchValue = tempSearchValue.substring(0, tempSearchValue.length() - 1) + ".*";
                }
                val pattern = Pattern.compile(tempSearchValue);
                return path -> pattern.matcher(path).find();
            }
            if (searchType.equals("regexp")) {
                val pattern = Pattern.compile(searchValue);
                return path -> pattern.matcher(path).find();
            }
            return null;
        }
        
        
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (lastSearchPredicate.test(file.getFileName().toString()))
                lastSearchResults.add(file.toAbsolutePath().toString());
            return FileVisitResult.CONTINUE;
        }
    }
    
    
}
