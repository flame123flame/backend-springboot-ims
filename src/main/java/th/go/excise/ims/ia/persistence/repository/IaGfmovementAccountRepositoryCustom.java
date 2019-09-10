package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaGfmovementAccount;
import th.go.excise.ims.ia.vo.Int0804SearchVo;
import th.go.excise.ims.ia.vo.Int0804SummaryVo;

public interface IaGfmovementAccountRepositoryCustom {
	public void batchInsert(List<IaGfmovementAccount> iaGfmovementAccounts);

	public List<Int0804SummaryVo> getResultByconditon(Int0804SearchVo request);
}
