package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Lottos {
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

    public LottoResult getResult(final WinningLotto winningLotto) {
        final LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos) {
            lottoResult.updateCount(lotto.findRankingByLotto(winningLotto.getWinningLotto()));
        }
        return lottoResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos = (Lottos) o;
        return Objects.equals(this.lottos, lottos.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
