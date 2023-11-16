package christmas.domain.discount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public abstract class DiscountCalculatorTest implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        List<Arguments> arguments = new ArrayList<>();
        for (int day = 1; day <= 31; day++) {
            for (int selectedCount = 1; selectedCount <= 20; selectedCount++) {
                arguments.add(makeSingleArgument(day, selectedCount));
            }
        }
        return arguments.stream();
    }

    abstract Arguments makeSingleArgument(int day, int selectedCount);
}
