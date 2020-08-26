package lotto.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Lottos implements Iterable<Lotto> {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<Ranking> matchesWinningLotto(WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::match)
                .collect(toList());
    }

    public Lottos concat(Lottos others) {
        return Stream.of(lottos, others.lottos)
                .flatMap(List::stream)
                .collect(collectingAndThen(toList(), Lottos::of));
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos that = (Lottos) o;
        return Objects.equals(this.lottos, that.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
