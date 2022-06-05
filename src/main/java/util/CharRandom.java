package util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.Value;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author caotc
 * @date 2022-05-07
 * @since 1.0.0
 */
@Value
@Builder(toBuilder = true)
public class CharRandom {
    public enum CharType {
        NUMBER {
            @Override
            public ImmutableList<Character> chars() {
                ImmutableList.Builder<Character> builder = ImmutableList.builder();
                for (int i = 0; i < 10; i++) {
                    builder.add((char) (i + '0'));
                }
                return builder.build();
            }
        }, SMALL_LETTER {
            @Override
            public ImmutableList<Character> chars() {
                ImmutableList.Builder<Character> builder = ImmutableList.builder();
                for (int i = 0; i < 26; i++) {
                    builder.add((char) (i + 'a'));
                }
                return builder.build();
            }
        }, CAPITAL_LETTER {
            @Override
            public ImmutableList<Character> chars() {
                ImmutableList.Builder<Character> builder = ImmutableList.builder();
                for (int i = 0; i < 26; i++) {
                    builder.add((char) (i + 'A'));
                }
                return builder.build();
            }
        }, ALL {
            @Override
            public ImmutableList<Character> chars() {
                ImmutableList.Builder<Character> builder = ImmutableList.builder();
                for (int i = Character.MIN_VALUE; i < Character.MAX_VALUE; i++) {
                    builder.add((char) (i));
                }
                return builder.build();
            }
        };

        public abstract ImmutableList<Character> chars();
    }

    Random random = new Random();
    @Singular
    ImmutableSet<CharType> charTypes;
    @Getter(lazy = true)
    ImmutableList<Character> chars = charTypes().stream().map(CharType::chars)
            .flatMap(Collection::stream)
            .collect(ImmutableList.toImmutableList());

    public char next() {
        return random.nextElement(chars());
    }

    public Stream<Character> nexts(int size) {
        return random.nextElements(chars(), size);
    }

    public StringRandom toStringRandom() {
        return StringRandom.builder().charSupplier(this::next).minLength(1).maxLength(1).build();
    }
}
